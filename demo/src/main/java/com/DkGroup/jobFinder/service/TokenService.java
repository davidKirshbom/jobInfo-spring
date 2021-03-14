package com.DkGroup.jobFinder.service;

import com.DkGroup.jobFinder.entity.Token;
import com.DkGroup.jobFinder.entity.TokenId;

public interface TokenService {

	public Token find(TokenId tokenId);
	public void save(Token token);
}
