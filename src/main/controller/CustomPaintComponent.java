package main.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

import main.controller.observers.ColorPickerObserver;
import main.controller.comamnds.ColorCommand;
import main.controller.comamnds.CreateShapeCommand;
import main.controller.comamnds.MoveCommand;
import main.controller.comamnds.ResizeCommand;
import main.controller.observers.ButtonsObserver;
import main.model.Editor;
import main.model.ShapeInderface;
import main.model.shapes.Circle;
import main.model.shapes.Point;
import main.model.shapes.Rectangle;
import main.model.shapes.Shape;
import main.view.ColorPicker;
import main.view.ContextMenu;
import main.view.MainWindow;

public class CustomPaintComponent extends Component {

	private static final long serialVersionUID = 1L;
	private String shapeSelector = "";
	private Shape pivotShape;
	private Boolean firstClick = true;
	private Point firstPoint = new Point();
	private Point secondPoint = new Point();
	private Editor editor;
	private boolean resizeShapeState;
	private ColorPicker colorPicker;
	private EventManeger eventManeger = new EventManeger();

	public void setSelectedShape(String shapeSelector) {
		this.shapeSelector = shapeSelector;
	}

	public void setSource(MainWindow source) {
		this.colorPicker = source.getColorPicker();
		this.editor = source.getEditor();
		eventManeger.subscribe("MANAGING BUTTONS", new ButtonsObserver(source.getTopToolBar()));
	}

	public void notifyManager(String name, String option) {
		eventManeger.notifyObserver(name, option);

	}

