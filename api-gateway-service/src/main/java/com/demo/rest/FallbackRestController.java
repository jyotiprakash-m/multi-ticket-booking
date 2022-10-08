package com.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.Message;

@RestController
public class FallbackRestController {
	Message message = new Message();
	Logger logger = LoggerFactory.getLogger(FallbackRestController.class);

	@GetMapping("/userServiceFallback")
	public ResponseEntity<Message> userServiceFallback() {
		logger.error("User service is down");
		message.setMessage("User service is down");
		message.setState(false);
		return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
	}
	@GetMapping("/busServiceFallback")
	public ResponseEntity<Message> busServiceFallback() {
		logger.error("Bus service is down");
		message.setMessage("Bus service is down");
		message.setState(false);
		return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
