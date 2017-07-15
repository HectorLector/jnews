package org.stiazla.jnews;

import org.stiazla.jnews.data.NewsArticle;
import org.stiazla.jnews.data.NewsResponse;
import org.stiazla.jnews.interfaces.NewsDataSource;
import org.stiazla.jnews.interfaces.NewsProvider;

public class NewsCollector implements Runnable{

	private NewsDataSource database;
	private NewsProvider newsProvider;
	private int interval;
	private boolean running = true;
	
	public NewsCollector(NewsProvider newsProvider, NewsDataSource database, int interval)
	{
		this.newsProvider = newsProvider;
		this.database = database;
		this.interval = interval;
	}
	
	@Override
	public void run() {
		
		while(running)
		{
			try
			{
				NewsResponse news = newsProvider.getNews();

				//Handle news articles
				saveArticles(news);
				
				Thread.sleep(interval);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}

	public void stop()
	{
		this.running = false;
	}
	
	private void saveArticles(NewsResponse response)
	{
		for(NewsArticle article : response.getArticles()){		
			database.saveArticle(article);
		}
	}
}
