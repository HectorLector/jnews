package org.stiazla.jnews;

import java.io.File;

import org.stiazla.jnews.data.NewsConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	/**
	 * @author David Seywald
	 * d.seywald@gmail.com
	 * Powered by NewsApi: newsapi.org/
	 */
	public static void main(String[] args) {
		
		if(args == null || args.length != 1)
		{
			System.out.println("Usage: jnews <pathToConfigJson>");
		}
		else
		{
		    try {
				String pathConfig = args[0].trim();
			    ObjectMapper mapper = new ObjectMapper();
		    	
				NewsConfig config = mapper.readValue(new File(pathConfig), NewsConfig.class);
				
				NewsManager manager = new NewsManager(config);
				manager.init();
				manager.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
