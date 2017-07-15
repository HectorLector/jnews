package org.stiazla.jnews.data;

import java.util.List;

public class NewsConfig {

    private String apiKey;
    private List<String> sources;
    private List<String> keywords;
    private int interval;

    public String getApiKey(){ 
    	return apiKey; 
    }
    
    public List<String> getSources(){ 
    	return sources; 
    }
    
    public List<String> getKeywords(){ 
    	return keywords; 
    }
    
    public int getInterval(){ 
    	return interval; 
    }
}
