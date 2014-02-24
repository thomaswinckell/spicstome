package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.StudentDTO;


public interface HistoryView extends IsWidget{

	void setStudent(StudentDTO student);
	void setAverageMessageLength(double d);
	void setAverageExecutionTime(double d);
	void setNbMailPerWeek(ArrayList<Integer> list);
}
