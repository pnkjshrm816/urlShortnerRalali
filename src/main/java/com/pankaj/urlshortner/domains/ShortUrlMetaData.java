package com.pankaj.urlshortner.domains;

import java.time.LocalDateTime;

public class ShortUrlMetaData {
	private String url;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Integer redirects = 0;
	public ShortUrlMetaData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShortUrlMetaData(String url, LocalDateTime createdAt, LocalDateTime updatedAt, Integer redirects) {
		super();
		this.url = url;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.redirects = redirects;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getRedirects() {
		return redirects;
	}
	public void setRedirects(Integer redirects) {
		this.redirects = redirects;
	}
}
