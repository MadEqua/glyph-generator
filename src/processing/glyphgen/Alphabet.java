package processing.glyphgen;

import java.util.ArrayList;
import java.util.List;

import processing.core.PGraphics;

public class Alphabet 
{
	private Sketch applet;
	
	private List<Glyph> glyphs;
	
	private int w, h;
	
	public Alphabet(Sketch applet)
	{
		this.applet = applet;
			
		glyphs = new ArrayList<Glyph>();
	}
	
	public void generate(int numberOfGlyphs)
	{
		w = applet.glyphWidth;
		h = applet.glyphHeight;
		
		glyphs.clear();
		
		for(int i = 0; i < numberOfGlyphs; ++i)
			glyphs.add(new Glyph(applet, applet.glyphWidth, applet.glyphHeight, applet.pointGridWidth, applet.pointGridHeight, applet.maxConnectionsPerPoint, applet.maxConnectionDistance, applet.maxLineIntersections, applet.glyphWidth, applet.glyphHeight));
	}
	
	public void draw()
	{
		int x = Sketch.X_GAP, y = Sketch.Y_GAP;
		
		for(Glyph glyph : glyphs)
		{
			applet.resetMatrix();
			applet.translate(x, y);
			
			glyph.draw();
					
			x += w + Sketch.X_GAP;
			if(x > applet.width - w)
			{
				x = Sketch.X_GAP;
				y += h + Sketch.Y_GAP;
			}
		}
	}
	
	
	public void drawToBuffer(PGraphics buffer, int rows, int cols)
	{
		int x = applet.glyphWidth/2, y = applet.glyphHeight/2;
		
		int glyphI = 0;
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; ++c)
			{
				Glyph glyph = glyphs.get(glyphI);
				
				buffer.resetMatrix();
				buffer.translate(x, y);
				glyph.drawToBuffer(buffer);
				x += applet.glyphWidth + applet.glyphWidth/2;
				
				glyphI++;
				if(glyphI >= glyphs.size())
					return;
			}
			
			x = applet.glyphWidth/2;
			y += applet.glyphHeight + applet.glyphHeight/2;
		}
	}
	
	public int getSize()
	{
		return glyphs.size();
	}
}
