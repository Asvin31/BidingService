package com.fse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/api/")
public class BuyerController {
	
	@GetMapping()
	public String home() {
		return "home!";
	}

}
