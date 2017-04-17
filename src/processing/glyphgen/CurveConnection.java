package processing.glyphgen;

import processing.core.PApplet;
import processing.core.PGraphics;

public class CurveConnection extends Connection 
{
	private float startAngle;
	private float stopAngle;
	
	private float x, y, w, h;

	public CurveConnection(PApplet applet, GridPoint gridPoint1, GridPoint gridPoint2) 
	{
		super(applet, gridPoint1, gridPoint2);

		float p1x = PApplet.max(gridPoint1.getScreenX(), gridPoint2.getScreenX());
		float p2x = PApplet.min(gridPoint1.getScreenX(), gridPoint2.getScreenX());

		float p1y = PApplet.max(gridPoint1.getScreenY(), gridPoint2.getScreenY());
		float p2y = PApplet.min(gridPoint1.getScreenY(), gridPoint2.getScreenY());
		
		final float sideOfCurveRandom = applet.random(0f, 1f);
		final float curveStrengthRandom = applet.random(0.25f, 1f);

		if (p1x == p2x) // Vertical
		{
			float dy = p1y - p2y;
			
			h = dy;
			w = h * curveStrengthRandom;
			
			x = p1x;
			y = p2y + (dy / 2f);
			
			if(sideOfCurveRandom < 0.5f)
			{
				startAngle = - PApplet.PI / 2f;
				stopAngle = PApplet.PI / 2f;
			}
			else
			{
				startAngle = PApplet.PI / 2f;
				stopAngle = 3f * PApplet.PI / 2f;
			}
		}
		else if (p1y == p2y) // Horizontal
		{
			float dx = p1x - p2x;
			
			w = dx;
			h = w * curveStrengthRandom;
			
			x = p2x + (dx / 2f);
			y = p1y;
			
			if(sideOfCurveRandom < 0.5f)
			{
				startAngle = 0f;
				stopAngle = PApplet.PI;
			}
			else
			{
				startAngle = PApplet.PI;
				stopAngle = 2f * PApplet.PI;
			}
		} 
		else // Diagonal
		{
			float dx = p1x - p2x;
			float dy = p1y - p2y;
			
			w = PApplet.sqrt(dx*dx + dy*dy);
			h = w;
			
			float alpha = PApplet.atan2(dy, dx);

			x = p2x + (dx / 2f);
			y = p2y + (dy / 2f);
			
			if(gridPoint1.getScreenX() > gridPoint2.getScreenX())
			{
				if(gridPoint1.getScreenY() > gridPoint2.getScreenY())
					diagonalDecline(sideOfCurveRandom, alpha, curveStrengthRandom);
				else
					diagonalIncline(sideOfCurveRandom, alpha, curveStrengthRandom);
			}
			else
			{
				if(gridPoint1.getScreenY() > gridPoint2.getScreenY())
					diagonalIncline(sideOfCurveRandom, alpha, curveStrengthRandom);
				else
					diagonalDecline(sideOfCurveRandom, alpha, curveStrengthRandom);
			}
		}
	}
	
	private void diagonalDecline(float sideOfCurveRandom, float alpha, float curveStrengthRandom)
	{
		if(sideOfCurveRandom < 0.5f)
		{
			startAngle = alpha - PApplet.PI;
			stopAngle = alpha;
		}
		else
		{
			startAngle = alpha;
			stopAngle = alpha + PApplet.PI;
		}
	}
	
	private void diagonalIncline(float sideOfCurveRandom, float alpha, float curveStrengthRandom)
	{
		if(sideOfCurveRandom < 0.5f)
		{
			startAngle = -alpha + PApplet.PI;
			stopAngle =  -alpha + 2*PApplet.PI;
		}
		else
		{
			startAngle = -alpha;
			stopAngle = -alpha + PApplet.PI;
		}
	}

	public void draw() 
	{
		applet.strokeWeight(2);
		applet.stroke(0);
		applet.noFill();
		applet.arc(x, y, w, h, startAngle, stopAngle);
	}
	
	public void drawToBuffer(PGraphics buffer)
	{
		buffer.strokeWeight(2);
		buffer.stroke(0);
		buffer.noFill();
		buffer.arc(x, y, w, h, startAngle, stopAngle);
	}
}
