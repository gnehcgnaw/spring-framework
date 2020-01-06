package com.beatshadow.example.dependencies.dependenciesandconfigurationindetail.innerbean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.io.ClassPathResource;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2020/1/6 16:39
 */
public class XmlModeInnerBeanTest {

	private final static DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

	@BeforeEach
	public void setup(){
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		defaultListableBeanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver());
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("com.beatshadow.example/innerBeanExample.xml"));
		defaultListableBeanFactory.preInstantiateSingletons();
	}

	@Test
	public void testInnerBean(){
		OutBean bean = defaultListableBeanFactory.getBean(OutBean.class);
		System.out.println(bean);
	}
}

/**
 * 外部bean
 */
class OutBean{
	private String outBeanId ;
	private InnerBean1 innerBean1 = new InnerBean1();
	private InnerBean2 innerBean2 = new InnerBean2();

	public void setInnerBean1(InnerBean1 innerBean1) {
		this.innerBean1 = innerBean1;
	}

	public void setInnerBean2(InnerBean2 innerBean2) {
		this.innerBean2 = innerBean2;
	}

	public String getOutBeanId() {
		return outBeanId;
	}

	public void setOutBeanId(String outBeanId) {
		this.outBeanId = outBeanId;
	}

	public InnerBean1 getInnerBean() {
		return innerBean1;
	}

	public void setInnerBean(InnerBean1 innerBean) {
		this.innerBean1 = innerBean1;
	}
}

class InnerBean1 {
	private String innerBeanId ;

	public InnerBean1() {
	}

	public InnerBean1(String innerBeanId) {
		this.innerBeanId = innerBeanId;
	}
}

class InnerBean2 {
	private String innerBeanId ;

	public InnerBean2() {
	}

	public InnerBean2(String innerBeanId) {
		this.innerBeanId = innerBeanId;
	}
}
