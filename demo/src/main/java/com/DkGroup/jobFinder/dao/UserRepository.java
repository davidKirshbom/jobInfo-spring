package com.DkGroup.jobFinder.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.DkGroup.jobFinder.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	  @Query(value="SELECT * FROM users u WHERE u.email = ?1" ,nativeQuery = true) 
	    User findByEmail( String email);
}
