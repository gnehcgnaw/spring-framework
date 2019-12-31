package com.beatshadow.samples.jpetstore;

import com.beatshadow.samples.jpetstore.dao.jpa.JpaItemDao;
import com.beatshadow.samples.jpetstore.services.ClientService;
import com.beatshadow.samples.jpetstore.services.DefaultServiceLocator;
import com.beatshadow.samples.jpetstore.services.ServerService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/30 15:06
 */
public class BeanDefinitionDemo {
	public static void main(String[] args) throws NoSuchMethodException {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.registerBeanDefinition("jpaItemDao",newInstanceBean1(JpaItemDao.class));
		annotationConfigApplicationContext.registerBeanDefinition("clientService" ,newInstanceBean2(ClientService.class));
		annotationConfigApplicationContext.registerBeanDefinition("defaultServiceLocator",newInstanceBean1(DefaultServiceLocator.class));
		//annotationConfigApplicationContext.registerBeanDefinition("11",newInstanceBean3(DefaultServiceLocator.class, ServerService.class));
		annotationConfigApplicationContext.registerBeanDefinition("serverService",newInstanceBean4(ServerService.class));
		annotationConfigApplicationContext.refresh();
		System.out.println(annotationConfigApplicationContext.getBean("jpaItemDao"));
		System.out.println(annotationConfigApplicationContext.getBean("clientService"));
		System.out.println(annotationConfigApplicationContext.getBean("defaultServiceLocator"));
		System.out.println(annotationConfigApplicationContext.getBean("serverService"));
	}

	public static BeanDefinition newInstanceBean1(Class<?> clazz){
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(clazz);
		genericBeanDefinition.setConstructorArgumentValues(null);
		return genericBeanDefinition ;
	}

	public static BeanDefinition newInstanceBean2(Class<?> clazz) throws NoSuchMethodException {
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(clazz);
		genericBeanDefinition.setFactoryMethodName(clazz.getMethod("createInstance").getName());
		return genericBeanDefinition ;
	}

	public static BeanDefinition newInstanceBean3(Class<?> ... clazz ) throws NoSuchMethodException {
		//Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'defaultServiceLocator' available
		GenericBeanDefinition defaultServiceLocatorBeanDefinition = new GenericBeanDefinition();
		defaultServiceLocatorBeanDefinition.setBeanClass(clazz[0]);
		defaultServiceLocatorBeanDefinition.setBeanClassName("defaultServiceLocator");

		GenericBeanDefinition serverServiceBeanDefinition = new GenericBeanDefinition();
		serverServiceBeanDefinition.setBeanClass(clazz[1]);
		serverServiceBeanDefinition.setFactoryBeanName("defaultServiceLocator");
		serverServiceBeanDefinition.setFactoryMethodName(clazz[0].getMethod("newInstance").getName());
		return serverServiceBeanDefinition ;
	}


	public static BeanDefinition newInstanceBean4(Class<?> clazz ) throws NoSuchMethodException {

		GenericBeanDefinition serverServiceBeanDefinition = new GenericBeanDefinition();
		serverServiceBeanDefinition.setBeanClass(clazz);
		serverServiceBeanDefinition.setFactoryBeanName("defaultServiceLocator");
		serverServiceBeanDefinition.setFactoryMethodName("newInstance");
		return serverServiceBeanDefinition ;
	}

}
