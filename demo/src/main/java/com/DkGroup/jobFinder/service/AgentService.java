package com.DkGroup.jobFinder.service;

import java.util.List;

import com.DkGroup.jobFinder.entity.Agent;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.ResultJob;

public interface AgentService {
	public void save(Agent agent);
	public void removeAgent(int agentId);
	public List<ResultJob> scanJobs(int agentId);
	public List<Job> latestScanJobs(int agentId);

}
