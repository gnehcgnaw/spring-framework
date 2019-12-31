package com.beatshadow.samples.jpetstore;

import com.beatshadow.samples.jpetstore.dao.jpa.JpaAccountDao;
import com.beatshadow.samples.jpetstore.dao.jpa.JpaItemDao;
import com.beatshadow.samples.jpetstore.services.PetStoreServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/20 00:50
 */
public class GeneralXMLStyleMain1 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
		PetStoreServiceImpl petStore= applicationContext.getBean("petStore",PetStoreServiceImpl.class);
		JpaAccountDao accountDao1 = (JpaAccountDao) applicationContext.getBean("aD");
		JpaAccountDao accountDao2 = (JpaAccountDao) applicationContext.getBean("accountDao");
		JpaItemDao jpaItemDao = (JpaItemDao) applicationContext.getBean("itemDao");
		Assert.notNull(petStore,"不为空");
		Assert.isTrue(accountDao1.equals(accountDao2),"是不是相等");
		Assert.notNull(jpaItemDao,"不为空");
		Assert.notNull(petStore.getUsernameList(),"不为空");

	}
}
