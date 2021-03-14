package com.DkGroup.jobFinder.service;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.DkGroup.jobFinder.dao.CompanyRepository;
import com.DkGroup.jobFinder.entity.Company;
import com.DkGroup.jobFinder.entity.Token;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Value( "${app.jwt_key}" )
	private String localKey;
	private CompanyRepository companyRepository;
	private TokenService tokenService;
	@Autowired
	public  CompanyServiceImpl(CompanyRepository theCompanyRepository,TokenService theTokenService) {
		// TODO Auto-generated constructor stub
		companyRepository=theCompanyRepository;
		tokenService=theTokenService;
	}
	
	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}
	
	@Override
	public UserTokenWrapper login(String email, char[] password) {
		// TODO Auto-generated method stub
		Company company=companyRepository.findByEmail(email);
		if(company==null)
			return null;
		if((BCrypt.verifyer().verify(password,company.getPassword())).verified)
		{
			Key signKey=Keys.hmacShaKeyFor(localKey.getBytes());
			String jws = Jwts.builder().setSubject(company.getUuid().toString()).signWith(signKey).setIssuedAt(new Date()).compact();
			tokenService.save(new Token(company.getUuid(),jws));
			return new UserTokenWrapper(jws,company);
		}
		else 
			return null;
	}
	@Override
	public UserTokenWrapper register(Company companyData) {
		companyData.setPassword(BCrypt.withDefaults().hashToChar(8, companyData.getPassword()));
		Company user= this.save(companyData);
		
		return this.login(user.getEmail(), user.getPassword());
	}

	@Override
	public Company save(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.save(company);
	}

	
	@Override
	public Company update( Company companyDB,Company company) {
		// TODO Auto-generated method stub
		if(company.getPassword().length>0)
			company.setPassword(BCrypt.withDefaults().hashToChar(8, company.getPassword()));
		else
			company.setPassword(companyDB.getPassword());
		return companyRepository.save(company);
	}
	
}
