package com.DkGroup.jobFinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DkGroup.jobFinder.entity.Position;

public interface PositionRepository extends JpaRepository<Position,Integer> {

}
