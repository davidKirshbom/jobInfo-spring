package com.DkGroup.jobFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DkGroup.jobFinder.dao.PositionRepository;
import com.DkGroup.jobFinder.entity.Position;

@Service
public class PositionServiceImpl implements PositionService {

	private PositionRepository positionRepository;
	
	@Autowired
	public  PositionServiceImpl(PositionRepository thePositionRepository ) {
		// TODO Auto-generated constructor stub
		positionRepository=thePositionRepository;
		
	}
	
	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		return positionRepository.findAll();
	}
	
}
