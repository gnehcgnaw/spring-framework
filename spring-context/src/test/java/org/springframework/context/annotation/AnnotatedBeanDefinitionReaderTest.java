package org.springframework.context.annotation;

import org.junit.Test;
import org.springframework.beans.factory.parsing.DefaultsDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;


/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/11 11:13
 */
public class AnnotatedBeanDefinitionReaderTest {

	@Test
	public void name() {
		/**
		 * 	如果使用new DefaultListableBeanFactory()显得对源码了解的不多，对于源码了解的多的话，
		 * 	这个其实是在GenericApplicationContext中初始化了一个DefaultListableBeanFactory的，
		 * 	所以我们也可以用这种方式。
		 * 	AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader1 =
		 * 			new AnnotatedBeanDefinitionReader(new DefaultListableBeanFactory());
 		 */
		GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader2 = new AnnotatedBeanDefinitionReader(genericApplicationContext);
		BeanDefinitionRegistry registry = annotatedBeanDefinitionReader2.getRegistry();

		System.out.println(genericApplicationContext.getDefaultListableBeanFactory() instanceof DefaultListableBeanFactory);	// true
		System.out.println(registry instanceof  DefaultListableBeanFactory);	//false

		System.out.println(annotatedBeanDefinitionReader2);
	}
}