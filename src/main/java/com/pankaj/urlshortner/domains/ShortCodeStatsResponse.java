package com.pankaj.urlshortner.domains;

import java.time.LocalDateTime;

public class ShortCodeStatsResponse {
	private LocalDateTime startDate;
	private LocalDateTime lastSeenDate;
	private Integer redirectCount;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getLastSeenDate() {
		return lastSeenDate;
	}
	public void setLastSeenDate(LocalDateTime lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}
	public Integer getRedirectCount() {
		return redirectCount;
	}
	public void setRedirectCount(Integer redirectCount) {
		this.redirectCount = redirectCount;
	}
	public ShortCodeStatsResponse(LocalDateTime startDate, LocalDateTime lastSeenDate, Integer redirectCount) {
		super();
		this.startDate = startDate;
		this.lastSeenDate = lastSeenDate;
		this.redirectCount = redirectCount;
	}
	public ShortCodeStatsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
