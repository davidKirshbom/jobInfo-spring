package com.DkGroup.jobFinder.entity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;


@Entity
@SqlResultSetMapping(name="resultJob",
entities={
    @EntityResult(entityClass=ResultJob.class, fields={
    		@FieldResult(name="id", column="id"),
    		@FieldResult(name="category", column="category"),
    		@FieldResult(name="company", column="company_uid"),
    		@FieldResult(name="description", column="description"),
    		@FieldResult(name="qualifications", column="qualifications"),
    		@FieldResult(name="endDate", column="end_date"),
    		@FieldResult(name="startDate", column="start_date"),
    		@FieldResult(name="locationArea", column="location_area"),
    		@FieldResult(name="experienceYears", column="experience_years"),
    		@FieldResult(name="isManagerial", column="is_managerial_position"),
    		@FieldResult(name="roleName", column="role_name"),
    		@FieldResult(name="type", column="type"),
        @FieldResult(name="companyOccuption", column="company_occupation"),
        @FieldResult(name="additionalPositions", column="additional_positions"),
        @FieldResult(name="additionalCategory", column="position_category")})}

)
public class ResultJob  {
	@Id
	@Column(name="id")
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
	@Column(name="company_occupation")
	private String companyOccuption;
	@Column(name="additional_positions")
	private String additionalPositions;
	@Column(name="position_category")
	private String additionalCategory;
	public ResultJob() {
		System.out.println("made resultjob");
	}

	public ResultJob(int id, String roleName, UUID company, String description, String qualifications, Date startDate,
			Date endDate, String locationArea, String type, int experienceYears, Category category,
			boolean isManagerial, List<Position> positions, String companyOccuption, String additionalPositions,
			String additionalCategory) {
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
		this.companyOccuption = companyOccuption;
		this.additionalPositions = additionalPositions;
		this.additionalCategory = additionalCategory;
	}

	public String getCompanyOccuption() {
		return companyOccuption;
	}

	public void setCompanyOccuption(String companyOccuption) {
		this.companyOccuption = companyOccuption;
	}

	public String getAdditionalPositions() {
		return additionalPositions;
	}

	public void setAdditionalPositions(String additionalPositions) {
		this.additionalPositions = additionalPositions;
	}

	public String getAdditionalCategory() {
		return additionalCategory;
	}

	public void setAdditionalCategory(String additionalCategory) {
		this.additionalCategory = additionalCategory;
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

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public String toString() {
		return "resultJob [companyOccuption=" + companyOccuption + ", additionalPositions=" + additionalPositions
				+ ", additionalCategory=" + additionalCategory + ", toString()=" + super.toString() + "]";
	}
	
	public Job castToJob() {
		return new Job(id,roleName,company,description,qualifications,startDate,endDate,locationArea,type,experienceYears,category,isManagerial,positions);
	}
	
	
	
}
