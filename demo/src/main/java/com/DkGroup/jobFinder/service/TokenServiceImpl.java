package com.DkGroup.jobFinder.service;

import org.springframework.stereotype.Service;

import com.DkGroup.jobFinder.dao.TokenRepository;
import com.DkGroup.jobFinder.entity.Token;
import com.DkGroup.jobFinder.entity.TokenId;

@Service
public class TokenServiceImpl implements TokenService {
	private TokenRepository tokenRepository;
	
	public TokenServiceImpl(TokenRepository theTokenRepository) {
		tokenRepository=theTokenRepository;
	}
	@Override
	public Token find(TokenId tokenId) {
		// TODO Auto-generated method stub
		return tokenRepository.getOne(tokenId);
	}

	@Override
	public void save(Token token) {
		// TODO Auto-generated method stub
		tokenRepository.save(token);
	}
	
	

}
