package com.spicstome.client.ui.chart;

import java.util.ArrayList;

import com.spicstome.client.dto.Point2D;

public class Curve {
	
	private ArrayList<Point2D> coords;
	private ArrayList<String> libelleX;
	private String stringX, stringY;
	private String color;
	
	public Curve(ArrayList<Point2D> coords,ArrayList<String> libelleX, String stringX, String stringY, String color) {
		this.coords = coords;
		this.libelleX=libelleX;
		this.stringX = stringX;
		this.stringY = stringY;
		this.color = color;
	}

	public ArrayList<Point2D> getCoords() {
		return coords;
	}

	public String getStringX() {
		return stringX;
	}

	public String getStringY() {
		return stringY;
	}
	
	public String getColor() {
		return color;
	}
	
	public double getMaxX() {
    	double max;
    	
    	if (coords.isEmpty())
    		return 0;
    	
    	max = coords.get(0).x;
    	
    	for(Point2D d : coords)
    		if(d.x > max)
    			max = d.x;
    	
    	return max;
    }
    
	public double getMaxY() {
    	double max;
    	
    	if (coords.isEmpty())
    		return 0;
    	
    	max = coords.get(0).y;
    	
    	for(Point2D d : coords)
    		if(d.y > max)
    			max = d.y;
    	
    	return max;
    }

	public ArrayList<String> getLibelleX() {
		return libelleX;
	}


	@Override
	public String toString() {
		return "Curve [coords=" + coords + ", stringX=" + stringX
				+ ", stringY=" + stringY + "]";
	}	
}
