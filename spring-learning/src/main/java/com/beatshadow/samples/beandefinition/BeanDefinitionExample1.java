package com.beatshadow.samples.beandefinition;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/28 21:21
 */
public class BeanDefinitionExample1 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(UserServiceImpl.class);
		annotationConfigApplicationContext.registerBeanDefinition("userService",genericBeanDefinition);
		System.out.println(annotationConfigApplicationContext.getBean(UserServiceImpl.class));
	}
}

@Configuration
@ComponentScan(basePackages = "com.beatshadow.samples.beandefinition")
class AppConfig{

}

interface UserService{

}

class UserServiceImpl implements UserService{

}