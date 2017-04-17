package processing.glyphgen;

import processing.core.PApplet;
import processing.core.PGraphics;

public class LineConnection extends Connection
{
	public LineConnection(PApplet applet, GridPoint gridPoint1, GridPoint gridPoint2) 
	{
		super(applet, gridPoint1, gridPoint2);
	}
	
	public void draw()
	{
		applet.strokeWeight(2);
		applet.stroke(0);
		applet.noFill();
		applet.line(gridPoint1.getScreenX(), gridPoint1.getScreenY(), gridPoint2.getScreenX(), gridPoint2.getScreenY());
	}
	
	public void drawToBuffer(PGraphics buffer)
	{
		buffer.strokeWeight(2);
		buffer.stroke(0);
		buffer.noFill();
		buffer.line(gridPoint1.getScreenX(), gridPoint1.getScreenY(), gridPoint2.getScreenX(), gridPoint2.getScreenY());
	}
}
