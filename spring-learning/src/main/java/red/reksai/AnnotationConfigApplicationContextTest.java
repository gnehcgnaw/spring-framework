package red.reksai;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.config.AppConfig;
import red.reksai.dao.UserDao;

import java.util.function.Supplier;

/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
@SuppressWarnings("all")
public class AnnotationConfigApplicationContextTest {
	public static void main(String[] args) {
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
}
