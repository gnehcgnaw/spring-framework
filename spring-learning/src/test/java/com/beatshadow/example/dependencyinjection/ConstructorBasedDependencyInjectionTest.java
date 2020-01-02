package com.beatshadow.example.dependencyinjection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 构造注入
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2020/1/2 14:24
 */
public class ConstructorBasedDependencyInjectionTest {
	private final static DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


	public static void main(String[] args) {

	}
	@BeforeEach
	public void setup(){
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver());
		//reader.loadBeanDefinitions(new ClassPathResource("constructorBaseDependencyInjection.xml" ,getClass()));
		reader.loadBeanDefinitions(new ClassPathResource("com.beatshadow.example/dependencyinjection/constructorBaseDependencyInjection.xml"));
		beanFactory.preInstantiateSingletons();
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo/demo.xml");
	}
	@Test
	public void test(){
		assertThat(beanFactory.getBean("com.beatshadow.example.dependencyinjection.ThingTwo#0")).isNotNull();
	}

}

class SimpleMovieLister {

	private MovieFinder movieFinder;

	// a constructor so that the Spring container can inject a com.beatshadow.example.dependencyinjection.MovieFinder
	public SimpleMovieLister(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	// business logic that actually uses the injected com.beatshadow.example.dependencyinjection.MovieFinder is omitted...
}

class MovieFinder{

}

class ThingOne {

	public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {

	}
}

class ThingTwo {

}

class ThingThree{

}