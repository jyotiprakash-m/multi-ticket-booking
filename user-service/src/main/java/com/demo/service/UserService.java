package com.demo.service;

import java.util.List;

import com.demo.entity.User;
import com.demo.util.Login;
import com.demo.util.Message;
import com.demo.util.UpdateUser;

public interface UserService {
//	Update avatar
	Message updateAvatar(String avatar, int userId);

//	Update password
	Message updatePassword(String oldPassword,String newpassword, int userI);

//	Update user
	Message updateUser(UpdateUser updateUser);

//	Create user
	Message createUser(User user);

//	Get user by id
	User getUserByID(int userId);

//	Get user by mobile no
	User getUserByMobileNumber(String mobileNumber);

//	Login
	Message login(Login login);

//	Logout
	Message logout(int userId);
	
//	Get All users
	List<User> getAllUsers();

}
