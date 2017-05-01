package noob.pwr;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	public Position2D warehouse;
	public List<Position2D> shopsToDraw;
	
	public List<Position2D> listLines;
	
	public int dotSize = 5;
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); 
	    if(shopsToDraw == null && warehouse==null)
	    	return;
	    
	    g.setColor(Color.cyan);
	    g.drawRect(0, 0, getWidth(), getHeight());
	    
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
	    
	    if(listLines!=null && listLines.size()%2 == 0)
	    {
	    	for(int i = 0;i<listLines.size();i+=2)
	    	{
	    		g.setColor(Color.black);
	    		g.drawLine((int)listLines.get(i).x + (this.getWidth()/2)
	    				, (int)listLines.get(i).y + (this.getHeight()/2)
	    				, (int)listLines.get(i+1).x + (this.getWidth()/2)
	    				, (int)listLines.get(i+1).y + (this.getHeight()/2));
	    	}
	    }
	}
 
}
