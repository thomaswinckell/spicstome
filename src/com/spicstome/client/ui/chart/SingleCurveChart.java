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
    
    public void drawChart() {
    	
    	drawPane.erase();
    	
        // draw x
    	drawLinePath(margin, drawPane.getHeight() - margin - padding, 
    			drawPane.getWidth() - padding, drawPane.getHeight() - margin - padding);
    	
    	// draw y
    	drawLinePath(margin + padding, drawPane.getHeight() - margin, margin + padding, margin);
    	
    	for (Curve c : curves)
    		drawCurve(c);

        drawPane.redraw();
    }
}