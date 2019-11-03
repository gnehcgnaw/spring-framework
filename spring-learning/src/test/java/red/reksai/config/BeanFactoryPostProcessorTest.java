package red.reksai.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.dao.OrderDao;

/**
 * BeanFactoryPostProcessorTest
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/1 23:39
 *
 * @see red.reksai.beanfactorypostprocessor.MyBeanFactoryPostProcessor
 */
public class BeanFactoryPostProcessorTest {
	@Test
	public void test1() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderDao orderDao1 = (OrderDao) annotationConfigApplicationContext.getBean("testOrderDaoImpl");
		OrderDao orderDao2 = (OrderDao) annotationConfigApplicationContext.getBean("testOrderDaoImpl");
		//输出结果是false ，说明插手成功
		System.out.println(orderDao1.hashCode()==orderDao2.hashCode());
	}
}
