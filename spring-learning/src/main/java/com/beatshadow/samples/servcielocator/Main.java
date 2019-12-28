package com.beatshadow.samples.servcielocator;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/24 11:11
 */
public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:serviceLocatorDemo.xml");
		classPathXmlApplicationContext.getBean(MyClientBean.class).someBusinessMethod();
		classPathXmlApplicationContext.getBean(MyClientBean.class).someBusinessMethod();
	}
}
