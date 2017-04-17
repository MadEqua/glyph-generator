package processing.glyphgen;

import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Connection 
{
	protected PApplet applet;
	
	protected GridPoint gridPoint1;
	protected GridPoint gridPoint2;
	
	public Connection(PApplet applet, GridPoint gridPoint1, GridPoint gridPoint2) 
	{
		this.applet = applet;
		
		this.gridPoint1 = gridPoint1;
		this.gridPoint2 = gridPoint2;
	}
	
	public abstract void draw();
	public abstract void drawToBuffer(PGraphics buffer);

	public GridPoint getGridPoint1() {
		return gridPoint1;
	}

	public GridPoint getGridPoint2() {
		return gridPoint2;
	}
}
