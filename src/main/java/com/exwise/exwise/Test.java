package com.exwise.exwise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	@GetMapping("/abc")
	public String myMethod() {
		return "Hello, dev!";
	}

}
