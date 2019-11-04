package red.reksai.config;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import red.reksai.beanfactorypostprocessor.MyBeanFactoryPostProcessor;
import red.reksai.beanpostprocessor.MyBeanPostProcessorOne;
import red.reksai.dao.UserDao;
import red.reksai.dao.impl.UserDaoImpl;

import java.util.Collection;
import java.util.List;


/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/10/22 09:49
 */
public class AnnotationConfigApplicationContextTest {
	@Test
	public void test1() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		/**
		 * 	在手动调用${@link AnnotationConfigApplicationContext#register(Class[])}时的时候，
		 * 	如果不执行刷新操作，是不会将@ComponentScan下定义的扫描包中定义的bean注册到beanDefinitionMap中。
		 */
		annotationConfigApplicationContext.register(AppConfig.class);
		/**
		 *	添加自定义的BeanFactoryPostProcessor，首先自定的BeanFactoryPostProcessor都必须实现{@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}接口，这是第一步;
		 * 	至于赋值给beanFactory有两种方式：
		 * 		1. 在自定义的BeanFactoryPostProcessor上添加@Component注解;
		 * 		2. 调用{@link org.springframework.context.support.AbstractApplicationContext#addBeanFactoryPostProcessor(BeanFactoryPostProcessor)}方法；
		 * 		3. 同时进行1 和2 操作。
		 *  注：通过方法2和方法3 ，添加的BeanFactoryPostProcessor才会被添加到{@link org.springframework.context.support.AbstractApplicationContext#beanFactoryPostProcessors}的list集合中。
		 *
		 *  这里添加这个注释的原因是为了解释{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)}方法中
		 *  (list表示的是List<BeanFactoryPostProcessor> )，list的来源。
		 */
		annotationConfigApplicationContext.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
		/**
		 * {@link AbstractApplicationContext#refresh()}
		 */
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
