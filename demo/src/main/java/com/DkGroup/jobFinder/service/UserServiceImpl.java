package com.DkGroup.jobFinder.service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.DkGroup.jobFinder.dao.UserRepository;
import com.DkGroup.jobFinder.entity.Token;
import com.DkGroup.jobFinder.entity.User;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
@PropertySource("application.properties")
public class UserServiceImpl implements UserService {
	@Value( "${app.jwt_key}" )
	private String localKey;
	private UserRepository userRepository;
	private TokenService tokenService;
	private CompanyService companyService;
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository,TokenService theTokenService,CompanyService theCompanyService) {
		userRepository=theUserRepository;
		tokenService=theTokenService;
		companyService=theCompanyService;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		User savedUser= userRepository.save(user);
		return savedUser;
	}

	@Override
	public User getOne(UUID userUid) {
		// TODO Auto-generated method stub
		return userRepository.getOne(userUid);
	}

	@Override
	public UserTokenWrapper login(String email, char[] cs) {
		// TODO Auto-generated method stub
		System.out.println(email+java.util.Arrays.toString(cs));
		System.out.println(BCrypt.withDefaults().hashToChar(8,cs));
		User user=userRepository.findByEmail(email);
		if(user==null)
			return companyService.login(email, cs);
		if((BCrypt.verifyer().verify(cs,user.getPassword())).verified)
		{
			Key signKey=Keys.hmacShaKeyFor(localKey.getBytes());
			String jws = Jwts.builder().setSubject(user.getUuid().toString()).signWith(signKey).setIssuedAt(new Date()).compact();
			tokenService.save(new Token(user.getUuid(),jws));
			return new UserTokenWrapper(jws,user);
		}
		else 
			return 	null;

	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserTokenWrapper register(User userData) {
		userData.setPassword(BCrypt.withDefaults().hashToChar(8,userData.getPassword()));
		User user= this.save(userData);
		return this.login(user.getEmail(), user.getPassword());
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		User userDB=userRepository.getOne(user.getUuid());
		user.setAgents(userDB.getAgents());
		user.setTokens(userDB.getTokens());
		if(user.getPassword().length<8)
			user.setPassword(userDB.getPassword());
		else
			user.setPassword(BCrypt.withDefaults().hashToChar(8,user.getPassword()));
		User updatedUser=userRepository.save(user);
		return updatedUser;
	}
	

	
	
}
