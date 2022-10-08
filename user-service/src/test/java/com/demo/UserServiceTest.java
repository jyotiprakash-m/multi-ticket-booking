package com.demo;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.repository.UserServiceRepository;
import com.demo.rest.UserServiceRestController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceTest {
	@Autowired
	MockMvc mvc;
	@Autowired
	UserServiceRepository repo;
	
	@InjectMocks
	UserServiceRestController usc;

}
