package com.spicstome.client.ui;


import java.util.ArrayList;

import com.smartgwt.client.widgets.Label;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.ui.chart.Curve;
import com.spicstome.client.ui.chart.Point2D;
import com.spicstome.client.ui.chart.SingleCurveChart;
import com.spicstome.client.ui.widget.Crumb;

public class HistoryViewImpl extends UserViewImpl  implements HistoryView{
	
	
	Crumb crumb;
	Label labelTitle = new Label();
	Label labelNbMails = new Label();
	Label labelAverageMessageLength = new Label();
	Label labelAverageExecutionTime = new Label();

	SingleCurveChart singleCurveChart;
	
	public HistoryViewImpl()
	{
		
		super();
		
		addCrumb(new Crumb("Les historiques"){
			@Override
			public void onClickCrumb() {			
				goTo(new HistoryManagementPlace());
			}
		});
		
		crumb = new Crumb(""){
			@Override
			public void onClickCrumb() {}
		};
		
		addCrumb(crumb);

		
		mainPanel.addMember(labelTitle);
		mainPanel.addMember(labelNbMails);
		mainPanel.addMember(labelAverageMessageLength);
		mainPanel.addMember(labelAverageExecutionTime);

		singleCurveChart = new SingleCurveChart(500,500,50,50);

		mainPanel.addMember(singleCurveChart);
	}
	
	

	@Override
	public void setStudent(StudentDTO student){
		
		String title = "Historique de "+student.getFirstName();
		crumb.setCrumbTitle(title);
		labelTitle.setContents(title);
		labelNbMails.setContents("Nombre de mail envoyés: "+student.getLogs().size());
		
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		
		coords.add(new Point2D(5.0,5.0));
		coords.add(new Point2D(10.0,10.0));
		
		singleCurveChart.addCurve(new Curve(coords, "x", "y", "red"));
		
		ArrayList<Point2D> coords2 = new ArrayList<Point2D>();
		
		coords2.add(new Point2D(0.0,10.0));
		coords2.add(new Point2D(5.0,5.0));
		
		singleCurveChart.addCurve(new Curve(coords2, "x", "y", "yellow"));
		
		singleCurveChart.drawChart();
	}

	@Override
	public void setAverageMessageLength(double d) {
		
		labelAverageMessageLength.setContents("Longueur moyenne des messages: "+d);
		
	}

	@Override
	public void setAverageExecutionTime(double d) {
		
		int minute = (int) (d/60);
		int seconds = (int) (d-(minute*60));
		
		labelAverageExecutionTime.setContents("Temps moyen d'écriture d'un message: "+minute+":"+seconds);
		
	}
}
