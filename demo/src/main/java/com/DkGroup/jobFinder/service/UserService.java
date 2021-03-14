package com.DkGroup.jobFinder.service;

import java.util.List;
import java.util.UUID;

import com.DkGroup.jobFinder.entity.User;
import com.DkGroup.jobFinder.utils.UserTokenWrapper;

public interface UserService {
	public List<User> findAll();
	public User save(User user);
	public User update(User user);
	public User  getOne(UUID userUid);
	public UserTokenWrapper login(String email,char[] password);
	public User findByEmail(String email);
	public UserTokenWrapper register(User userData);


}
