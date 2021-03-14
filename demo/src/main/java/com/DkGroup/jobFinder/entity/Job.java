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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name="jobs")
public class Job {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name = "company_uid")
	private UUID company;
	
	@Column(name="description")
	private String description;
	@Column(name="qualifications")
	private String qualifications;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@Column(name="location_area")
	private String locationArea;
	@Column(name="type")
	private String type;
	@Column(name="experience_years")
	private int experienceYears;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category")
	private Category category;
	@Column(name="is_managerial_position")
	private boolean isManagerial;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="position_jobs_connection",joinColumns = @JoinColumn(name="job_id"),inverseJoinColumns = @JoinColumn(name="position_id"))
	private List<Position> positions;
	
	public Job() {}
	public Job(int id) {
		this.id=id;
	}

	

	public Job(int id, String roleName, UUID company, String description, String qualifications, Date startDate,
			Date endDate, String locationArea, String type, int experienceYears, Category category,
			boolean isManagerial, List<Position> positions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.company = company;
		this.description = description;
		this.qualifications = qualifications;
		this.startDate = startDate;
		this.endDate = endDate;
		this.locationArea = locationArea;
		this.type = type;
		this.experienceYears = experienceYears;
		this.category = category;
		this.isManagerial = isManagerial;
		this.positions = positions;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UUID getCompany() {
		return company;
	}

	public void setCompany(UUID company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isManagerial() {
		return isManagerial;
	}

	public void setManagerial(boolean isManagerial) {
		this.isManagerial = isManagerial;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", roleName=" + roleName + ", company=" + company + ", description=" + description
				+ ", qualifications=" + qualifications + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", locationArea=" + locationArea + ", type=" + type + ", experienceYears=" + experienceYears
				+ ", category=" + category + ", isManagerial=" + isManagerial + "]";
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.id==((Job)obj).getId();
	}
	
	
}
