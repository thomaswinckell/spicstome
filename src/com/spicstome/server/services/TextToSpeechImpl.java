package com.spicstome.server.services;

import java.io.BufferedReader;
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
	
	private static String SERVICE_PROTOCOLE = "http://";
	private static String SERVICE_URL = "translate.google.com/translate_tts";
	private static String SERVICE_PARAMS = "?ie=UTF-8&tl=fr";

	@Override  
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException   {

		String query = request.getParameter("q");
		
		response.setHeader("Content-Type", "audio/mpeg");
		
		InputStream input = null;
		PrintWriter writer = null;

		/*try
		{*/
		
			char[] buffer = new char[1024];
			int read;
			
			//String stringUrl = SERVICE_PROTOCOLE+SERVICE_URL+SERVICE_PARAMS+"&q="+query;
			String stringUrl = "http://winckell.com/test.mp3";
			
			System.out.println(stringUrl);
			
			HttpURLConnection con = (HttpURLConnection) new URL(stringUrl).openConnection();
			con.setRequestMethod("GET");
			//con.getOutputStream().write("LOGIN".getBytes("UTF-8"));
			
			//URL url = new URL(stringUrl);
			
			BufferedReader reader = null;			
			
			writer = response.getWriter();

			try {
			    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));//, "UTF-8"));
			    
			    while ((read = reader.read(buffer)) > 0)
			    	writer.write(buffer, 0, read);
			    
			    writer.flush();
			    
			    System.out.println("ok");

			    /*for (String line; (line = reader.readLine()) != null;) {
			        System.out.println(line);
			    }*/
			} finally {
			    if (reader != null) try { reader.close(); } catch (IOException ignore) {  }
			}
			
			
			/*URLConnection connection = url.openConnection();
			int fileLength = connection.getContentLength();

			if (fileLength == -1)
			{
				System.out.println("Invalide URL or file.");
				return;
			}

			writer = response.getWriter();
			
			input = connection.getInputStream();*/
			//String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
			//writeFile = new FileOutputStream(fileName);
			/*char[] buffer = new char[1024];
			int read;
			
			File f = new File(url.getFile());
			if (f.length() == 0L)
			{
				System.out.println("Invalide URL or file.");
				return;
			}
			
			try(BufferedReader br = new BufferedReader(new FileReader(f))) {
				
				System.out.println("ok");
				
				while ((read = br.read(buffer)) > 0)
					writer.write(buffer, 0, read);
				
				writer.flush();
		    }*/

			/*while ((read = input.read(buffer)) > 0)
				writer.write(bytesToStringUTFNIO(buffer), 0, read);
				//writeFile.write(buffer, 0, read);
			//writeFile.flush();
			writer.flush();*/
		/*}
		catch (IOException e)
		{
			System.out.println("Error while trying to download the file.");
			e.printStackTrace();
		}*/
		/*finally
		{
			try
			{
				//writer.close();
				//writeFile.close();
				//input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}*/
	}
	
	public static String bytesToStringUTFNIO(byte[] bytes) {
		CharBuffer cBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
		return cBuffer.toString();
	}
}