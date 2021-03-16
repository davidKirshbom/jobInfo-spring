package com.DkGroup.jobFinder.rest;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DkGroup.jobFinder.entity.Company;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.Position;
import com.DkGroup.jobFinder.entity.ResultJob;
import com.DkGroup.jobFinder.service.JobService;
import com.DkGroup.jobFinder.utils.KeyValuePair;
import com.DkGroup.jobFinder.utils.SearchObj;


@RestController
@RequestMapping("/job")
@CrossOrigin(origins="http://jobinfo-spring-prod.s3-website-eu-west-1.amazonaws.com")
public class JobRestController {

	private JobService jobService;
	
	@Autowired
	public JobRestController(JobService theJobService)
	{
		jobService=theJobService;
	}
	
	@GetMapping("")
	public List<Job> get(@RequestParam(name = "jobId") Optional<Integer> jobId){
		if(jobId.isEmpty())
		return jobService.findAll();
		List <Job> jobs= new ArrayList<Job>();
		jobs.add(jobService.findOne(jobId.get()));
		return jobs;
	}
	@PutMapping("/insert")
	public void newJob(@Value("#{request.getAttribute('company')}") Company company,@RequestBody Job job) {
		job.setCompany(company.getUuid());
		job.setStartDate(new Date(System.currentTimeMillis()));
		jobService.save(job);
	}
	@GetMapping("/count-total")
	public long get(){
		
		return jobService.count();
		
	}
	@GetMapping("/job-positions")
	public List<Position> getJobPositions(@RequestParam(name = "jobId") Optional<Integer> jobId){
		
		return jobService.findOne(jobId.get()).getPositions();
	}
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<HttpStatus> Update(@Value("#{request.getAttribute('company')}") Company company,@RequestBody(required = true) Job job) {
		if(company.getJobs().indexOf(job)==-1)
			return new ResponseEntity<HttpStatus>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
		job.setCompany(company.getUid());
		jobService.save(job);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	@PutMapping("/toggle")
	public ResponseEntity<HttpStatus> toggleCloseOpen(@Value("#{request.getAttribute('company')}") Company company,@RequestParam(name = "jobId") int jobId) {
		if(company.getJobs().indexOf(new Job(jobId))==-1)
			return new ResponseEntity<HttpStatus>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
		jobService.toggle(jobId);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> delete(@Value("#{request.getAttribute('company')}") Company company,@RequestParam(name = "id") int jobId) {
		Job job= jobService.findOne(jobId);
		if(job==null)
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		if(company.getJobs().indexOf(job)==-1)
			return new ResponseEntity<HttpStatus>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		jobService.delete(job);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	@PostMapping("/search")
	public List<ResultJob> searchJob(@RequestBody SearchObj searchObj)
	{
		System.out.println(searchObj);
		List<ResultJob> jobs=jobService.searchJob(searchObj);
		return jobs;
	}
	@GetMapping("/categories")
	public List<KeyValuePair> categoriesNames(){
		System.out.println((jobService.categoriesList()));
		return jobService.categoriesList();
	}
	@GetMapping("/positions")
	public List<KeyValuePair> positionsNames(){
		return jobService.positionsList();
	}
	@GetMapping("/areas")
	public List<String> areasNames(){
		return jobService.areasList();
	}
	@GetMapping("/positions-type")
	public List<String> positionsTypeNames(){
		return jobService.positionsTypeList();
	}
	@GetMapping("/for-category")
	public List<Job> jobsByCategories(@RequestParam(name = "categoriesId") String categoriesIdString){
		String[] categoryId=categoriesIdString.split(",");
		List<Integer> categoriesId=new ArrayList<Integer>();
		for (String string : categoryId) {
			categoriesId.add(Integer.parseInt(string));
		}
		return jobService.getCategoriesJob(categoriesId);
	}
	
}
class wrapper{
	public List<Integer> categoryId;
	public wrapper() {}

	public wrapper(List<Integer> categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public List<Integer> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<Integer> categoryId) {
		this.categoryId = categoryId;
	}
	
}
class searchObjWrapper{
	public SearchObj searchObj;
	public searchObjWrapper() {}
	public searchObjWrapper(SearchObj searchObj) {
		super();
		this.searchObj = searchObj;
	}
	public SearchObj getSearchObj() {
		return searchObj;
	}
	public void setSearchObj(SearchObj searchObj) {
		this.searchObj = searchObj;
	}
	
}
