package com.demo.rest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.service.UserServiceImpl;
import com.demo.util.Login;
import com.demo.util.Message;
import com.demo.util.Password;
import com.demo.util.ResponseChecker;
import com.demo.util.UpdateUser;

@RestController
@RequestMapping("/user")
public class UserServiceRestController {
	@Autowired
	UserServiceImpl userService;

//	Create user
	@PostMapping("/save")
	public ResponseEntity<Message> createUser(@RequestBody User user) {
		return new ResponseEntity<Message>(userService.createUser(user), HttpStatus.OK);
	}

//	Update user
	@PutMapping("/update")
	public ResponseEntity<Message> updateUser(@RequestBody UpdateUser updateUser) {
		return ResponseChecker.checkResponse(userService.updateUser(updateUser));
	}

//	Update avatar
	@PutMapping("/update/avatar")
	public ResponseEntity<Message> updateAvatar(@RequestParam("avatar") String avatar,
			@RequestParam("userId") int userId) {
		return ResponseChecker.checkResponse(userService.updateAvatar(avatar, userId));

	}

//	Update password
	@PutMapping("/update/password")
	public ResponseEntity<Message> updatepassword(@RequestBody Password password) {
		return ResponseChecker.checkResponse(
				userService.updatePassword(password.getOldPassword(), password.getNewPassword(), password.getUserId()));
	}

//	Get user by id
	@GetMapping("/get/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") int userId) {
		return new ResponseEntity<User>(userService.getUserByID(userId), HttpStatus.OK);
	}

//	Get user by mobile number
	@GetMapping("/getByMobileNumber/{mobile}")
	public ResponseEntity<User> getUserByMobileNumber(@PathVariable("mobile") String mobile) {
		return new ResponseEntity<User>(userService.getUserByMobileNumber(mobile), HttpStatus.OK);
	}

//	Login
	@PostMapping("/login")
	public ResponseEntity<Message> login(@RequestBody Login login) {
		return ResponseChecker.checkResponse(userService.login(login));
	}

//	Logout
	@PostMapping("/logout")
	public ResponseEntity<Message> logout(@RequestParam("userId") int userId) {
		return ResponseChecker.checkResponse(userService.logout(userId));
	}

//	Get all user
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

//	Handling already exist data
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Message handleValidationExceptions(SQLIntegrityConstraintViolationException ex) {
		Message message = new Message();
		message.setMessage(ex.getMessage());
		message.setState(false);
		return message;
	}

//	Handle not found exception
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchElementException.class)
	public Message handleNotFoundExceptions(NoSuchElementException ex) {
		Message message = new Message();
		message.setMessage("User not found");
		message.setState(false);
		return message;
	}

//	Entity Validation exception handler (This exception will not come after adding service layer)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

//	Handle constraint violation exception ( This error come while we add service layer)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.put( violation.getPropertyPath().toString(),violation.getMessage());
		}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}

}
