package com.beatshadow.samples.jpetstore.services;

import com.beatshadow.samples.jpetstore.dao.jpa.JpaAccountDao;
import com.beatshadow.samples.jpetstore.dao.jpa.JpaItemDao;

import java.util.Arrays;
import java.util.List;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/20 00:23
 */
public class PetStoreServiceImpl {
	private JpaAccountDao accountDao ;

	private JpaItemDao itemDao ;

	public void setAccountDao(JpaAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setItemDao(JpaItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public List<String> getUsernameList(){
		if (accountDao!=null&&itemDao!=null){
			return Arrays.asList("zhangsan","lisi","wangwu");
		}	else {
			return null ;
		}
	}
}
