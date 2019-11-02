package red.reksai;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.config.AppConfig;
import red.reksai.dao.UserDao;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
public class AnnotationConfigApplicationContextTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(AppConfig.class);
		annotationConfigApplicationContext.refresh();
		UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
		System.out.println(userDao.selectUserById());
	}
}
