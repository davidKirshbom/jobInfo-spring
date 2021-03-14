package com.DkGroup.jobFinder.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tokens")
@IdClass(TokenId.class)
public class Token {
	
	@Id
	@Column(name="user_uid")
	@Type(type="pg-uuid")
	private UUID userUid;
	@Id
	@Column(name="token")
	private String token;
	
	
	public Token() {}


	public Token(UUID userUid, String token) {
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
