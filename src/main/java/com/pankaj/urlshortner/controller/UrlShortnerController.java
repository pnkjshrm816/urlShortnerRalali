package com.pankaj.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.urlshortner.domains.CreateShortUrlRequest;
import com.pankaj.urlshortner.domains.CreateShortUrlResponse;
import com.pankaj.urlshortner.domains.ShortCodeStatsResponse;
import com.pankaj.urlshortner.exceptions.BadRequestException;
import com.pankaj.urlshortner.service.UrlShortnerService;


@RestController
public class UrlShortnerController {
	
	@Autowired
	private UrlShortnerService urlShortnerService;

	@RequestMapping(value = "/shorten", method = { RequestMethod.POST })
	public ResponseEntity<CreateShortUrlResponse> createShortUrl(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
		if(StringUtils.isEmpty(createShortUrlRequest.getUrl())) {
			throw new BadRequestException("url is not present");
		}
		return new ResponseEntity<>(urlShortnerService.generateShortUrl(createShortUrlRequest), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{shortcode}", method = { RequestMethod.GET })
	public ResponseEntity<String> getUrl(@PathVariable("shortcode") String shortcode) {
		return new ResponseEntity<>(urlShortnerService.getEncodedUrl(shortcode), HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/{shortcode}/stats", method = { RequestMethod.GET })
	public ResponseEntity<ShortCodeStatsResponse> getStats(@PathVariable("shortcode") String shortcode) {
		return new ResponseEntity<>(urlShortnerService.getStats(shortcode), HttpStatus.OK);
	}
}
