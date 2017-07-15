package org.stiazla.jnews;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.stiazla.jnews.data.NewsArticle;
import org.stiazla.jnews.interfaces.NewsDataSource;

public class NewsDatabase implements NewsDataSource{

	private static final String TABLE_NAME = "[article]";
	private static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	private static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY ASC, title TEXT NULL, publishedAt TEXT NULL);";
	private static final String SQL_SAVE = "INSERT INTO " + TABLE_NAME + "(title, publishedAt) VALUES(?,?);";
	private static final String SQL_READ = "SELECT * FROM " + TABLE_NAME + " WHERE ID > ?;";
	private static final String SQL_UNIQUE = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE title = ? AND publishedAt = ?;";
	
	private Connection connection;
	private int lastReadArticleId = 0;
	
	public void init()
	{
		connect();
		resetDatabase();
	}
	
	private void connect()
	{ 
	      try {
	         Class.forName("org.sqlite.JDBC");
	         connection = DriverManager.getConnection("jdbc:sqlite:news.db");
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}
	
	private void resetDatabase()
	{
		try(Statement stmt = connection.createStatement()){
			
			stmt.executeUpdate(SQL_DROP);
			stmt.executeUpdate(SQL_CREATE);		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveArticle(NewsArticle article)
	{
		try(PreparedStatement stmt = connection.prepareStatement(SQL_SAVE);
			PreparedStatement check = connection.prepareStatement(SQL_UNIQUE)){
			
			//Check if the article already exists in database
			check.setString(1, article.getTitle());
			check.setString(2, article.getPublishedAt());
			
			int alreadyExists = 0;
			ResultSet rs = check.executeQuery();
		    while(rs.next()){
		    	alreadyExists = rs.getInt(1);
		    }
		    
		    //Only save the article if it is not already stored
		    if(alreadyExists <= 0){
				stmt.setString(1, article.getTitle());
				stmt.setString(2, article.getPublishedAt());
				stmt.executeUpdate();
		    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String> readArticleInfos()
	{
		List<String> articleInfos = new ArrayList<String>();
		try(PreparedStatement stmt = connection.prepareStatement(SQL_READ)){
			
			stmt.setInt(1, lastReadArticleId);
			ResultSet rs = stmt.executeQuery();
			
			StringBuilder sb;
            while (rs.next()) {
            	//Read data
            	int id = rs.getInt("id");
            	String title = rs.getString("title");
            	String publishedAt = rs.getString("publishedAt");
            	
            	//Build article info
            	sb = new StringBuilder();
            	sb.append(publishedAt);
            	sb.append(" ");
            	sb.append(title);
            	
            	articleInfos.add(sb.toString());
            	
            	lastReadArticleId = id;
            }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return articleInfos;
	}
	
}
