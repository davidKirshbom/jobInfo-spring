package com.DkGroup.jobFinder.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DkGroup.jobFinder.entity.Job;
import com.DkGroup.jobFinder.entity.User;
import com.DkGroup.jobFinder.service.JobService;
import com.DkGroup.jobFinder.service.UserService;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRestController {

	private UserService userService;
	private JobService jobService;

	public UserRestController(UserService theUserService,JobService theJobService) {
		userService=theUserService;
		jobService=theJobService;
	}
	
	@GetMapping("")
	public List<User> getAll(){
		return userService.findAll();
	}
	
	@PostMapping("")
	public UserTokenWrapper newUser(@RequestBody(required = true) User user ) {
	
		return userService.register(user);
	}
	@PutMapping("")
	public User updateUser(@RequestBody(required = true) User user ) {
		
		return userService.update(user);
	}
	
	@PostMapping("/login")
	public UserTokenWrapper login(@RequestParam(name = "email")String email,@RequestParam(name = "password")char[] password,HttpServletResponse response) {
	UserTokenWrapper userToken= userService.login(email, password);
	if(userToken==null)
		{
		try {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		}
	return userToken;
	
	}
	
	@GetMapping("/details")
	public User getUserByToken(@Value("#{request.getAttribute('user')}") User currentUser) {
		System.out.println(currentUser);
		return currentUser;
	}
	@PostMapping("/send-cv")
	public ResponseEntity<HttpStatus> sendCV(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "jobId")int jobId){
		List<Job> sendedCV=currentUser.getSendedJobs();
		sendedCV.add(jobService.findOne(jobId));
		
		currentUser.setSendedJobs(sendedCV);
		userService.save(currentUser);
		return new ResponseEntity(HttpStatus.ACCEPTED);

	}
	@GetMapping("/save-job")
	public void addSavedJob(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "jobId")int jobId) {
		currentUser.getSavedJobs().add(jobService.findOne(jobId));
		userService.save(currentUser);
		System.out.println(currentUser);
	}
	@PostMapping("/remove-save-job")
	public void removeSavedJob(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam(name = "jobId")int jobId) {
		currentUser.getSavedJobs().remove(jobService.findOne(jobId));
		userService.save(currentUser);
		System.out.println(currentUser);
	}
	@GetMapping("/get-saved-job")
	public List<Job> savedJobs(@Value("#{request.getAttribute('user')}") User currentUser)
	{
		return currentUser.getSavedJobs();
	}
	@PostMapping("/add-saved-job")
	public ResponseEntity<HttpStatus> addSavedJobs(@Value("#{request.getAttribute('user')}") User currentUser,@RequestParam("jobId")int jobId)
	{
		List <Job> savedJobs=currentUser.getSavedJobs();
		savedJobs.add(jobService.findOne(jobId));
		currentUser.setSavedJobs(savedJobs);
		userService.save(currentUser);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	@GetMapping("/get-sended-list")
	public List<Job> sendedJobs(@Value("#{request.getAttribute('user')}") User currentUser)
	{
		return currentUser.getSendedJobs();
	}
	
}
