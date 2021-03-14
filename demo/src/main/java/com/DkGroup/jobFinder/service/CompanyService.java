package com.DkGroup.jobFinder.service;

import java.util.List;

import com.DkGroup.jobFinder.entity.Company;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

public interface CompanyService {
	public List<Company> findAll();
	public UserTokenWrapper login(String email, char[] password);
	public UserTokenWrapper register(Company companyData);
	public Company save(Company company);
	public Company update(Company companyDB, Company company);


}
