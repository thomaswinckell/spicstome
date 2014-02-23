package com.spicstome.client.ui;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MyChart;

public class HistoryViewImpl extends UserViewImpl  implements HistoryView{
	
	
	Crumb crumb;
	Label labelTitle = new Label();
	Label labelNbMails = new Label();
	Label labelAverageMessageLength = new Label();
	Label labelAverageExecutionTime = new Label();
	MyChart chart;
	
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

	
	   
	   
	    Canvas canvas = new Canvas();
	    canvas.setWidth(300);
	    canvas.setHeight(300);
	    chart = new MyChart();


	    canvas.addChild(chart);
	
		
		mainPanel.addMember(labelTitle);
		mainPanel.addMember(labelNbMails);
		mainPanel.addMember(labelAverageMessageLength);
		mainPanel.addMember(labelAverageExecutionTime);
		mainPanel.addMember(canvas);
		
		chart.update();
		
		
		
	}
	
	

	@Override
	public void setStudent(StudentDTO student){
		
		String title = "Historique de "+student.getFirstName();
		crumb.setCrumbTitle(title);
		labelTitle.setContents(title);
		labelNbMails.setContents("Nombre de mail envoyés: "+student.getLogs().size());
		
		chart.update();
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
