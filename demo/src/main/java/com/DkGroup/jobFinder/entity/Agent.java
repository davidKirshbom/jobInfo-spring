package com.DkGroup.jobFinder.entity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;


@Entity
@Table(name="user_smart_agents")
public class Agent {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location_area")
	private String area;
	
	@Column(name="job_type")
	private String jobType;
	
	@Column(name="search_words")
	private byte[] searchWords;
	
	@Column(name="user_uid")
	private UUID userUid;
	
	@Column(name="frequency_weeks")
	private int frequancyWeeks;
	
	@Column(name="last_scan_date")
	private Date lastScan;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="smart_agent_positions",joinColumns = @JoinColumn(name="agent_id"),inverseJoinColumns = @JoinColumn(name="position_id"))
	private List<Position> positions;
	@Fetch(org.hibernate.annotations.FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="smart_agent_categories",joinColumns = @JoinColumn(name="agent_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories;
	
	@Fetch(org.hibernate.annotations.FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="agents_sended_jobs",joinColumns = @JoinColumn(name="agent_id"),inverseJoinColumns = @JoinColumn(name="job_id"))
	private List<Job> sendedJobs;
	
	public Agent() {}
	public Agent(int id, String name, String area, String jobType, byte[] searchWords, UUID userUid,
			int frequancyWeeks, Date lastScan) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.jobType = jobType;
		this.searchWords = searchWords;
		this.userUid = userUid;
		this.frequancyWeeks = frequancyWeeks;
		this.lastScan = lastScan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public byte[] getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(byte[] searchWords) {
		this.searchWords = searchWords;
	}


	public UUID getUserUid() {
		return userUid;
	}

	public void setUserUid(UUID userUid) {
		this.userUid = userUid;
	}

	public int getFrequancyWeeks() {
		return frequancyWeeks;
	}

	public void setFrequancyWeeks(int frequancyWeeks) {
		this.frequancyWeeks = frequancyWeeks;
	}

	public Date getLastScan() {
		return lastScan;
	}

	public void setLastScan(Date lastScan) {
		this.lastScan = lastScan;
	}
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Job> getSendedJobs() {
		return sendedJobs;
	}
	public void setSendedJobs(List<Job> sendedJobs) {
		this.sendedJobs = sendedJobs;
	}
	
	

}
