package processing.glyphgen;

import processing.core.PFont;
import controlP5.Button;
import controlP5.ControlP5;

public class UI 
{
	private Sketch applet;
	
	private ControlP5 controlP5;
	
	private static final int SLIDER_W = 200;
	private static final int SLIDER_H = 22;
	
	private static final int GAP_X = SLIDER_W + 20;
	private static final int GAP_Y = SLIDER_H + 10;
	
	public UI(Sketch applet)
	{
		this.applet = applet;
		
		controlP5 = new ControlP5(applet);
		
		PFont font = applet.createFont("TAHOMA", 15);

		int x = Sketch.UI_X;
		int y = Sketch.UI_Y;
		
		controlP5.addTextlabel("labelDimensions").setText("Glyph Dimensions").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("glyphWidth").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(10, 150).
		setValue(65);
		y += GAP_Y;
		
		controlP5.addSlider("glyphHeight").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(10, 150).
		setValue(65);
		y += GAP_Y;
		
		
		controlP5.addTextlabel("labelgridPoints").setText("Grid points").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("pointGridWidth").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(3, 10).
		setValue(3);
		y += GAP_Y;
		
		controlP5.addSlider("pointGridHeight").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(3, 10).
		setValue(3);
		x += GAP_X;
		y = Sketch.UI_Y;
		
		
		controlP5.addTextlabel("labelMaxConnectionsPerPoint").setText("Max Lines/Point").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("maxConnectionsPerPoint").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(1, 10).
		setValue(2);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelMaxConnectionDistance").setText("Max Line Distance").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("maxConnectionDistance").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(1, 10).
		setValue(1);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelMaxLineIntersections").setText("Max Line Intersections").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("maxLineIntersections").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0, 10).
		setValue(1);
		x += GAP_X;
		y = Sketch.UI_Y;
		
		
		controlP5.addTextlabel("labelMinStrokesMultiplier").setText("Min Lines/Glyph").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("minStrokesMultiplier").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0.1f, 1.0f).
		setValue(0.4f);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelMaxStrokesMultiplier").setText("Max Lines/Glyph").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("maxStrokesMultiplier").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0.1f, 1.0f).
		setValue(0.8f);
		x += GAP_X;
		y = Sketch.UI_Y;
		
		
		controlP5.addTextlabel("lineLineProbability").setText("Line Probability").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("lineProbability").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0.0f, 1.0f).setDecimalPrecision(3).
		setValue(0.97f);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelCurveProbability").setText("Curve Probability").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("curveProbability").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0.0f, 1.0f).setDecimalPrecision(3).
		setValue(0.015f);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelCircleProbability").setText("Circle Probability").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		
		controlP5.addSlider("circleProbability").
		setPosition(x, y).
		setSize(SLIDER_W, SLIDER_H).
		setRange(0.0f, 1.0f).setDecimalPrecision(3).
		setValue(0.015f);
		x += GAP_X;
		y = Sketch.UI_Y;
		
		controlP5.addTextlabel("labelIsDebug").setText("Toggle Debug View").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		controlP5.addToggle("isDebug").setPosition(x, y).setSize(SLIDER_H, SLIDER_H).setValue(false);
		y += GAP_Y;
		
		controlP5.addTextlabel("labelIsBackground").setText("Toggle Background").setPosition(x, y).setSize(SLIDER_W, SLIDER_H).setColor(0).setFont(font);
		y += GAP_Y;
		controlP5.addToggle("isBackground").setPosition(x, y).setSize(SLIDER_H, SLIDER_H).setValue(true);		
		x += GAP_X;
		y = Sketch.UI_Y;
		
		Button button = controlP5.addButton("generate").
		setPosition(x, y).setSize(150, 60);
		button.getCaptionLabel().getFont().setSize(15);
		button.getCaptionLabel().setText("Generate!").alignX(ControlP5.CENTER);
		y += GAP_Y*2.7f;
		
		Button buttonExport = controlP5.addButton("export").
				setPosition(x, y).setSize(150, 60);
		buttonExport.getCaptionLabel().getFont().setSize(15);
		buttonExport.getCaptionLabel().setText("Export PNG!").alignX(ControlP5.CENTER);
		
		controlP5.addTextlabel("labelCredits").setText("#PROCJAM 2014 - Bruno Lourenço - @MadEqua").setPosition(applet.width-390, Sketch.HEIGHT-30).setColor(0).setFont(font);		
	}
	
	
	public void generate() 
	{
		applet.generate();
	}
	
	public void draw()
	{
		controlP5.draw();
	}

	public ControlP5 getControlP5() 
	{
		return controlP5;
	}
}
