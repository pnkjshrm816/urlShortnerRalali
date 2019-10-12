package com.pankaj.urlshortner.domains;

public class CreateShortUrlResponse {
	private String shortcode;

	public CreateShortUrlResponse() {
		super();
	}

	public CreateShortUrlResponse(String shortcode) {
		super();
		this.shortcode = shortcode;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
}
