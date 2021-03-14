package com.DkGroup.jobFinder.utils;

import java.util.Arrays;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Fillter {
	@JsonProperty("job_type")
	private String[] jobs_type;
	@JsonProperty("positions")
	private String[] positions;
	@JsonProperty("location_area")
	private String[] jobsArea;
	
	public Fillter(String[] jobs_type, String[] positions, String[] jobsArea) {
		super();
		this.jobs_type = jobs_type;
		this.positions = positions;
		this.jobsArea = jobsArea;
	}
	public Fillter() {
		
	}
	public String[] getJobs_type() {
		return jobs_type;
	}
	public void setJobs_type(String[] jobs_type) {
		this.jobs_type = jobs_type;
	}
	public String[] getPositions() {
		return positions;
	}
	public void setPositions(String[] positions) {
		
		this.positions = positions;
	}
	public String[] getJobsArea() {
		return jobsArea;
	}
	public void setJobsArea(String[] jobsArea) {
		this.jobsArea = jobsArea;
	}
	@Override
	public String toString() {
		return "filters [jobs_type=" + Arrays.toString(jobs_type) + ", positions=" + Arrays.toString(positions)
				+ ", jobsArea=" + Arrays.toString(jobsArea) + "]";
	}
	
}
