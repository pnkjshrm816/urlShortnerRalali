package com.pankaj.urlshortner.helper;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pankaj.urlshortner.domains.ShortUrlMetaData;

@Component
public class UrlShortnerControllerHelper {
	private Map<String, ShortUrlMetaData> shortUrlMap = new HashMap<String, ShortUrlMetaData>();
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	static final Integer len = 6;
	
	public boolean isShortCodeExist(String shortcode) {
		if(shortUrlMap.get(shortcode) != null) {
			return true;
		}
		return false;
	}
	
	public void saveShortcodeAndUrl(String shortCode, String url) {
		ShortUrlMetaData shortUrlMetaData = new ShortUrlMetaData();
		shortUrlMetaData.setUrl(url);
		shortUrlMetaData.setCreatedAt(LocalDateTime.now());
		shortUrlMetaData.setUpdatedAt(LocalDateTime.now());
		shortUrlMap.put(shortCode, shortUrlMetaData);
	}
	
	public String generateShortCode() {
		StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
	}
	
	public ShortUrlMetaData getUrl(String shortcode) {
		return shortUrlMap.get(shortcode);
	}
	
	public void updateMetaData(ShortUrlMetaData shortUrlMetaData) {
		shortUrlMetaData.setUpdatedAt(LocalDateTime.now());
		shortUrlMetaData.setRedirects(shortUrlMetaData.getRedirects()+1);
	}
}
