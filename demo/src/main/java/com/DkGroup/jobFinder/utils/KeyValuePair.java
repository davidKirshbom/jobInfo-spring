package com.DkGroup.jobFinder.utils;

import java.math.BigInteger;

public class KeyValuePair {
	private BigInteger id;
	private String name;
	public KeyValuePair(BigInteger id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
