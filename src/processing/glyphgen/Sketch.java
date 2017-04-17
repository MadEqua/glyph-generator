package processing.glyphgen;

import java.io.File;

import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.Controller;

public class Sketch extends PApplet 
{
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	
	private Alphabet alphabet;
	
	private UI ui;
	
	public static final int X_GAP = 40;
	public static final int Y_GAP = 40;
	
	public static final int UI_X = 10;
	public static final int UI_Y = 600;
	
	public static final int SCREEN_HEIGHT_GLYPHS = UI_Y - Y_GAP*2;
	
	//PARAMETERS FILLED BY THE UI (REFLECTION)
	public int glyphWidth = 65;
	public int glyphHeight = 65;
	
	public int pointGridWidth = 3;
	public int pointGridHeight = 3;
	
	public int maxConnectionsPerPoint = 2;
	public int maxConnectionDistance = 1;
	
	public int maxLineIntersections = 1;
	
	public float minStrokesMultiplier = 0.4f;
	public float maxStrokesMultiplier = 0.8f;
	
	public float lineProbability = 0.97f;
	public float curveProbability = 0.015f;
	public float circleProbability = 0.015f;
	
	public boolean isDebug = false;
	public boolean isBackground = true;

	public void setup()
	{
		size(WIDTH, HEIGHT);
		fill(255);
		stroke(0);
		
		frame.setTitle("Glyph Generator");		
		
		ui = new UI(this);
		alphabet = new Alphabet(this);

		generate();
	}

	public void draw() 
	{
		background(255);
		
		alphabet.draw();
		
		resetMatrix();
		ui.draw();
	}
	
	public void generate() 
	{
		alphabet.generate(findHowManyToFit());
	}
	
	private int findHowManyToFit()
	{
		int columns = (int) PApplet.ceil((float)(WIDTH-glyphWidth) / (float)(glyphWidth+X_GAP));
		int lines = (int) PApplet.ceil((float)(SCREEN_HEIGHT_GLYPHS-glyphHeight) / (float)(glyphHeight+Y_GAP));
		return columns * lines;
	}

	public static void main(String args[]) 
	{
		PApplet.main("processing.glyphgen.Sketch");
	}
	

	
	/*
	 * CALLBACKS OF THE UI NEED TO BE HERE
	 */
	private float lastValueLineProb;
	private float lastValueCurveProb;
	private float lastValueCircleProb;
	
	public void lineProbability(float value) 
	{
		if(ui == null) return;
		
		lastValueLineProb = lineProbability;
		lineProbability = value;
		
		float adjustBy = (value - lastValueLineProb)/2f;
		circleProbability -= adjustBy;
		curveProbability -= adjustBy;
		
		if(circleProbability < 0f)
		{
			circleProbability = 0;
			//float dif = - circleProbability;
			lineProbability -= adjustBy;
		}
		else if(circleProbability > 1f)
		{
			circleProbability = 1;
			//float dif = 1f - circleProbability;
			lineProbability += adjustBy;
		}
		
		if(curveProbability < 0f)
		{
			curveProbability = 0;
			//float dif = - curveProbability;
			circleProbability -= adjustBy;
		}
		else if(curveProbability > 1f)
		{
			curveProbability = 1;
			//float dif = 1f - curveProbability;
			circleProbability += adjustBy;
		}
		
		Controller<?> controller = ui.getControlP5().getController("curveProbability");
		controller.setBroadcast(false);
		controller.setValue(curveProbability);
		controller.setBroadcast(true);
		
		controller = ui.getControlP5().getController("circleProbability");
		controller.setBroadcast(false);
		controller.setValue(circleProbability);
		controller.setBroadcast(true);
	}
	
	public void curveProbability(float value) 
	{
		if(ui == null) return;
		
		lastValueCurveProb = curveProbability;
		curveProbability = value;
			
		float adjustBy = (value - lastValueCurveProb)/2f;
		circleProbability -= adjustBy;
		lineProbability -= adjustBy;
		
		if(circleProbability < 0f)
		{
			circleProbability = 0;
			//float dif = - circleProbability;
			lineProbability -= adjustBy;
		}
		else if(circleProbability > 1f)
		{
			circleProbability = 1;
			//float dif = 1f - circleProbability;
			lineProbability += adjustBy;
		}
		
		if(lineProbability < 0f)
		{
			lineProbability = 0;
			//float dif = - lineProbability;
			circleProbability -= adjustBy;
		}
		else if(lineProbability > 1f)
		{
			lineProbability = 0;
			//float dif = 1f - lineProbability;
			circleProbability += adjustBy;
		}
		
		Controller<?> controller = ui.getControlP5().getController("lineProbability");
		controller.setBroadcast(false);
		controller.setValue(lineProbability);
		controller.setBroadcast(true);
		
		controller = ui.getControlP5().getController("circleProbability");
		controller.setBroadcast(false);
		controller.setValue(circleProbability);
		controller.setBroadcast(true);
	}
	
	public void circleProbability(float value) 
	{
		if(ui == null) return;
		
		lastValueCircleProb = circleProbability;
		circleProbability = value;
		
		float adjustBy = (value - lastValueCircleProb)/2f;
		curveProbability -= adjustBy;
		lineProbability -= adjustBy;
		
		if(curveProbability < 0f)
		{
			curveProbability = 0;
			//float dif = - curveProbability;
			circleProbability -= adjustBy;
		}
		else if(curveProbability > 1f)
		{
			curveProbability = 1;
			//float dif = 1f - curveProbability;
			circleProbability += adjustBy;
		}
		
		if(lineProbability < 0f)
		{
			lineProbability = 0;
			//float dif = - lineProbability;
			circleProbability -= adjustBy;
		}
		else if(lineProbability > 1f)
		{
			lineProbability = 0;
			//float dif = 1f - lineProbability;
			circleProbability += adjustBy;
		}
		
		Controller<?> controller = ui.getControlP5().getController("lineProbability");
		controller.setBroadcast(false);
		controller.setValue(lineProbability);
		controller.setBroadcast(true);
		
		controller = ui.getControlP5().getController("curveProbability");
		controller.setBroadcast(false);
		controller.setValue(curveProbability);
		controller.setBroadcast(true);
	}
	
	
	public void export()
	{
		selectFolder("Select a folder to process:", "folderSelected", null, this);
	}


	public void folderSelected(File selection) 
	{
		if (selection != null) 
		{
			int rows = 4;
			int cols = PApplet.ceil((float) alphabet.getSize() / (float) rows);
			PGraphics pgraphics = createGraphics(50
					+ (glyphWidth + glyphWidth / 2) * cols, 50
					+ (glyphHeight + glyphHeight / 2) * rows);

			pgraphics.smooth(8);
			pgraphics.beginDraw();
			alphabet.drawToBuffer(pgraphics, rows, cols);
			pgraphics.endDraw();

			pgraphics.save(selection.getAbsolutePath() + "/glyphs_" + day() + hour()
					+ minute() + second() + ".png");
		}
	}
}