	public CustomPaintComponent(MainWindow source) {

		setBackground(Color.white);
		attachMouseListeners();

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (firstClick) {
					firstPoint.setX(e.getX());
					firstPoint.setY(e.getY());
					firstClick = false;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				secondPoint.setX(e.getX());
				secondPoint.setY(e.getY());
				if (shapeSelector != "SELECT") { // or else
					CreateShapeCommand command = new CreateShapeCommand(editor);
					command.create(shapeSelector, firstPoint, secondPoint, colorPicker.getSelectedFillColor(), colorPicker.getSelectedLineColor());
					editor.execute(command);
					eventManeger.notifyObserver("MANAGING BUTTONS", "ENABLE UNDO");
					eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE REDO");
					 
				}
				repaint();
				firstClick = true;
				pivotShape = null;

			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (!firstClick) {
					secondPoint.setX(e.getX());
					secondPoint.setY(e.getY());

					switch (shapeSelector) {
					case "POINT":
						/* TODO: as point */ break;
					case "LINE":
						// pivotShape = new Line(firstPoint, secondPoint);
						break;
					case "TRIANGLE":
						// pivotShape = new Triangle(firstPoint, secondPoint);
						break;
					case "RECTANGLE":
						 pivotShape = new Rectangle(firstPoint, secondPoint, colorPicker.getSelectedFillColor(), colorPicker.getSelectedLineColor());
						break;
					case "CIRCLE":
						int a = secondPoint.getX() - firstPoint.getX();
						int b = secondPoint.getY() - firstPoint.getY();

						int radius = (int) (Math.sqrt(a * a + b * b) / 2.4);
						pivotShape = new Circle(firstPoint.getX(), firstPoint.getY(), radius, colorPicker.getSelectedFillColor(), colorPicker.getSelectedLineColor());
						break;

					}

					repaint();
				}
			}
		});
		// attachKeyboardListeners();
	}

	private void setReadyToScaleShape(boolean b) {
		resizeShapeState = b;
	}

	private void attachMouseListeners() {
		MouseAdapter contexMenu = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {					
						ShapeInderface target = editor.getChildAt(e.getX(), e.getY());
						if (target != null ) {
							ContextMenu menu = new ContextMenu();
							menu.addSource(CustomPaintComponent.this);
							menu.show(e.getComponent(), e.getX(), e.getY());
						}
				}

			}
		};
		addMouseListener(contexMenu);

		MouseAdapter selector = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isMiddleMouseButton(e)) {
					return;
				}

				ShapeInderface target = editor.getChildAt(e.getX(), e.getY());
				boolean ctrl = (e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK;
				boolean select = shapeSelector.toUpperCase().equals("SELECT");
				if (target == null) {
					if (select) {
						editor.unSelect();
						eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE MODIFY DELETE");
					}
				} else {
					if (target.isOnEdges(e.getX(), e.getY()) && target.isSelected()) {
						setReadyToScaleShape(true);
					} else {
						if (select) {
							if (target.isSelected()) {
								target.unSelect();
								if(editor.getSelected().size() == 0)
									eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE DELETE MODIFY");								
								if(editor.getSelected().size() == 1)
									eventManeger.notifyObserver("MANAGING BUTTONS", "ENABLE DELETE MODIFY");
								if(editor.getSelected().size() > 1)
									eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE MODIFY");									
								
							} else {
								if (!ctrl)
									editor.unSelect();
								
								target.select();
								
								if(ctrl)
								{
									if(editor.getSelected().size() == 0)
										eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE DELETE MODIFY");								
									if(editor.getSelected().size() == 1)
										eventManeger.notifyObserver("MANAGING BUTTONS", "ENABLE DELETE  MODIFY");
									if(editor.getSelected().size() > 1)
										eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE  MODIFY");
										
								}
							}
						}
					}
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setReadyToScaleShape(false);
			}

		};
		addMouseListener(selector);

		MouseAdapter dragger = new MouseAdapter() {
			MoveCommand moveCommand;
			ResizeCommand resizeCommand;

			@Override
			public void mouseDragged(MouseEvent e) {
				if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != MouseEvent.BUTTON1_DOWN_MASK) {
					return;
				}
				switch (shapeSelector.toUpperCase()) {
				case "SCALE":

					if (resizeCommand == null) {
						resizeCommand = new ResizeCommand(editor);
						resizeCommand.start(e.getX(), e.getY());
					}
					resizeCommand.scale(e.getX(), e.getY());
				

					break;
				case "SELECT":
					if (moveCommand == null) {
						moveCommand = new MoveCommand(editor);
						moveCommand.start(e.getX(), e.getY());
					}
					moveCommand.move(e.getX(), e.getY());
					repaint();
					break;
				case "DELETE":
					break;
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1) {
					return;
				}
				switch (shapeSelector.toUpperCase()) {
				case "SELECT":
						if(moveCommand!=null)
						if(moveCommand.hasTarget()) 
						{
							moveCommand.stop(e.getX(), e.getY());
							editor.execute(moveCommand);
							eventManeger.notifyObserver("MANAGING BUTTONS","ENABLE UNDO ");
							eventManeger.notifyObserver("MANAGING BUTTONS","DISABLE REDO");
						}
						this.moveCommand = null;
					break;
				case "SCALE":
					if(resizeCommand!=null)
						if(resizeCommand.hasTarget()) 
						{
							resizeCommand.stop(e.getX(), e.getY());
							editor.execute(resizeCommand);
							eventManeger.notifyObserver("MANAGING BUTTONS","ENABLE UNDO ");
							eventManeger.notifyObserver("MANAGING BUTTONS","DISABLE REDO");
							
						}
						this.resizeCommand = null;
					break;
				case "DELETE":
					break;
				}	
				repaint();
			}
		};

	addMouseListener(dragger);
		addMouseMotionListener(dragger);
	}

	// Ona se ponavlja pozivom funkcije repaint();
	public void paint(Graphics g) {

		for (Shape shape : editor.getShapes()) {
			draw(shape, g);

		}
		if (pivotShape != null)
			draw(pivotShape, g);

	}

	void draw(Shape shape, Graphics g) {

		shape.paint(g);
	}

	public Editor getEditor() {
		return editor;
	}

	public void changeCursorToMove() {
		setCursor(new Cursor(Cursor.MOVE_CURSOR));
	}

	public void changeCursorToModificatior() {
		setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));

	}

}