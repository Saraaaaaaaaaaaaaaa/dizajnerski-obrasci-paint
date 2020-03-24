package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



public class ColorPickerRender extends JPanel implements ListCellRenderer{
	protected Color m_c = Color.black;

	  public ColorPickerRender() {
	    super();
	
	  }

	  public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus) {
	    if (obj instanceof Color)
	      m_c = (Color) obj;
	    return this;
	  }

	  public void paint(Graphics g) {
	    setBackground(m_c);
	    super.paint(g);

	  }
}
