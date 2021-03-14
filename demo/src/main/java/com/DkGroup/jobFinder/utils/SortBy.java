package com.DkGroup.jobFinder.utils;

public class SortBy {
	private String attribute;
	private boolean isAscending;
	public SortBy() {}
	
	public SortBy(String attribute, boolean isAscending) {
		super();
		this.attribute = attribute;
		this.isAscending = isAscending;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public void setAscending(boolean isAscending) {
		this.isAscending = isAscending;
	}

	@Override
	public String toString() {
		return "sortBy [attribute=" + attribute + ", isAscending=" + isAscending + "]";
	}
	
	
	
}
