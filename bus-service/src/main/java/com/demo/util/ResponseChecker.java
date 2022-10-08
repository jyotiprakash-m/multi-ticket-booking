package com.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseChecker {
	public static ResponseEntity<Message> checkResponse(Message message) {
		if (message.isState()) {
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
