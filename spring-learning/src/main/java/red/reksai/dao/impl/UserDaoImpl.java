package red.reksai.dao.impl;

import org.springframework.stereotype.Repository;
import red.reksai.dao.UserDao;

import javax.annotation.PostConstruct;


/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
@Repository
public class UserDaoImpl  implements UserDao{
	public UserDaoImpl() {
		System.out.println("constructor execution");
	}

	@PostConstruct
	public void init() {
		// do some initialization work
		System.out.println("postConstruct execution");
	}

	@Override
	public int selectUserById() {
		return 1;
	}
}
