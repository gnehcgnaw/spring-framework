package com.beatshadow.samples.jpetstore.services;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/30 16:00
 */
public class ClientService {

	private ClientService() {
	}

	private static ClientService clientServcie = new ClientService() ;

	public static ClientService createInstance(){
		return clientServcie ;
	}
}
