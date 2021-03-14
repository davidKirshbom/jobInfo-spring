package com.DkGroup.jobFinder.service;

import java.util.List;
import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.ResultJob;
import com.DkGroup.jobFinder.utils.KeyValuePair;
import com.DkGroup.jobFinder.utils.SearchObj;


public interface JobService {
	public List<Job> findAll();
	public Job findOne(int id);
	public void save(Job job);
	public void delete(Job job);
	public List<ResultJob> searchJob(SearchObj searchObj);
	public long count();
	public List<KeyValuePair> categoriesList();
	public List<KeyValuePair> positionsList();
	public List<String> positionsTypeList();
	public List<String> areasList();
	public List<Job> getCategoriesJob(List<Integer> categoryId);
	public void toggle(int jobId);

}
