package com.DkGroup.jobFinder.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DkGroup.jobFinder.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,UUID> {
	 @Query(value="SELECT * FROM companies c WHERE c.email = ?1" ,nativeQuery = true) 
	    Company findByEmail( String email);
}
