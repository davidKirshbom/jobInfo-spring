package com.DkGroup.jobFinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DkGroup.jobFinder.entity.Token;
import com.DkGroup.jobFinder.entity.TokenId;

public interface TokenRepository extends JpaRepository<Token, TokenId> {

}
