package com.spicstome.server.services;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextToSpeechImpl extends HttpServlet {

	private static final long serialVersionUID = 7971828116206411598L;
	
    private static final String TEXT_TO_SPEECH_SERVICE = 
            "http://translate.google.com/translate_tts";
    private static final String USER_AGENT =  
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:11.0) " +
            "Gecko/20100101 Firefox/11.0";

    @Override  
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException   {
        
    	response.setHeader("Content-Type", "audio/mpeg");
    	
    	// Create url based on input params
        String strUrl = TEXT_TO_SPEECH_SERVICE + "?" + 
                "tl=" + "fr" + "&q=" + request.getParameter("q").replace(" ", "%20");
        
        System.out.println(strUrl);
        
        URL url = new URL(strUrl);

        // Etablish connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Get method
        connection.setRequestMethod("GET");
        // Set User-Agent to "mimic" the behavior of a web browser. In this 
        // example, I used my browser's info
        connection.addRequestProperty("User-Agent", USER_AGENT);
        connection.connect();

        // Get content
        BufferedInputStream bufIn = 
                new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[1024];
        int n;
        ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
        while ((n = bufIn.read(buffer)) > 0) {
            bufOut.write(buffer, 0, n);
        }		
		
        ServletOutputStream writer = response.getOutputStream();

		writer.write(bufOut.toByteArray());
		
		writer.flush();
		writer.close();
		
        System.out.println("Done");
    }
}