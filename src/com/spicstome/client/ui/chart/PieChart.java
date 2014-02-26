package com.spicstome.client.ui.chart;

import java.util.ArrayList;

import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.DrawSector;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.layout.HLayout;

public class PieChart extends HLayout {

	private DrawPane drawPane;
	private ArrayList<Double> percents;
	private ArrayList<String> strings, colors;
	private String label;
	private static int PADDING_TOP = 30, MARGIN = 5, SPACE_BETWEEN_LEGEND_AND_CHART = 20, 
			LEGEND_ITEM_HEIGHT = 50, LEGEND_RECT_HEIGHT = 40, LEGEND_RECT_WIDTH = 80;
	private int radius, legendWidth;
	
	public PieChart(int radius, ArrayList<Double> percents, ArrayList<String> strings, 
			ArrayList<String> colors, String label, int legendWidth) {
		
		super();
		
		this.percents = percents;
		this.strings = strings;
		this.colors = colors;
		this.label = label;
		this.radius = radius;
		this.legendWidth = legendWidth;
		
		drawPane = new DrawPane();
    	drawPane.setHeight(radius*2+PADDING_TOP+MARGIN*2);
        drawPane.setWidth(radius*2+legendWidth+MARGIN*2+SPACE_BETWEEN_LEGEND_AND_CHART);
    	addMember(drawPane);
	}
	
	public void setPercents(ArrayList<Double> percents)
	{
		this.percents=percents;
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
	
	private void drawSector(int startAngle, int endAngle, String color) {
		
		DrawSector drawSector = new DrawSector();
		drawSector.setDrawPane(drawPane);
		drawSector.setCenterPoint(new Point(radius+MARGIN, radius+PADDING_TOP+MARGIN));
		drawSector.setRadius(radius);
		drawSector.setStartAngle(startAngle);
		drawSector.setEndAngle(endAngle);
    	drawSector.setFillColor(color);
    	drawSector.draw();
	}
	
	private void drawRect(int left, int top, int width, int height, String color) {
    	DrawRect drawRect = new DrawRect();
    	drawRect.setDrawPane(drawPane);
    	drawRect.setLeft(left);
    	drawRect.setTop(top);
    	drawRect.setWidth(width);
    	drawRect.setHeight(height);
    	drawRect.setFillColor(color);
    	drawRect.draw();
    }
	
	private void drawStrings() {
		
		int left = drawPane.getWidth() - legendWidth - MARGIN;
		int top = PADDING_TOP;
		
		for(int i = 0; i < strings.size(); i++) {
    		
			drawRect(left, top, LEGEND_RECT_WIDTH, LEGEND_RECT_HEIGHT, colors.get(i));
			drawLabel(left+LEGEND_RECT_WIDTH+5, top + LEGEND_RECT_HEIGHT/2 - 8, strings.get(i) + " (" + percents.get(i) + " %)", "start");
			
    		top += LEGEND_ITEM_HEIGHT;
    	}
	}
	
	public void drawChart() {
    	
    	drawPane.erase();
    	
    	drawLabel(radius+MARGIN, MARGIN, label, "center");
    	
    	int startAngle = 0, endAngle = 0;
    	
    	for(int i = 0; i < percents.size(); i++) {
    		
    		endAngle = startAngle + (int) (percents.get(i) / 100 * 360);
    		
    		drawSector(startAngle,endAngle,colors.get(i));
    		
    		startAngle = endAngle;
    	}
    	
    	drawStrings();
    		
        drawPane.redraw();
    }
}
