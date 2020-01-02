package com.beatshadow.example.autowired.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/31 14:21
 */
@Component
public class OrderService {
	@Autowired
	private UserService userService ;

	public void printOrderMsg(){
		System.out.println(this.userService);

	}
}
