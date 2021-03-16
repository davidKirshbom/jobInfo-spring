package com.DkGroup.jobFinder.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DkGroup.jobFinder.entity.Position;
import com.DkGroup.jobFinder.service.PositionService;

@RestController
@RequestMapping(path = "/position")
@CrossOrigin(origins="http://jobinfo-spring-prod.s3-website-eu-west-1.amazonaws.com")
public class PositionRestController {

	private PositionService positionService;
	
	public PositionRestController(PositionService thePositionService) {
		positionService=thePositionService;
	}
	@GetMapping("")
	public List<Position> getAll(){
		return positionService.findAll();
	}
}
