package com.DkGroup.jobFinder.utils;

import com.DkGroup.jobFinder.entity.Client;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserTokenWrapper {
	@JsonProperty("token")
	private String token;
	@JsonProperty("data")
	private Client data;
	public UserTokenWrapper(String token, Client data) {
		super();
		this.token = token;
		this.data = data;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Client getUser() {
		return data;
	}
	public void setUser(Client data) {
		this.data = data;
	}
	
	
}
