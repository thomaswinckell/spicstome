package com.spicstome.client.ui.chart;

import java.util.ArrayList;

import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.shared.Point2D;

public class SingleCurveChart extends HLayout {
    
	private DrawPane drawPane;
	private int margin, padding;
	private ArrayList<Curve> curves;
	private double maxX = 0, maxY = 0;
	private String stringX, stringY;
	private static int ARROW_LENGTH = 5, STEP_HEIGHT = 50, STEP_WIDTH = 50, STEP_LINE_LENGTH = 5; // pixels

    public SingleCurveChart(int width, int height, int margin, int padding, String stringX, String stringY) {
    	
    	super();
    	
    	curves = new ArrayList<Curve>();
    	this.margin = margin;
    	this.padding = padding;
    	this.stringX = stringX;
    	this.stringY = stringY;
    	
    	drawPane = new DrawPane();
    	drawPane.setHeight(height);
        drawPane.setWidth(width);
    	addMember(drawPane);
    }

    
    private void drawLine(Point2D start, Point2D end, String color) {
    	DrawLine drawLine = new DrawLine();
    	drawLine.setLineColor(color);
        drawLine.setDrawPane(drawPane);
        drawLine.setStartPoint(getGlobalCoords(start));
        drawLine.setEndPoint(getGlobalCoords(end));
        drawLine.draw();
    }
    
    private void drawLinePath(int startX, int startY, int endX, int endY) {
    	DrawLine drawLine = new DrawLine();
        drawLine.setDrawPane(drawPane);
        //drawLine.setEndArrow(ArrowStyle.OPEN);
        drawLine.setStartPoint(new Point(startX, startY));
        drawLine.setEndPoint(new Point(endX, endY));
        drawLine.draw();
    }
    
    private void drawLabel(int left, int top, String content, String align) {
    	DrawLabel drawLabel = new DrawLabel();
    	drawLabel.setDrawPane(drawPane);
    	drawLabel.setLeft(left);
    	drawLabel.setAlignment(align);
    	drawLabel.setTop(top); 
    	drawLabel.setContents(content);
        drawLabel.draw();
    }
    
    private int getLocalWidth() {
    	return drawPane.getWidth() - 2 * margin - 2*padding;
    }
    
    private int getLocalHeight() {
    	return drawPane.getHeight() - 2 * margin - 2*padding;
    }
    
    private Point getGlobalCoords(Point2D pLocal) {
    	int x = (int) (pLocal.x * getLocalWidth() / maxX + margin + padding);
    	int y = (int) ((drawPane.getHeight() - margin - padding) - pLocal.y * getLocalHeight() / maxY);    	
    	return new Point(x, y);
    }
    
    public void addCurve(Curve curve) {
    	curves.add(curve);
    }
    
    private void drawCurve(Curve curve) {
    	boolean isFirst = true;
    	for(int i = 0; i < curve.getCoords().size(); i++) {
    		if (isFirst) {
    			isFirst = false;
    		} else {    			
        		drawLine(curve.getCoords().get(i-1), curve.getCoords().get(i), curve.getColor());
    		}
    	}
    }
    
    private void updateMax() {
    	maxX = 0;
    	maxY = 0;
    	
    	for (Curve c : curves) {
        	if (c.getMaxX() > maxX)
        		maxX = c.getMaxX();
        	if (c.getMaxY() > maxY)
        		maxY = c.getMaxY();
    	}
    }
    
    private void drawSteps() {
    	
    	int stepX, stepY, x, y;
    	Point p;
    	
    	if (maxX > 10) 
    		stepX = (int) (maxX / (getLocalWidth() / STEP_HEIGHT));
    	else
    		stepX = 1;
    	
    	if (maxY > 10) 
    		stepY = (int) (maxY / (getLocalHeight() / STEP_WIDTH));
    	else
    		stepY = 1;
    	
    	x = stepX;
    	y = stepY;
    	
    	// drawing X steps
    	while (x <= maxX) {
    		
    		p = getGlobalCoords(new Point2D(x, 0));
    		
    		drawLinePath(p.getX(), p.getY() + STEP_LINE_LENGTH, p.getX(), p.getY() - STEP_LINE_LENGTH);
    		
    		String label = (curves.get(0).getLibelleX()==null?""+x:curves.get(0).getLibelleX().get(x));
    		drawLabel(p.getX(), p.getY() + STEP_LINE_LENGTH*2, label, "center");
    		
    		x += stepX;
    	}
    	
    	// drawing Y steps
    	while (y <= maxY) {
    		
    		p = getGlobalCoords(new Point2D(0, y));
    		
    		drawLinePath(p.getX() + STEP_LINE_LENGTH, p.getY(), p.getX() - STEP_LINE_LENGTH, p.getY());
    		
    		drawLabel(p.getX() - STEP_LINE_LENGTH*2, p.getY() - 8, ""+y, "end");
    		
    		y += stepY;
    	}
    }
    
    private void drawStrings() {
    	
    	// drawing y string    	
    	Point p = getGlobalCoords(new Point2D(0, maxY));    	
    	drawLabel(p.getX(), p.getY() - padding - 40, stringY, "center");
    	
    	// drawing x string  
    	p = getGlobalCoords(new Point2D(maxX, 0));    	
    	drawLabel(p.getX() + padding, p.getY() + 40, stringX, "end");
    }
    
    private void drawChartAxes() {
    	
    	int startX, startY, endX, endY;
    	
    	startX = margin;
    	startY = drawPane.getHeight() - margin - padding;
    	endX = drawPane.getWidth() - margin;
    	endY = drawPane.getHeight() - margin - padding;
    	
    	// draw x
    	drawLinePath(startX, startY, endX, endY);
    	
    	// draw arrow
    	drawLinePath(endX, endY, endX - ARROW_LENGTH, endY - ARROW_LENGTH);
    	drawLinePath(endX, endY, endX - ARROW_LENGTH, endY + ARROW_LENGTH);
    	
    	startX = margin + padding;
    	startY = drawPane.getHeight() - margin;
    	endX = margin + padding;
    	endY = margin;
    	
    	// draw y
    	drawLinePath(startX, startY, endX, endY);
    	
    	// draw arrow
    	drawLinePath(endX, endY, endX - ARROW_LENGTH, endY + ARROW_LENGTH);
    	drawLinePath(endX, endY, endX + ARROW_LENGTH, endY + ARROW_LENGTH);
    	
    	// draw 0        
        drawLabel(startX - padding/2, startY - padding + padding/4, "0", "start");
        
        drawSteps();
        
        drawStrings();
    }
    
    public void clearCurves()
    {
    	curves.clear();
    }
    
    public void drawChart() {
    	
    	updateMax();
    	
    	drawPane.erase();
    	
        drawChartAxes();
    	
    	for (Curve c : curves)
    		drawCurve(c);

        drawPane.redraw();
    }

}
