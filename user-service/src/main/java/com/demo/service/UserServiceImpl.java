package com.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.LoginHistory;
import com.demo.entity.User;
import com.demo.repository.UserServiceRepository;
import com.demo.util.Login;
import com.demo.util.Message;
import com.demo.util.UpdateUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserServiceRepository repo;
	

//	Logging
	Message message = new Message();
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public Message createUser(User user) {
		repo.save(user);
		logger.info("User is saved");
		message.setMessage("User is saved");
		message.setState(true);
		return message;
	}

	@Override
	@Transactional
	public Message updateUser(UpdateUser updateUser) {
		if (repo.findById(updateUser.getUserId()).isPresent()) {
			repo.updateUser(updateUser.getFirstName(), updateUser.getLastName(), updateUser.getCountry(), updateUser.getState(), updateUser.getDistrict(), updateUser.getUserId());
			logger.info("User is updated");
			message.setMessage("User is updated");
			message.setState(true);
		} else {
			logger.error("User is not present");
			message.setMessage("User is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	@Transactional
	public Message updateAvatar(String avatar, int userId) {
		if (repo.findById(userId).isPresent()) {
			repo.updateAvatar(avatar, userId);
			logger.info("Avatar is updated");
			message.setMessage("Avatar is updated");
			message.setState(true);
		} else {
			logger.error("User is not present");
			message.setMessage("User is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	@Transactional
	public Message updatePassword(String oldPassword, String newpassword, int userId) {
		Optional<User> userOp = repo.findById(userId);
		if (userOp.isPresent()) {
			if (oldPassword.equals(newpassword)) {
				logger.warn("Password is same as old password");
				message.setMessage("Password is same as old password");
				message.setState(false);
			} else if (userOp.get().getPassword().equals(oldPassword)) {
				repo.updatePassword(newpassword, userId);
				logger.info("Password is updated");
				message.setMessage("Password is updated");
				message.setState(true);
			} else {
				logger.error("Wrong credential");
				message.setMessage("Wrong credential");
				message.setState(false);
			}
		} else {
			logger.error("User is not present");
			message.setMessage("User is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public User getUserByID(int userId) {
		return repo.findById(userId).get();
	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		return repo.findByMobileNumber(mobileNumber).get();
	}

	@Override
	@Transactional
	public Message login(Login login) {

		Optional<User> userOp = repo.findByMobileNumber(login.getMobileNumber());
		if (userOp.isPresent()) {
			if (userOp.get().getPassword().equals(login.getPassword())) {
				if (userOp.get().isLogin()) {
					logger.warn("You are already login");
					message.setMessage("You are already login");
					message.setState(false);
				} else {
					userOp.get().setLogin(true);
					userOp.get().getLoginHistories().add(new LoginHistory(login.getSystemInfo(), LocalDateTime.now()));
					repo.save(userOp.get());
					logger.info("Login successful");
					message.setMessage("Login successful");
					message.setState(true);
				}
			} else {
				logger.error("Credential is not matched");
				message.setMessage("Credential is not matched");
				message.setState(false);
			}
		} else {
			logger.error("User not found");
			message.setMessage("User not found");
			message.setState(false);
		}
		return message;
	}

	@Override
	@Transactional
	public Message logout(int userId) {
		Optional<User> userOp = repo.findById(userId);
		if (userOp.isPresent()) {
			if (userOp.get().isLogin()) {
				repo.logout(userId);
				logger.info("Logout successful");
				message.setMessage("Logout successful");
				message.setState(true);
			} else {
				logger.warn("You already logout");
				message.setMessage("You already logout");
				message.setState(false);
			}
		} else {
			logger.error("User is not present");
			message.setMessage("User is not present");
			message.setState(false);
		}
		return message;
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

}
