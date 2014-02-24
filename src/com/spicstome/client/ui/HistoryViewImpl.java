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

		singleCurveChart = new SingleCurveChart(500,500,50,50, "aaaaaaaaaaaaaaa", "bbbbbbbbbbbbb");

		mainPanel.addMember(singleCurveChart);
	}
	
	

	@Override
	public void setStudent(StudentDTO student){
		
		String title = "Historique de "+student.getFirstName();
		crumb.setCrumbTitle(title);
		labelTitle.setContents(title);
		labelNbMails.setContents("Nombre de mail envoyés: "+student.getLogs().size());
		
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		
		coords.add(new Point2D(0.0,0.0));
		coords.add(new Point2D(1000.0,1000.0));
		coords.add(new Point2D(2000.0,0.0));
		coords.add(new Point2D(3000.0,1000.0));
		coords.add(new Point2D(4000.0,2000.0));
		coords.add(new Point2D(5000.0,1000.0));
		coords.add(new Point2D(6000.0,3000.0));
		
		singleCurveChart.addCurve(new Curve(coords, "red"));
		
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
