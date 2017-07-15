package org.stiazla.jnews;

import java.util.ArrayList;
import java.util.List;

import org.stiazla.jnews.data.NewsConfig;
import org.stiazla.jnews.interfaces.NewsDataSource;

public class NewsReader implements Runnable{

	private int interval;
	private boolean running = true;
	private NewsDataSource database;
	private List<String> keywords;
	
	public NewsReader(NewsDataSource database, NewsConfig config)
	{
		this.database = database;
		this.interval = config.getInterval();
		this.keywords = config.getKeywords();		
	}
	
	@Override
	public void run() {
		
		while(running)
		{
			try
			{			
				List<String> articleInfos = database.readArticleInfos();
				articleInfos = filterArticles(articleInfos);
				printArticles(articleInfos);
				
				Thread.sleep(interval);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
	
	private void printArticles(List<String> articleInfos) {
		for(String articleInfo : articleInfos)
		{
			System.out.println(articleInfo);
		}		
	}

	private List<String> filterArticles(List<String> articleInfos) {
		
		if(keywords == null || keywords.size() == 0)
			return articleInfos;
		
		List<String> filteredResult = new ArrayList<String>();
		
		for(String articleInfo : articleInfos)
		{
			for(String keyword : keywords){
				//Check if article contains at least one of the keywords (ignore case)
				if(articleInfo.toLowerCase().contains(keyword.toLowerCase())){
					filteredResult.add(articleInfo);
					break;
				}
			}
		}
		return filteredResult;
	}

	public void stop()
	{
		this.running = false;
	}
}
