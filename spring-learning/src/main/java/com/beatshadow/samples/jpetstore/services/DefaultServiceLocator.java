package com.beatshadow.samples.jpetstore.services;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/12/30 16:28
 */
public class DefaultServiceLocator {
	private static ServerService serverService = new ServerService() ;
	public ServerService newInstance(){
		return serverService ;
	}
}
