package com.DkGroup.jobFinder.entity;

import java.io.Serializable;
import java.util.UUID;



public class TokenId implements Serializable {
	private UUID userUid;

	
	private String token;

	public TokenId() {}
	public TokenId(UUID userUid, String token) {
		super();
		this.userUid = userUid;
		this.token = token;
	}
	public UUID getUserUid() {
		return userUid;
	}
	public void setUserUid(UUID userUid) {
		this.userUid = userUid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
