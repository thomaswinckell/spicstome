package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.Point2D;
import com.spicstome.client.dto.StudentDTO;


public interface HistoryView extends IsWidget{

	void setStudent(StudentDTO student);
	void setAverageMessageLength(double d);
	void setAverageExecutionTime(double d);
	void setNbMailPerWeek(ArrayList<Point2D> list);
	void setMessageLengthPerWeek(ArrayList<Point2D> list);
	void setExecutionTimePerWeek(ArrayList<Point2D> list);
	void setPartitionMessageLength(ArrayList<Double> list);
	void drawCharts();
}
