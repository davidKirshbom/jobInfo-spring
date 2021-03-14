package com.DkGroup.jobFinder.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="companies")
public class Company implements Client {
	@Id
	@Column(name="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Type(type="pg-uuid")
	private UUID uuid;
	
	@Column(name="name")
	private String name;
	@Column(name="area_location")
	@JsonProperty(value = "areaLocation")
	private String areaLocation;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="email")
	private String email;
	@Column(name="category")
	private String category;
	@Column(name="password")
	private char[] password;
	@Transient
	private String userType="company";
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_uid")
	@JsonIgnore
	private List<Job> jobs;
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Company() {}

	public Company(String name, String area, String phoneNumber, String email, String category, char[] password) {
		super();
		this.name = name;
		this.areaLocation = area;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.category = category;
		this.password = password;
	}

	public UUID getUid() {
		return uuid;
	}

	public void setUid(UUID uid) {
		this.uuid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return areaLocation;
	}

	public void setArea(String areaLocation) {
		this.areaLocation = areaLocation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getAreaLocation() {
		return areaLocation;
	}

	public void setAreaLocation(String areaLocation) {
		this.areaLocation = areaLocation;
	}

	public String getUserType() {
		return userType;
	}


//	public List<Job> getJobs() {
//		return jobs;
//	}
//
//	public void setJobs(List<Job> jobs) {
//		this.jobs = jobs;
//	}
//	
//	public void add(Job job) {
//		if(jobs==null)
//			jobs=new ArrayList<Job>();
//		jobs.add(job);
//		job.setCompanyUid(this);
//	}
	
	
	
}
