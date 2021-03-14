package com.DkGroup.jobFinder.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DkGroup.jobFinder.dao.AgentRepository;
import com.DkGroup.jobFinder.entity.Agent;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.ResultJob;
import com.DkGroup.jobFinder.utils.SearchObj;
import com.DkGroup.jobFinder.utils.Fillter;

@Service
public class AgentServiceImpl implements AgentService {
	private AgentRepository agentRepository;
	private JobService jobService;
	@Autowired
	public  AgentServiceImpl(AgentRepository theAgentRepository,JobService theJobService) {
		// TODO Auto-generated constructor stub
		agentRepository=theAgentRepository;
		jobService=theJobService;
	}
	@Override
	public void save(Agent agent) {
		// TODO Auto-generated method stub
		agentRepository.save(agent);
	}


	@Override
	public void removeAgent(int agentId) {
		// TODO Auto-generated method stub
		agentRepository.delete(agentRepository.getOne(agentId));
	}

	@Override
	public List<ResultJob> scanJobs(int agentId) {
		// TODO Auto-generated method stub
		Agent agent=agentRepository.getOne(agentId);
		String[] jobTypes= {agent.getJobType()};
		List<String> positions= new ArrayList<String>();
		List<Job> jobs= new ArrayList<Job>();
		agent.getPositions().stream().forEach(position->positions.add("'"+position.getName()+"'"));
		String[] areas=("'"+agent.getArea()+"'").replace(",", "','").split(",");
		SearchObj searchObj=new SearchObj(null,agent.getSearchWords()!=null? agent.getSearchWords().toString():null, new Fillter(jobTypes,Arrays.copyOf(positions.toArray(), positions.size(),String[].class),areas), false, true, false, 100,0);
		List <ResultJob> foundJobs=jobService.searchJob(searchObj);
		agent.setLastScan(new Date(System.currentTimeMillis()));
		foundJobs.stream().forEach(job->jobs.add(job.castToJob()));
		agent.setSendedJobs(jobs);
		agentRepository.save(agent);
		return 	foundJobs;	
	}

	@Override
	public List<Job> latestScanJobs(int agentId) {
		// TODO Auto-generated method stub
		Agent agent=agentRepository.getOne(agentId);
		Hibernate.initialize(agent.getSendedJobs());
		return agent.getSendedJobs();
	}

}
