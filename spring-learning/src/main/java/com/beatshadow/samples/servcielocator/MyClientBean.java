package com.beatshadow.samples.servcielocator;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/24 11:03
 */
public class MyClientBean {
	private ServiceFactory serviceFactory ;

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	public void someBusinessMethod(){
		MyServcie service = this.serviceFactory.getService("userService");
		System.out.println(service);
	}
}
