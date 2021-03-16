package com.DkGroup.jobFinder.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DkGroup.jobFinder.entity.Company;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.service.CompanyService;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins="http://jobinfo-spring-prod.s3-website-eu-west-1.amazonaws.com")
public class CompanyRestController {
	private CompanyService companyService;
	
	public  CompanyRestController(CompanyService theCompanyService) {
		// TODO Auto-generated constructor stub
		companyService=theCompanyService;
	}
	@PostMapping("")
	public UserTokenWrapper newUser(@RequestBody(required = true) Company company ) {
	
		return companyService.register(company);
	}
	@GetMapping("/all")
	public List<String> getAll(){
		List <Company> companies=companyService.findAll();
		List <String> result=new ArrayList<String>();
		for (Company company : companies) {
			result.add(company.getName());
		}
		return  result;
	}
	@GetMapping("/login")
	public String login(@RequestParam(name = "email")String email,@RequestParam(name = "password")char[] password) {
		UserTokenWrapper loginToken= companyService.login(email, password);
	
	if(loginToken==null)
		return null;
	return loginToken.getToken();
	}
	@GetMapping("/jobs")
	public List<Job> getCompanyJobs(@Value("#{request.getAttribute('company')}") Company currentCompany) {
		// TODO Auto-generated constructor stub
		
		if(currentCompany==null)
			return null;
		else
			return currentCompany.getJobs();
	}
	@PutMapping("")
	public Company updateUser(@Value("#{request.getAttribute('company')}") Company companyDB,@RequestBody(required = true) Company company ) {
		return companyService.update(companyDB,company);
	}
}
