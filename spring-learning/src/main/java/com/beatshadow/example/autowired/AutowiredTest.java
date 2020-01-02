package com.beatshadow.example.autowired;

import com.beatshadow.example.autowired.config.AppConfig;
import com.beatshadow.example.autowired.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/31 14:10
 */
public class AutowiredTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderService orderService = annotationConfigApplicationContext.getBean(OrderService.class);
		orderService.printOrderMsg();
	}
}
