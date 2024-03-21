package com.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {

	@GetMapping("/test")
	public String getTest() {
		return "Test";
	}
}
