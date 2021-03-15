package com.DkGroup.jobFinder.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchObj {
	@JsonProperty("sortBy")
	private SortBy sortBy;
	@JsonProperty("searchWord")
	private String searchWords;
	@JsonProperty("filters")
	private Fillter fillters;
	@JsonProperty("isSenorSearch")
	private boolean isSenorSearch;
	@JsonProperty("openJobsOnly")
	private boolean isOpenJobsOnly;
	@JsonProperty("dateLimits")
	private boolean isDateLimit;
	@JsonProperty("resultsLimit")
	private int resultLimit;
	@JsonProperty("resultsOffset")
	private int resultOffset;
	
	public SearchObj() {
		super();
	}

	public SearchObj(SortBy sortBy, String searchWords, Fillter fillters, boolean isSenorSearch, boolean isOpenJobsOnly,
			boolean isDateLimit, int resultLimit, int resultOffset) {
		super();
		this.sortBy = sortBy;
		this.searchWords = searchWords;
		this.fillters = fillters;
		this.isSenorSearch = isSenorSearch;
		this.isOpenJobsOnly = isOpenJobsOnly;
		this.isDateLimit = isDateLimit;
		this.resultLimit = resultLimit;
		this.resultOffset = resultOffset;
		System.out.println(this);
	}

	public SortBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}

	public String getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}

	public Fillter getFillters() {
		return fillters;
	}

	public void setFillters(Fillter fillters) {
		this.fillters = fillters;
	}

	public boolean isSenorSearch() {
		return isSenorSearch;
	}

	public void setSenorSearch(boolean isSenorSearch) {
		this.isSenorSearch = isSenorSearch;
	}

	public boolean isOpenJobsOnly() {
		return isOpenJobsOnly;
	}

	public void setOpenJobsOnly(boolean isOpenJobsOnly) {
		this.isOpenJobsOnly = isOpenJobsOnly;
	}

	public boolean isDateLimit() {
		return isDateLimit;
	}

	public void setDateLimit(boolean isDateLimit) {
		this.isDateLimit = isDateLimit;
	}

	public int getResultLimit() {
		return resultLimit;
	}

	public void setResultLimit(int resultLimit) {
		this.resultLimit = resultLimit;
	}

	public int getResultOffset() {
		return resultOffset;
	}

	public void setResultOffset(int resultOffset) {
		this.resultOffset = resultOffset;
	}

	@Override
	public String toString() {
		return "SearchObj [sortBy=" + sortBy + ", searchWords=" + searchWords + ", fillters=" + fillters
				+ ", isSenorSearch=" + isSenorSearch + ", isOpenJobsOnly=" + isOpenJobsOnly + ", isDateLimit="
				+ isDateLimit + ", resultLimit=" + resultLimit + ", resultOffset=" + resultOffset + "]";
	}
	
	
}
