package org.stiazla.jnews;

import java.net.MalformedURLException;
import java.net.URL;

import org.stiazla.jnews.data.NewsResponse;
import org.stiazla.jnews.interfaces.NewsProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsWebProvider implements NewsProvider{

	private URL request;
	public NewsWebProvider(String source, String apiKey)
	{
		try {
			request = new URL(String.format("https://newsapi.org/v1/articles?source=%s&apiKey=%s", source, apiKey));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public NewsResponse getNews() {
		NewsResponse response = null;
		try
		{
			//Map json response from url to POJO
			ObjectMapper mapper = new ObjectMapper();
		    response = mapper.readValue(request, NewsResponse.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

}
