package com.spicstome.server.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextToSpeechImpl extends HttpServlet {

	private static final long serialVersionUID = 7971828116206411598L;
	
	private static final String TEXT_TO_SPEECH_SERVICE = "http://translate.google.com/translate_tts";
    private static final String USER_AGENT =  
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:11.0) " +
            "Gecko/20100101 Firefox/11.0";
    private static final String LANGUAGE = "fr";

	@Override  
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException   {

		String query = request.getParameter("q");
		
		response.setHeader("Content-Type", "audio/mpeg");
		
        // Create url based on input params
        String strUrl = TEXT_TO_SPEECH_SERVICE + "?" + 
                "tl=" + LANGUAGE + "&q=" + query.replace(" ", "%20");
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

        // Done, save data
        BufferedOutputStream out = 
                new BufferedOutputStream(response.getOutputStream());
        out.write(bufOut.toByteArray());
        out.close();
	}
}