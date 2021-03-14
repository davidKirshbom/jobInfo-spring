package com.DkGroup.jobFinder.config;

import java.security.Key;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import com.DkGroup.jobFinder.dao.CompanyRepository;
import com.DkGroup.jobFinder.dao.UserRepository;
import com.DkGroup.jobFinder.entity.Client;
import com.DkGroup.jobFinder.entity.Company;
import com.DkGroup.jobFinder.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
	@Value("${app.jwt_key}")
	private String localKey;

	private UserRepository userRepository;
	private CompanyRepository companyRepository;

	@Autowired
	public ProductServiceInterceptor(UserRepository theUserRepository,CompanyRepository theCompanyRepository) {
		userRepository = theUserRepository;
		companyRepository=theCompanyRepository;
	}

	@Override
	@Transactional
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("authorization");
		if (token == null) {
			return true;
		}
		token = token.replaceFirst("Bearer", "").trim();
		Optional<Client> user=findByToken(token);
		if(user.isEmpty())
			{
			user=findCompanyByToken(token);
			if(user.isEmpty())
				{
				response.sendError(401, "Unauthorized ");
				return false;
				}
			Company comapny=(Company)user.get();
			Hibernate.initialize(comapny.getJobs());
			request.setAttribute("company", comapny);
			return true;
			}
			User cuurentUser=(User) user.get();
			Hibernate.initialize(cuurentUser.getSavedJobs());
			Hibernate.initialize(cuurentUser.getSendedJobs());
			Hibernate.initialize(cuurentUser.getTokens());
			Hibernate.initialize(cuurentUser.getAgents());
			request.setAttribute("user", cuurentUser);
			
		
		return true;
	}

	public Optional<Client> findByToken(String token) {
		try {
			Key signKey = Keys.hmacShaKeyFor(localKey.getBytes());
			String userUid = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token.toString())
					.getBody().getSubject();
			Optional<User> user=userRepository.findById(UUID.fromString(userUid));
			if(user.isEmpty())
				return Optional.empty();
			return Optional.of(user.get());
		} catch (JwtException e) {
			return Optional.empty();
		}
	}
	public Optional<Client> findCompanyByToken(String token) {
		try {
			Key signKey = Keys.hmacShaKeyFor(localKey.getBytes());
			String comapnyUid = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token.toString())
					.getBody().getSubject();
			Company company=companyRepository.findById(UUID.fromString(comapnyUid)).get();
			if(company==null)
				return Optional.empty();
			return Optional.of(company);
		} catch (JwtException e) {
			return Optional.empty();
		}
	}
}
