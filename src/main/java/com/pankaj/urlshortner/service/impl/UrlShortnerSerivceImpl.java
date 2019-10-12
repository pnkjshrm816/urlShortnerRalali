package com.pankaj.urlshortner.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pankaj.urlshortner.domains.CreateShortUrlRequest;
import com.pankaj.urlshortner.domains.CreateShortUrlResponse;
import com.pankaj.urlshortner.domains.ShortCodeStatsResponse;
import com.pankaj.urlshortner.domains.ShortUrlMetaData;
import com.pankaj.urlshortner.exceptions.ConflictException;
import com.pankaj.urlshortner.exceptions.InternalServerErrorOccuredException;
import com.pankaj.urlshortner.exceptions.NotFoundException;
import com.pankaj.urlshortner.exceptions.UnResponsableEntityException;
import com.pankaj.urlshortner.helper.UrlShortnerControllerHelper;
import com.pankaj.urlshortner.service.UrlShortnerService;

@Service
public class UrlShortnerSerivceImpl implements UrlShortnerService {

	@Autowired
	private UrlShortnerControllerHelper urlShortnerControllerHelper;
	
	private String regex = "^[0-9a-zA-Z_]{4,}$";
	
	@Override
	public CreateShortUrlResponse generateShortUrl(CreateShortUrlRequest createShortUrlRequest) {
		try {
			CreateShortUrlResponse createShortUrlResponse = new CreateShortUrlResponse();
			String shortcode = null;
			if(!StringUtils.isEmpty(createShortUrlRequest.getShortcode())) {
				if(urlShortnerControllerHelper.isShortCodeExist(createShortUrlRequest.getShortcode())) {
					throw new ConflictException("The the desired shortcode is already in use. Shortcodes are case-sensitive.");
				}
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(createShortUrlRequest.getShortcode());
				if(!matcher.matches()) {
					throw new UnResponsableEntityException("The shortcode fails to meet the following regexp: ^[0-9a-zA-Z_]{4,}$.");
				}
				shortcode = createShortUrlRequest.getShortcode();
			} else {
				while(true) {
					shortcode = urlShortnerControllerHelper.generateShortCode();
					if(!urlShortnerControllerHelper.isShortCodeExist(shortcode)) {
						break;
					}
				}
			}
			urlShortnerControllerHelper.saveShortcodeAndUrl(shortcode, createShortUrlRequest.getUrl());
			createShortUrlResponse.setShortcode(shortcode);
			return createShortUrlResponse;
		} catch(ConflictException | UnResponsableEntityException e) {
			throw e;
		} catch(Exception e) {
			throw new InternalServerErrorOccuredException("Some occurred occurred we are working on it, Please try after sometime!");
		}
	}

	@Override
	public String getEncodedUrl(String shortCode) {
		ShortUrlMetaData shortUrlMetaData = urlShortnerControllerHelper.getUrl(shortCode);
		if(shortUrlMetaData == null) {
			throw new NotFoundException("The shortcode cannot be found in the system");
		}
		try {
			urlShortnerControllerHelper.updateMetaData(shortUrlMetaData);
            return URLEncoder.encode(shortUrlMetaData.getUrl(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
	}

	@Override
	public ShortCodeStatsResponse getStats(String shortCode) {
		try {
			ShortUrlMetaData shortUrlMetaData = urlShortnerControllerHelper.getUrl(shortCode);
			if(shortUrlMetaData == null) {
				throw new NotFoundException("The shortcode cannot be found in the system");
			}
			ShortCodeStatsResponse shortCodeStatsResponse = new ShortCodeStatsResponse();
			shortCodeStatsResponse.setLastSeenDate(shortUrlMetaData.getUpdatedAt());
			shortCodeStatsResponse.setStartDate(shortUrlMetaData.getCreatedAt());
			shortCodeStatsResponse.setRedirectCount(shortUrlMetaData.getRedirects());
			return shortCodeStatsResponse;
		} catch(NotFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new InternalServerErrorOccuredException("Some occurred occurred we are working on it, Please try after sometime!");
		}
	}

}
