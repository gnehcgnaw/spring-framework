package com.beatshadow.samples.autowiringmode;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/30 10:35
 */
public class AutowiringModeDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowiringModeDemo.xml");
		System.out.println(applicationContext.getBean("orderService"));
	}
}

class UserService {

}

class OrderService{
	private UserService userService ;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
