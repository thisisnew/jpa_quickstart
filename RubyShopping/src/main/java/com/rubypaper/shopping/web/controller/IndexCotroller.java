package com.rubypaper.shopping.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCotroller {
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
}
