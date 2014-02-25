package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.shared.Point2D;
import com.spicstome.client.ui.chart.Curve;
import com.spicstome.client.ui.chart.SingleCurveChart;
import com.spicstome.client.ui.widget.Crumb;

public class HistoryViewImpl extends UserViewImpl  implements HistoryView{
	
	
	Crumb crumb;
	Label labelTitle = new Label();
	Label labelNbMails = new Label();
	Label labelAverageMessageLength = new Label();
	Label labelAverageExecutionTime = new Label();
	Label labelResultNbMails = new Label();
	Label labelResultAverageMessageLength = new Label();
	Label labelResultAverageExecutionTime = new Label();
	
	HLayout horizontalLayout = new HLayout();
	HLayout horizontalLayout2 = new HLayout();
	VLayout verticalLayout = new VLayout();
	VLayout firstResults = new VLayout();
	
	HLayout nbMailLayout = new HLayout();
	HLayout averageMessageLengthLayout = new HLayout();
	HLayout averageExecutionTimeLayout = new HLayout();
	
	SingleCurveChart singleCurveChartCountMail;
	SingleCurveChart singleCurveChartMessageLength;
	SingleCurveChart singleCurveChartExecutionTime;
	
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

		labelTitle.setStyleName("title");
		labelNbMails.setStyleName("subTitle");
		labelAverageMessageLength.setStyleName("subTitle");
		labelAverageExecutionTime.setStyleName("subTitle");
		labelResultNbMails.setStyleName("title");
		labelResultAverageMessageLength.setStyleName("title");
		labelResultAverageExecutionTime.setStyleName("title");
		labelNbMails.setWidth100();
		labelAverageMessageLength.setWidth100();
		labelAverageExecutionTime.setWidth100();
		labelResultNbMails.setWidth(50);
		labelResultAverageMessageLength.setWidth(50);
		labelResultAverageExecutionTime.setWidth(50);
		
		
		labelNbMails.setContents("Nombre de mail envoyés: ");
		labelAverageMessageLength.setContents("Longueur moyenne des messages: ");
		labelAverageExecutionTime.setContents("Temps moyen d'écriture d'un message: ");
		
		nbMailLayout.addMember(labelNbMails);
		nbMailLayout.addMember(labelResultNbMails);
		averageExecutionTimeLayout.addMember(labelAverageExecutionTime);
		averageExecutionTimeLayout.addMember(labelResultAverageExecutionTime);
		averageMessageLengthLayout.addMember(labelAverageMessageLength);
		averageMessageLengthLayout.addMember(labelResultAverageMessageLength);
		
		firstResults.setStyleName("bloc");
		labelTitle.setAlign(Alignment.CENTER);
		
		
		firstResults.addMember(nbMailLayout);
		firstResults.addMember(averageExecutionTimeLayout);
		firstResults.addMember(averageMessageLengthLayout);
		
		verticalLayout.addMember(labelTitle);
		verticalLayout.addMember(firstResults);

		singleCurveChartCountMail = new SingleCurveChart(1000,500,50,50, "semaine", "mails envoyés");
		singleCurveChartMessageLength = new SingleCurveChart(600,400,50,50, "semaine", "longueur moyenne des mails");
		singleCurveChartExecutionTime = new SingleCurveChart(600,400,50,50, "semaine", "temps moyen d'écriture");

		horizontalLayout.addMember(verticalLayout);
		horizontalLayout.addMember(singleCurveChartCountMail);
	
		horizontalLayout2.addMember(singleCurveChartMessageLength);
		horizontalLayout2.addMember(singleCurveChartExecutionTime);
		
		mainPanel.addMember(horizontalLayout);
		mainPanel.addMember(horizontalLayout2);
	}
	
	

	@Override
	public void setStudent(StudentDTO student){
		
		String title = "Historique de "+student.getFirstName();
		crumb.setCrumbTitle(title);
		labelTitle.setContents(title);
		labelResultNbMails.setContents(String.valueOf(student.getLogs().size()));
		

	}

	@Override
	public void setAverageMessageLength(double d) {
		
		
		labelResultAverageMessageLength.setContents(NumberFormat.getFormat("#0.00").format(d));
		
	}

	@Override
	public void setAverageExecutionTime(double d) {
		
		int minute = (int) (d/60);
		int seconds = (int) (d-(minute*60));
		
		labelResultAverageExecutionTime.setContents(+minute+":"+seconds);
		
	}

	@Override
	public void setNbMailPerWeek(ArrayList<Point2D> list) {
		
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		
		singleCurveChartCountMail.clearCurves();
		
		for(int x=0;x<list.size();x++)
		{
			coords.add(new Point2D(x,list.get(x).y));
		}
			
		
		
		singleCurveChartCountMail.addCurve(new Curve(coords, "x", "y", "red"));

		singleCurveChartCountMail.drawChart();
		
	}



	@Override
	public void setMessageLengthPerWeek(ArrayList<Point2D> list) {
		
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		
		singleCurveChartMessageLength.clearCurves();
		
		for(int x=0;x<list.size();x++)
		{
			coords.add(new Point2D(x,list.get(x).y));
		}
			
		
		
		singleCurveChartMessageLength.addCurve(new Curve(coords, "x", "y", "red"));

		singleCurveChartMessageLength.drawChart();
		
	}



	@Override
	public void setExecutionTimePerWeek(ArrayList<Point2D> list) {
		
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		
		singleCurveChartExecutionTime.clearCurves();
		
		for(int x=0;x<list.size();x++)
		{
			coords.add(new Point2D(x,list.get(x).y));
		}
			
		
		
		singleCurveChartExecutionTime.addCurve(new Curve(coords, "x", "y", "red"));

		singleCurveChartExecutionTime.drawChart();
		
		
	}
}
