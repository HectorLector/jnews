package org.stiazla.jnews.interfaces;

import java.util.List;

import org.stiazla.jnews.data.NewsArticle;

public interface NewsDataSource {

	public void init();
	public void saveArticle(NewsArticle article);
	public List<String> readArticleInfos();
}
