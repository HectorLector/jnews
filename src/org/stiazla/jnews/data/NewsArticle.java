package org.stiazla.jnews.data;

public class NewsArticle {

	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	
	public String getAuthor(){ return author; }
	public String getTitle(){ return title; }
	public String getDescription(){ return description; }
	public String getUrl(){ return url; }
	public String getUrlToImage(){ return urlToImage; }
	public String getPublishedAt(){ return publishedAt; }
	
	@Override
	public String toString(){ return publishedAt + " " + title; }
}
