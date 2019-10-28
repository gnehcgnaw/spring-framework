package red.reksai.dao.impl;

import org.springframework.stereotype.Repository;
import red.reksai.dao.UserDao;


/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
@Repository
public class UserDaoImpl  implements UserDao{
	@Override
	public int selectUserById() {
		return 1;
	}
}
