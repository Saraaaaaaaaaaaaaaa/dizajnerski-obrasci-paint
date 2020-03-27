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

import main.controller.observers.ColorPickerObserver;
import main.controller.observers.DeleteObserver;
import main.model.Editor;
import main.model.ShapeInderface;
import main.model.shapes.Circle;
import main.model.shapes.Point;
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
		eventManeger.subscribe("MANAGING BUTTONS", new DeleteObserver(source.getTopToolBar()));
	}

	public void notifyManager(String name, String option) {
		eventManeger.notifyObserver(name, option);
		
	}
	public CustomPaintComponent(MainWindow source) {

		setBackground(Color.white);

		attachKeyboardListeners();
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
				switch (shapeSelector) {
				case "POINT":
					break;
				case "LINE": // shapeArray.add(new Line(firstPoint, secondPoint)); break;
				case "TRIANGLE":
					// shapeArray.add(new Triangle(firstPoint, secondPoint));
					break;
				case "RECTANGLE":// shapeArray.add(new Rectangle(firstPoint, secondPoint));
					break;
				case "CIRCLE":
					int a = secondPoint.getX() - firstPoint.getX();
					int b = secondPoint.getY() - firstPoint.getY();
					int radius = (int) (Math.sqrt(a * a + b * b) / 2.4);
					//
					if (radius > 2)
						editor.addShape(new Circle(firstPoint.getX(), firstPoint.getY(), radius,
								colorPicker.getSelectedColor()));
					break;
				}

				// repaint();
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
						break;
					case "CIRCLE":
						int a = secondPoint.getX() - firstPoint.getX();
						int b = secondPoint.getY() - firstPoint.getY();

						int radius = (int) (Math.sqrt(a * a + b * b) / 2.4);
						pivotShape = new Circle(firstPoint.getX(), firstPoint.getY(), radius,
								colorPicker.getSelectedColor());
						break;

					}

					repaint();
				}
			}
		});
	}

	private void attachKeyboardListeners() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_Z:
						editor.undo();
						break;
					case KeyEvent.VK_R:
						editor.redo();
						break;
					}
				}
			}
		});
	}

	private void setReadyToScaleShape(boolean b) {
		resizeShapeState = b;
	}

	private void attachMouseListeners() {
		MouseAdapter colorizer = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3) {
					if (shapeSelector == "COLORING") {
						ShapeInderface target = editor.getChildAt(e.getX(), e.getY());
						if (target != null && shapeSelector == "COLORING") {
							editor.execute(new ColorCommand(editor, colorPicker.getSelectedColor()));
							repaint();
						}
					}

				}else {

				ContextMenu menu = new ContextMenu();

				menu.addSource(CustomPaintComponent.this);
				menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

		};
		addMouseListener(colorizer);

		MouseAdapter selector = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1) {
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
									eventManeger.notifyObserver("MANAGING BUTTONS", "ENABLE DELETE  MODIFY");
								if(editor.getSelected().size() > 1)
									eventManeger.notifyObserver("MANAGING BUTTONS", "DISABLE  MODIFY");									
								// lud zbunjenog
							} else {
								if (!ctrl)
									editor.unSelect();
								
								target.select();
								eventManeger.notifyObserver("MANAGING BUTTONS", "ENABLE DELETE MODIFY");
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
					repaint();

					break;
				case "SELECT":
					if (moveCommand == null) {
						moveCommand = new MoveCommand(editor);
						moveCommand.start(e.getX(), e.getY());
					}
					moveCommand.move(e.getX(), e.getY());
					repaint();
					break;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1 || moveCommand == null) {
					return;
				}
				moveCommand.stop(e.getX(), e.getY());

				editor.execute(moveCommand);
				this.moveCommand = null;
				this.resizeCommand = null;
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