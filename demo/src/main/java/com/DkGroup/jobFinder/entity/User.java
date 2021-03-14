package com.DkGroup.jobFinder.entity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="users")
public class User implements Client {
	
	@Id
	@Column(name="uid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Type(type="pg-uuid")
	private UUID uuid;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="password")
	private char[] password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="email_subscribe")
	private boolean isSubscribe;
	
	@Column(name="send_auto_cv")
	private boolean isSendAutoCv;
	
	@Column(name="cv")
	private byte[] cv;
	
	@ManyToMany()
	@JoinTable(name="user_saved_jobs",joinColumns = @JoinColumn(name="user_uid"),inverseJoinColumns = @JoinColumn(name="job_id"))
	private List<Job> savedJobs;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_sended_jobs",joinColumns = @JoinColumn(name="user_uid"),inverseJoinColumns = @JoinColumn(name="job_id"))
	private List<Job> sendedJobs;
	
	
	@OneToMany()
	@JoinColumn(name="user_uid")
	private List<Token> tokens;
	@Fetch(org.hibernate.annotations.FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="user_uid")
	private List<Agent> agents;
	
@Transient
	private String userType="user";
	
	public User() {}

	public List<Job> getSavedJobs() {
		return savedJobs;
	}

	public void setSavedJobs(List<Job> savedJobs) {
		this.savedJobs = savedJobs;
	}

	

	public User(UUID uuid, String firstName, String lastName, String phoneNumber, char[] password, String email,
			boolean isSubscribe, boolean isSandAutoCv, byte[] cv, List<Job> savedJobs, List<Job> sendedJobs,
			List<Token> tokens, List<Agent> agents) {
		super();
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.email = email;
		this.isSubscribe = isSubscribe;
		this.isSendAutoCv = isSandAutoCv;
		this.cv = cv;
		this.savedJobs = savedJobs;
		this.sendedJobs = sendedJobs;
		this.tokens = tokens;
		this.agents = agents;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSubscribe() {
		return isSubscribe;
	}

	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public boolean isSandAutoCv() {
		return isSendAutoCv;
	}

	public void setSandAutoCv(boolean isSandAutoCv) {
		this.isSendAutoCv = isSandAutoCv;
	}

	public byte[] getCv() {
		return cv;
	}

	public void setCv(byte[] cv) {
		this.cv = cv;
	}
	
	public List<Job> getSendedJobs() {
		return sendedJobs;
	}

	public void setSendedJobs(List<Job> sendedJobs) {
		this.sendedJobs = sendedJobs;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public boolean isSendAutoCv() {
		return isSendAutoCv;
	}

	public void setSendAutoCv(boolean isSendAutoCv) {
		this.isSendAutoCv = isSendAutoCv;
	}

	public String getUserType() {
		return userType;
	}

	

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", password=" + password + ", email=" + email + ", isSubscribe=" + isSubscribe
				+ ", isSandAutoCv=" + isSendAutoCv + ", cv=" + Arrays.toString(cv) + ", savedJobs=" + savedJobs
				+ ", sendedJobs=" + sendedJobs + ", tokens=" + tokens + ", agents=" + agents + "]";
	}
	
	
	
}
