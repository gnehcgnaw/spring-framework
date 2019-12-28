package com.beatshadow.samples.jpetstore;

import java.beans.Introspector;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/23 22:19
 */
public class IntrospectorTest {
	public static void main(String[] args) {
		String userServiceImpl = Introspector.decapitalize("UserServiceImpl");
		String url = Introspector.decapitalize("URL");
		System.out.println(url);
	}
}
