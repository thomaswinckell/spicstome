package com.spicstome.client.ui.widget;

import com.googlecode.gchart.client.GChart;

public class MyChart extends GChart {
	public MyChart() {
	     setChartTitle("<b>x<sup>2</sup> vs x</b>");
	     setChartSize(150, 150);
	     addCurve();
	// solid, 2px thick, 1px resolution, connecting lines:
	     getCurve().getSymbol().setSymbolType(SymbolType.LINE);
	     getCurve().getSymbol().setFillThickness(2);
	     getCurve().getSymbol().setFillSpacing(1);
	// Make center-fill of square point markers same color as line:
	     getCurve().getSymbol().setBackgroundColor(
	    		 getCurve().getSymbol().getBorderColor());
	     for (int i = 0; i < 10; i++) 
	       getCurve().addPoint(i,i*i);
	     getCurve().setLegendLabel("x<sup>2</sup>");
	     getXAxis().setAxisLabel("x");
	     getYAxis().setAxisLabel("x<sup>2</sup>");
	  }
	}
