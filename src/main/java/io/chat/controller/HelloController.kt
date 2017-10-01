package io.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by coupang on 2017. 9. 15..
 */
@RestController
class HelloController {
	@GetMapping("/hello")
	fun hello() {
		println("hello")
	}
}
