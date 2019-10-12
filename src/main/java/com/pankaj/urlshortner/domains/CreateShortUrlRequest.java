package com.pankaj.urlshortner.domains;

public class CreateShortUrlRequest {
	private String url;
	private String shortcode;
	
	public CreateShortUrlRequest() {
		super();
	}
	public CreateShortUrlRequest(String url, String shortcode) {
		super();
		this.url = url;
		this.shortcode = shortcode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
}
