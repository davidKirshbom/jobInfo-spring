package com.DkGroup.jobFinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DkGroup.jobFinder.entity.Job;

public interface JobRepository extends JpaRepository<Job,Integer>  {

}
