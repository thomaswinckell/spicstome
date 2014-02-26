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
import com.spicstome.client.ui.chart.PieChart;
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
	HLayout horizontalLayout3 = new HLayout();
	VLayout verticalLayout = new VLayout();
	VLayout firstResults = new VLayout();
	
	HLayout nbMailLayout = new HLayout();
	HLayout averageMessageLengthLayout = new HLayout();
	HLayout averageExecutionTimeLayout = new HLayout();
	
	SingleCurveChart singleCurveChartCountMail;
	SingleCurveChart singleCurveChartMessageLength;
	SingleCurveChart singleCurveChartExecutionTime;
	PieChart pieChart;
	
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
		singleCurveChartCountMail = new SingleCurveChart(1000,500,100,50, "semaine", "mails envoyés");
		
		firstResults.setStyleName("bloc");
		labelTitle.setAlign(Alignment.CENTER);
			
		firstResults.addMember(nbMailLayout);
		firstResults.addMember(averageExecutionTimeLayout);
		firstResults.addMember(averageMessageLengthLayout);
		
		verticalLayout.addMember(labelTitle);
		verticalLayout.addMember(firstResults);

		horizontalLayout.setStyleName("bloc");
		horizontalLayout2.setStyleName("bloc");
		horizontalLayout3.setStyleName("bloc");
		
		horizontalLayout.addMember(verticalLayout);
		horizontalLayout.addMember(singleCurveChartCountMail);
		
		/* Test pie chart */
		
		
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<String> colors = new ArrayList<String>();

		colors.add("grey");
		colors.add("red");
		colors.add("orange");
		colors.add("green");
		colors.add("blue");
		
		strings.add("1 mot");
		strings.add("2 mots");
		strings.add("3 mots");
		strings.add("4 mots");
		strings.add("5 mots et plus");
		
		singleCurveChartMessageLength = new SingleCurveChart(600,400,100,50, "semaine", "longueur moyenne des mails");
		pieChart = new PieChart(100, null, strings, colors, "Répartition des messages par longueur", 300);
	
		horizontalLayout2.addMember(singleCurveChartMessageLength);
		horizontalLayout2.addMember(pieChart);
		
		singleCurveChartExecutionTime = new SingleCurveChart(600,400,100,50, "semaine", "temps moyen d'écriture");
		
		horizontalLayout3.addMember(singleCurveChartExecutionTime);
		
		mainPanel.addMember(horizontalLayout);
		mainPanel.addMember(horizontalLayout2);
		mainPanel.addMember(horizontalLayout3);
	}
	
	

	@Override
	public void setStudent(StudentDTO student){
		
		String title = "Historique général de "+student.getFirstName();
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

	public void setDataInWeekIntoChart(ArrayList<Point2D> list,SingleCurveChart chart)
	{
		ArrayList<Point2D> coords = new ArrayList<Point2D>();
		ArrayList<String> libelleX = new ArrayList<String>();
		chart.clearCurves();
		
		for(int x=0;x<list.size();x++)
		{
			coords.add(new Point2D(x,list.get(x).y));
			libelleX.add(String.valueOf((int)list.get(x).x));
		}

		chart.addCurve(new Curve(coords,libelleX, "x", "y", "red"));

		chart.drawChart();
	}
	
	@Override
	public void setNbMailPerWeek(ArrayList<Point2D> list) {
		
		setDataInWeekIntoChart(list, singleCurveChartCountMail);
		
	}



	@Override
	public void setMessageLengthPerWeek(ArrayList<Point2D> list) {
		
		setDataInWeekIntoChart(list, singleCurveChartMessageLength);
		
	}



	@Override
	public void setExecutionTimePerWeek(ArrayList<Point2D> list) {
		
		setDataInWeekIntoChart(list, singleCurveChartExecutionTime);
		
		
	}



	@Override
	public void setPartitionMessageLength(ArrayList<Double> list) {

		pieChart.setPercents(list);
		pieChart.drawChart();
		
	}
}
