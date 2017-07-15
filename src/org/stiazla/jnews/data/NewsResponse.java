package org.stiazla.jnews.data;

import java.util.List;

public class NewsResponse {

	private String status;
	private String source;
	private String sortBy;
	private List<NewsArticle> articles;
	
	public String getStatus(){ return status; }
	public String getSource(){ return source; }
	public String getSortBy(){ return sortBy; }
	public List<NewsArticle> getArticles(){ return articles; }
}
