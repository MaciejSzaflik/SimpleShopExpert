package noob.pwr;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	public Position2D warehouse;
	public List<Position2D> shopsToDraw;
	public int dotSize = 5;
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); 
	    if(shopsToDraw != null && warehouse!=null)
	    
	    for(Position2D position : shopsToDraw)
	    {
	    	g.setColor(Color.black);
		    g.drawRect(position.getIntX() + (this.getWidth()/2),position.getIntY() + (this.getHeight()/2),dotSize,dotSize);  
		    g.setColor(Color.RED);  
		    g.fillRect(position.getIntX() + (this.getWidth()/2) ,position.getIntY() + (this.getHeight()/2),dotSize,dotSize);  
	    }
	    
	    g.drawRect(warehouse.getIntX() + (this.getWidth()/2),warehouse.getIntY() + (this.getHeight()/2),dotSize,dotSize);  
	    g.setColor(Color.black);  
	    g.fillRect(warehouse.getIntX() + (this.getWidth()/2),warehouse.getIntY() + (this.getHeight()/2),dotSize,dotSize);  
	}
 
}
