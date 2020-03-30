package main.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.controller.EventManeger;
import main.controller.observers.ColorPickerObserver;

	
public class ColorPickerDialog extends JFrame{

	public JPanel contexPane = new JPanel(); 
	public JPanel wraper = new JPanel();
	private GroupLayout framelayout ;
	private GridLayout  layout = new GridLayout(10,10);
	private EventManeger eventManeger;
	private JLabel label1 = new JLabel("Pick color ");
	private JButton btnOk ;
	private JButton sender;
	public ColorPickerDialog(EventManeger eventManeger, JButton sender) {
		this.sender = sender;
		this.eventManeger = eventManeger;
		setMinimumSize(new Dimension(280, 280));
		framelayout = new GroupLayout(wraper); 
		wraper.setLayout(framelayout);
		contexPane.setLayout(layout);
		
		addListenerToButtonOK();
		generateOptions();
		
		framelayout.setHorizontalGroup(
				framelayout.createSequentialGroup()				  
				      .addGroup(framelayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(label1)
				           .addComponent(contexPane)
				           .addComponent(btnOk))	
				     
				);
		framelayout.setVerticalGroup(
				framelayout.createSequentialGroup()
				.addComponent(label1)
				.addComponent(contexPane)		
				 .addComponent(btnOk)
				);
	
		add(wraper);
		
		pack();
	}
	private void addListenerToButtonOK() {
		btnOk = new JButton(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				eventManeger.notifyObserver("UPDATE "+sender.getName() + " COLOR",Integer.toString(sender.getBackground().getRGB()));
				dispose();
				
				
			}
		});
		btnOk.setText("Ok");
	}
	private void generateOptions() {
		addItem(Color.BLACK);
		for (int g = 0; g < 255; g+=12) {
		       Color c = new Color(255, g ,0);		       
		          addItem(c);
		}
		for (int r = 255; r > 0; r-=12) {
		       Color c = new Color(r, 255, 255-r);
		          addItem(c);
		}
		for (int g = 255; g > 0; g-=12) {
		       Color c = new Color(255 - g, g, 255);
		          addItem(c);
		}
		for (int b = 255; b > 0 ; b-=20) {
		       Color c = new Color(255,255-b,255);
		          addItem(c);
		}
		
		
		
		
		
	}
	void addItem(Color option) {

		Button clr = new Button();
		clr.setSize(new Dimension(30,30 ));
		clr.setBackground(option);
		clr.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sender.setBackground( clr.getBackground());
			}
		});		
			
		contexPane.add(clr);
	}
	public Color getSelectedColor() {
		return Color.GREEN;
	}
	


}
