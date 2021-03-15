package com.DkGroup.jobFinder.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DkGroup.jobFinder.dao.JobRepository;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.ResultJob;
import com.DkGroup.jobFinder.utils.KeyValuePair;
import com.DkGroup.jobFinder.utils.SearchObj;
import com.DkGroup.jobFinder.utils.SortBy;
import com.DkGroup.jobFinder.utils.Mapper;

@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	public JobServiceImpl(JobRepository theJobRepository) {
		jobRepository=theJobRepository;
	}
	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}
	@Override
	public Job findOne(int id) {
		// TODO Auto-generated method stub
		return jobRepository.findById(id).get();
	}
	@Override
	public void save(Job job) {
		// TODO Auto-generated method stub
		jobRepository.save(job);
	}
	@Override
	public void delete(Job job) {
		// TODO Auto-generated method stub
		jobRepository.delete(job);
		
	}
	
	@Override
	@Transactional
	public List<ResultJob> searchJob(SearchObj searchObj) {
		// TODO Auto-generated method stub
		String positions=searchObj.getFillters().getPositions()!=null?Arrays.toString( searchObj.getFillters().getPositions()).replace("[", "").replace("]", "").toString().toUpperCase():null;
		String searchWord=searchObj.getSearchWords();
		String locationArea=searchObj.getFillters().getJobsArea()!=null?Arrays.toString(searchObj.getFillters().getJobsArea()).replace("[", "").replace("]",""):null;
		String jobTypes=searchObj.getFillters().getJobs_type()!=null&&searchObj.getFillters().getJobs_type().length>0?Arrays.toString(searchObj.getFillters().getJobs_type()).replace("[", "").replace("]",""):null;
		SortBy sortBy=searchObj.getSortBy()!=null&&searchObj.getSortBy().getAttribute()!=null?searchObj.getSortBy():null;
		String queryString="SELECT jobs.*,companies.category AS company_occupation,p.category as additional_positions,positions_category.name as position_category FROM jobs\r\n"
				+ "            INNER JOIN positions_category ON jobs.category=positions_category.id\r\n"
				+ "            INNER JOIN companies ON jobs.company_uid=companies.uuid \r\n"
				+ "            INNER JOIN (SELECT position_jobs_connection.job_id,string_agg(positions.name,',') AS category FROM position_jobs_connection\r\n"
				+ "                  INNER JOIN positions ON positions.id=position_jobs_connection.position_id";
		queryString+=positions!=null? " WHERE upper(positions.name) IN ("+positions+")":"";
		queryString+=" GROUP BY position_jobs_connection.job_id) AS p \r\n"
				+ "                  ON jobs.id=p.job_id\r\n"
				+ "            WHERE";
		queryString+=(searchWord!=null&&searchWord!=""?"(role_name ILIKE '%"+searchWord+"%' OR description ILIKE '%"+searchWord+"%' OR  qualifications ILIKE '%"+searchWord+"%' )":" 1=1 ") +" AND ";
		queryString+=(searchObj.isSenorSearch()?" jobs.is_managerial_position='True' ":" 1=1 ")+" AND ";
		queryString+=(searchObj.isOpenJobsOnly()?" jobs.end_date IS NULL " : " 1=1 ") +" AND ";
		queryString+=(locationArea!=null?"location_area IN ("+locationArea+")":" 1=1 ")+" AND ";
		queryString+=(jobTypes!=null&&!jobTypes.contains("all types")?"positions_category.name IN ("+jobTypes+")":" 1=1 ")+" AND ";
		queryString+=(searchObj.isDateLimit()?"jobs.start_date BETWEEN current_date - integer '7' AND current_date":" 1=1 ");
		queryString+=(sortBy!=null?(" ORDER BY jobs."+sortBy.getAttribute()+" "+ (sortBy.isAscending() ? "ASC" : "DESC")):"ORDER BY jobs.start_date");
		queryString+=(searchObj.getResultLimit()!=-1?" Limit "+searchObj.getResultLimit()+" ":"");

		
		
		System.out.println("search \n"+queryString);
		Query searchQuery=entityManager.createNativeQuery(queryString,ResultJob.class);
	
		List<ResultJob> jobs=searchQuery.getResultList();
		
		return jobs;
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return jobRepository.count();
	}
	@Override
	public List<KeyValuePair> categoriesList() {
		// TODO Auto-generated method stub
		String query="SELECT * FROM positions_category";
		Query searchQuery=entityManager.createNativeQuery(query);
		List<Object[]> list=searchQuery.getResultList();
	
	   
		return  (List<KeyValuePair>)Mapper.mapObjToMap(list);
	}
	@Override
	public List<KeyValuePair> positionsList() {
		// TODO Auto-generated method stub
		String query="SELECT * FROM positions";
		Query searchQuery=entityManager.createNativeQuery(query);
		return (List<KeyValuePair>)Mapper.mapObjToMap(searchQuery.getResultList()) ;
	}
	@Override
	public List<String> positionsTypeList() {
		// TODO Auto-generated method stub
		String query="SELECT DISTINCT(jobs.type) FROM jobs";
		Query searchQuery=entityManager.createNativeQuery(query);
		return (List<String>)searchQuery.getResultList();
	}
	@Override
	public List<String> areasList() {
		// TODO Auto-generated method stub
		String query="SELECT DISTINCT(jobs.location_area) FROM jobs";
		Query searchQuery=entityManager.createNativeQuery(query);
		return searchQuery.getResultList();
	}
	@Override
	public List<Job> getCategoriesJob(List<Integer> categoryId) {
		// TODO Auto-generated method stub
		List <Job> jobs=jobRepository.findAll();
		List<Job> result=new ArrayList<Job>();
		for (Job job : jobs) {
			if(categoryId.contains(job.getCategory().getId()))
				result.add(job);
		}
		return result;
	}
	@Override
	public void toggle(int jobId) {
	Job job=jobRepository.findById(jobId).get();
	if(job.getEndDate()==null)
		job.setEndDate(new Date(System.currentTimeMillis()));
	else
		job.setEndDate(null);
	jobRepository.save(job);
	}
}
