package com.DkGroup.jobFinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DkGroup.jobFinder.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
