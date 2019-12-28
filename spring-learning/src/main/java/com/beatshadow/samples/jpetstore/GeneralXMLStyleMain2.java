package com.beatshadow.samples.jpetstore;

import com.beatshadow.samples.jpetstore.dao.jpa.JpaAccountDao;
import com.beatshadow.samples.jpetstore.dao.jpa.JpaItemDao;
import com.beatshadow.samples.jpetstore.services.PetStoreServiceImpl;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.Assert;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/20 00:50
 */
public class GeneralXMLStyleMain2 {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();
		new XmlBeanDefinitionReader(context).loadBeanDefinitions("classpath:beans.xml");
		context.refresh();
		PetStoreServiceImpl petStore= context.getBean("petStore",PetStoreServiceImpl.class);
		JpaAccountDao accountDao = (JpaAccountDao) context.getBean("com.beatshadow.samples.jpetstore.services.UserServcie");
		JpaItemDao jpaItemDao = (JpaItemDao) context.getBean("itemDao");
		Assert.notNull(petStore,"不为空");
		Assert.notNull(accountDao,"不为空");
		Assert.notNull(jpaItemDao,"不为空");
		Assert.notNull(petStore.getUsernameList(),"不为空");

	}
}
