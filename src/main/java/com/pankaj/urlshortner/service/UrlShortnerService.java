package com.pankaj.urlshortner.service;

import com.pankaj.urlshortner.domains.CreateShortUrlRequest;
import com.pankaj.urlshortner.domains.CreateShortUrlResponse;
import com.pankaj.urlshortner.domains.ShortCodeStatsResponse;

public interface UrlShortnerService {

	CreateShortUrlResponse generateShortUrl(CreateShortUrlRequest createShortUrlRequest);
	
	String getEncodedUrl(String shortCode);
	
	ShortCodeStatsResponse getStats(String shortCode);
}
