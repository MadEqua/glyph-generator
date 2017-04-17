package processing.glyphgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import processing.core.PGraphics;

public class Glyph 
{
	private Sketch applet;
	
	private int width; //in pixels
	private int height;
	
	private int pointGridWidth; //in points
	private int pointGridHeight;
	
	private int maxConnectionsPerPoint;
	private int maxConnectionDistance; //grid coordinates
	
	private int maxLineIntersections;
	private int intersectionCount;
	
	private GridPoint gridPoints[][];
	
	private List<Connection> connections;
	
	private List<GridPoint> adjacentList;
	
	public Glyph(Sketch applet, int width, int height, int pointGridWidth, int pointGridHeight, int maxConnectionsPerPoint, int maxConnectionDistance, int maxLineIntersections, int glyphWidth, int glyphHeight)
	{
		this.applet = applet;
		
		this.width = width;
		this.height = height;
		
		this.pointGridWidth = pointGridWidth;
		this.pointGridHeight = pointGridHeight;

		this.maxConnectionsPerPoint = maxConnectionsPerPoint;
		this.maxConnectionDistance = maxConnectionDistance;
		this.maxLineIntersections = maxLineIntersections;
		
		intersectionCount = 0;
		
		connections = new ArrayList<Connection>();
		adjacentList = new LinkedList<GridPoint>();
		
		gridPoints = new GridPoint[pointGridWidth][pointGridHeight];
		for(int x = 0; x < gridPoints.length; ++x)
			for(int y = 0; y < gridPoints[0].length; ++y)
				gridPoints[x][y] = new GridPoint(applet, x, y, (float)x * (float)glyphWidth/(float)(pointGridWidth-1f), (float)y * (float)glyphHeight/((float)pointGridHeight-1f));
		
		generate();
	}
	
	private void generate()
	{		
		GridPoint point = gridPoints[(int) applet.random(0, pointGridWidth)][(int) applet.random(0, pointGridHeight)];
		getAdjacents(point, maxConnectionDistance);
		
		int strokes = 0;
		int maxStrokes = (pointGridWidth * pointGridHeight);
		final int totalStrokes = (int) applet.random(maxStrokes * applet.minStrokesMultiplier, maxStrokes * applet.maxStrokesMultiplier);
		int tries = 0;
		final int MAX_TRIES = 100;
		
		//PApplet.println(maxStrokes);
		
		while(strokes < totalStrokes && tries < MAX_TRIES)
		{
			GridPoint randomAdjacent = adjacentList.get((int) applet.random(0, adjacentList.size()));
			Connection c = getConnection(point, randomAdjacent);
			int intersections = countIntersections(c);
			
			//PApplet.println("P: " + point + ": " + point.getTotalConnections() + ", RA: " + randomAdjacent + ": " + randomAdjacent.getTotalConnections() + "; " + maxConnectionsPerPoint);
			
			if(randomAdjacent.getTotalConnections() >= maxConnectionsPerPoint || //point.getTotalExitConnections() >= maxConnectionsPerPoint || 
					(intersectionCount + intersections > maxLineIntersections) ||
					checkConnectionBetweenPoints(point, randomAdjacent))
			{
				tries++;
			}
			else
			{
				intersectionCount += intersections;
							
				connections.add(c);
				
				point.addConnection();
				randomAdjacent.addConnection();

				strokes++;
				tries = 0;
				
				getAdjacents(randomAdjacent, maxConnectionDistance);
				adjacentList.remove(point);
				point = randomAdjacent;
			}
		}
		
		//PApplet.println(tries + "," + strokes + "," + intersectionCount);
	}
	
	private Connection getConnection(GridPoint gridPoint1, GridPoint gridPoint2)
	{
		final float r = applet.random(0f, 1f);
		
		if(r < applet.circleProbability)
			return new CircleConnection(applet, gridPoint1, gridPoint2);
		else if(r < applet.circleProbability + applet.curveProbability)
			return new CurveConnection(applet, gridPoint1, gridPoint2);
		else
			return new LineConnection(applet, gridPoint1, gridPoint2);
	}
	
	private boolean checkConnectionBetweenPoints(GridPoint point1, GridPoint point2)
	{
		for(Connection connection : connections)
		{
			if((connection.getGridPoint1().equals(point1) && connection.getGridPoint2().equals(point2)) ||
				(connection.getGridPoint2().equals(point1) && connection.getGridPoint1().equals(point2)))
				return true;
		}
		return false;
	}
	
	private int countIntersections(Connection connection1)
	{
		int count = 0;
		
		for(Connection connection2 : connections)
		{
			GridPoint con1Point1 = connection1.getGridPoint1();
			GridPoint con1Point2 = connection1.getGridPoint2();
			GridPoint con2Point1 = connection2.getGridPoint1();
			GridPoint con2Point2 = connection2.getGridPoint2();
			if(intersectLineSegments(con1Point1.getGridX(), con1Point1.getGridY(), con1Point2.getGridX(), con1Point2.getGridY(), con2Point1.getGridX(), con2Point1.getGridY(), con2Point2.getGridX(), con2Point2.getGridY()))
				count++;
		}
		return count;
	}

	private boolean intersectLineSegments(float p0_x, float p0_y, float p1_x,
			float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) 
	{
		float s1_x, s1_y, s2_x, s2_y;
		s1_x = p1_x - p0_x;
		s1_y = p1_y - p0_y;
		s2_x = p3_x - p2_x;
		s2_y = p3_y - p2_y;

		float s, t;
		s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y))
				/ (-s2_x * s1_y + s1_x * s2_y);
		t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x))
				/ (-s2_x * s1_y + s1_x * s2_y);

		if (s > 0 && s < 1 && t > 0 && t < 1)
			return true;
		else
			return false;
	}

	private List<GridPoint> getAdjacents(GridPoint point, int radius)
	{
		adjacentList.clear();
		for(int x = point.getGridX() - radius; x <= point.getGridX() + radius; ++x)
			for(int y = point.getGridY() - radius; y <= point.getGridY() + radius; ++y)
			{
				if(x < 0 || y < 0 || x >= pointGridWidth || y >= pointGridHeight || (x == point.getGridX() && y == point.getGridY()))
					continue;
				
				adjacentList.add(gridPoints[x][y]);
			}
		
		return adjacentList;
	}
	
	public void draw()
	{
		if(applet.isBackground)
		{
			applet.fill(240);
			applet.noStroke();
			applet.rect(-5, -5, width+10, height+10);
		}
		
		for(Connection connection : connections)
			connection.draw();
		
		if(applet.isDebug)
		{
			for(int x = 0; x < gridPoints.length; ++x)
				for(int y = 0; y < gridPoints[0].length; ++y)
					gridPoints[x][y].draw();
			
			applet.strokeWeight(1);
			applet.stroke(0);
			applet.noFill();
			applet.rect(0, 0, width, height);
		}
	}
	
	public void drawToBuffer(PGraphics buffer)
	{
		for(Connection connection : connections)
			connection.drawToBuffer(buffer);
	}
}
