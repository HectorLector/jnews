package org.stiazla.jnews;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.stiazla.jnews.data.NewsConfig;
import org.stiazla.jnews.interfaces.NewsDataSource;
import org.stiazla.jnews.interfaces.NewsProvider;

public class NewsManager {

	private NewsConfig config;
	private List<NewsCollector> newsCollectors;
	private NewsReader newsReader;
	
	public NewsManager(NewsConfig config)
	{
		this.config = config;
	}
	
	public void init()
	{
		NewsDataSource newsDatabase = new NewsDatabase();
		newsDatabase.init();
		
		int interval = config.getInterval();
		String apiKey = config.getApiKey();
		
		newsReader = new NewsReader(newsDatabase, config);
		newsCollectors = new ArrayList<NewsCollector>();
			
		for(String source : config.getSources()){
			NewsProvider newsProvider = new NewsWebProvider(source, apiKey);
			newsCollectors.add(new NewsCollector(newsProvider, newsDatabase, interval));
		}
	}
	
	public void start()
	{
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		//Read web news threads
		for(NewsCollector newsCollector : newsCollectors){
			executorService.execute(newsCollector);
		}
		
		//Read news from database thread
		executorService.execute(newsReader);
	}
	
}

