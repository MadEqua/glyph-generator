package processing.glyphgen;

import processing.core.PApplet;

public class GridPoint
{
	private int gridX, gridY;
	private float screenX, screenY;
	private int totalConnections;
	
	private PApplet applet;

	public GridPoint(PApplet applet, int gridX, int gridY, float screenX, float screenY)
	{
		this.applet = applet;
		
		this.gridX = gridX;
		this.gridY = gridY;
		
		this.screenX = screenX;
		this.screenY = screenY;
		
		totalConnections = 0;
	}

	public int getGridX() 
	{
		return gridX;
	}

	public int getGridY() 
	{
		return gridY;
	}
	
	public float getScreenX() 
	{
		return screenX;
	}

	public float getScreenY() 
	{
		return screenY;
	}

	public void addConnection()
	{
		totalConnections++;
	}

	public int getTotalConnections()
	{
		return totalConnections;
	}
	
	public void draw()
	{
		applet.strokeWeight(5);
		applet.stroke(0);
		applet.noFill();
		applet.point(screenX, screenY);
	}
	
	public String toString()
	{
		return "[" + gridX + ", " + gridY + "]";
	}
}