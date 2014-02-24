package com.spicstome.client.ui.chart;

import java.util.ArrayList;

import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.layout.HLayout;

public class SingleCurveChart extends HLayout {
    
	private DrawPane drawPane;
	private int margin, padding;
	private ArrayList<Curve> curves;
	private double maxX = 0, maxY = 0;
	private static int ARROW_LENGTH = 5;

    public SingleCurveChart(int width, int height, int margin, int padding) {
    	
    	super();
    	
    	curves = new ArrayList<Curve>();
    	this.margin = margin;
    	this.padding = padding;
    	
    	drawPane = new DrawPane();
    	drawPane.setHeight(width);
        drawPane.setWidth(height);
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
    
    private int getLocalWidth() {
    	return drawPane.getWidth() - 2 * margin - padding;
    }
    
    private int getLocalHeight() {
    	return drawPane.getHeight() - 2 * margin - padding;
    }
    
    private Point getGlobalCoords(Point2D pLocal) {
    	int x = (int) (pLocal.x * getLocalWidth() / maxX + margin + padding);
    	int y = (int) ((drawPane.getHeight() - margin - padding) - pLocal.y * getLocalHeight() / maxY);    	
    	return new Point(x, y);
    }
    
    public void addCurve(Curve curve) {
    	curves.add(curve);
    	
    	// update MAX
    	if (curve.getMaxX() > maxX)
    		maxX = curve.getMaxX();
    	if (curve.getMaxY() > maxY)
    		maxY = curve.getMaxY();
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
    
    private void drawChartAxes() {
    	
    	int startX, startY, endX, endY;
    	
    	startX = margin;
    	startY = drawPane.getHeight() - margin - padding;
    	endX = drawPane.getWidth() - padding;
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
    	drawLinePath(endX, endY, endX - ARROW_LENGTH, endY - ARROW_LENGTH);
    	drawLinePath(endX, endY, endX + ARROW_LENGTH, endY - ARROW_LENGTH);
    }
    
    public void drawChart() {
    	
    	drawPane.erase();
    	
        drawChartAxes();
    	
    	for (Curve c : curves)
    		drawCurve(c);

        drawPane.redraw();
    }
}