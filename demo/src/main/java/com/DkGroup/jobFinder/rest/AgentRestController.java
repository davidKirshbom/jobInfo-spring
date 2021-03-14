package com.DkGroup.jobFinder.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DkGroup.jobFinder.entity.Agent;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.User;
import com.DkGroup.jobFinder.entity.ResultJob;
import com.DkGroup.jobFinder.service.AgentService;
@RestController
@RequestMapping("/agents")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentRestController {
	private AgentService agentService;
	@Autowired
	public AgentRestController(AgentService agentService) {
		super();
		this.agentService = agentService;
	}

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

	@GetMapping("")
	public List<Agent> getUserAgent(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name="agentId",required = false,defaultValue = "-1") int agentId){
		if(agentId!=-1)
			 {
			List<Agent> agents= new ArrayList<Agent>();
			for (Agent agent : currentUser.getAgents()) {
				if(agent.getId()==agentId)
					{
					agents.add(agent);
					return agents;
					}
				
			}
			return null;
			 }
		return currentUser.getAgents();
	}
	
	@PostMapping("")
	public void newAgent(@Value("#{request.getAttribute('user')}") User currentUser,@RequestBody Agent agent) {
		agent.setUserUid(currentUser.getUuid());
		agentService.save(agent);
	}
	@PutMapping("")
	public void updateAgent(@Value("#{request.getAttribute('user')}") User currentUser,@RequestBody Agent agent) {
		agent.setUserUid(currentUser.getUuid());
		agentService.save(agent);
	}
	@DeleteMapping("")
	public void deleteAgent(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "agentId") int agentId) {
		agentService.removeAgent(agentId);;
	}
	@PutMapping("/scan")
	public List<ResultJob> agentScan(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "agentId") int agentId){
		return agentService.scanJobs(agentId);
	}
	@GetMapping("/last-scan")
	public List<Job> lastAgentScan(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "agentId") int agentId){
		return agentService.latestScanJobs(agentId);
	}
}
