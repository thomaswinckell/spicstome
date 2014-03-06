package com.spicstome.client.dto;

import java.io.Serializable;

public class Point2D implements Serializable{

	private static final long serialVersionUID = 7216352410019645915L;
	
	public double x, y;
	
	public Point2D() {
		x = 0;
		y = 0;
	}
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}
}
