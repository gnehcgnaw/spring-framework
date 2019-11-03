package red.reksai.config;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.dao.UserDao;
import red.reksai.dao.impl.UserDaoImpl;


/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
@SuppressWarnings("all")
public class AnnotationConfigApplicationContextTest {
	@Test
	public void test1() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		/**
		 * 	在手动调用${@link AnnotationConfigApplicationContext#register(Class[])}时的时候，
		 * 	如果不执行刷新操作，是不会将@ComponentScan下定义的扫描包中定义的bean注册到beanDefinitionMap中。
		 */
		annotationConfigApplicationContext.register(AppConfig.class);
		annotationConfigApplicationContext.refresh();
		UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
		System.out.println(userDao.selectUserById());
	}

	/**
	 *
	 *如果使用的是 {@link AnnotationConfigApplicationContext#AnnotationConfigApplicationContext(Class[])}，即参数列表是一个被@Configuration标记的配置类，
	 * 那么这个构造里面是自带refresh()方法，后续不需要我们手动去调用。
	 */
	@Test
	public void test2(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
		System.out.println(userDao.selectUserById());
	}

	/**
	 *
	 */
	@Test
	public void test3(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.registerBean(UserDaoImpl.class, userDao->{
																					userDao.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
														});
		annotationConfigApplicationContext.refresh();
		UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
		System.out.println(userDao.selectUserById());
	}

	@Test
	public void test4(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DefaultListableBeanFactory.class);
		annotationConfigApplicationContext.register(UserDaoImpl.class);
		UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
		System.out.println(userDao.selectUserById());
	}
}
