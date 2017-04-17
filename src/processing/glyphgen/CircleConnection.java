package processing.glyphgen;

import processing.core.PApplet;
import processing.core.PGraphics;

public class CircleConnection extends Connection
{
	private float x, y, w, h;
	
	public CircleConnection(PApplet applet, GridPoint gridPoint1, GridPoint gridPoint2) 
	{
		super(applet, gridPoint1, gridPoint2);
		
		float p1x = PApplet.max(gridPoint1.getScreenX(), gridPoint2.getScreenX());
		float p2x = PApplet.min(gridPoint1.getScreenX(), gridPoint2.getScreenX());

		float p1y = PApplet.max(gridPoint1.getScreenY(), gridPoint2.getScreenY());
		float p2y = PApplet.min(gridPoint1.getScreenY(), gridPoint2.getScreenY());
		
		final float random = applet.random(0f, 1f);
		
		if(random < 0.5f)
		{
			x = gridPoint1.getScreenX();
			y = gridPoint1.getScreenY();
			
			w = PApplet.max(p1x - p2x, p1y - p2y);
			h = w;
		}
		else
		{
			float dx = p1x - p2x;
			float dy = p1y - p2y;
			
			if (p1x == p2x) // Vertical
			{
				w = dy;
				h = w;
				
				x = p1x;
				y = p2y + dy / 2f;
			}
			else if (p1y == p2y) // Horizontal
			{
				w = dx;
				h = w;
				
				y = p1y;
				x = p2x + dx / 2f;
			}
			else // Diagonal
			{			
				w = PApplet.sqrt(dx*dx + dy*dy);
				h = w;
				
				y = p2y + dy / 2f;
				x = p2x + dx / 2f;
			}
		}
	}
	
	public void draw()
	{
		applet.strokeWeight(2);
		applet.noFill();
		applet.stroke(0);
		applet.ellipse(x, y, w, h);
	}
	
	public void drawToBuffer(PGraphics buffer)
	{
		buffer.strokeWeight(2);
		buffer.noFill();
		buffer.stroke(0);
		buffer.ellipse(x, y, w, h);
	}
}
