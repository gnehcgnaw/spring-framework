package red.reksai.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import red.reksai.dao.UserDao;


/**
 * 自定义后置处理器
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/1 01:22
 */
@Component
public class MyBeanPostProcessorTwo implements BeanPostProcessor , PriorityOrdered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (("userDaoImpl").equals(beanName)){
			System.out.println("two before");
			// todo  e.g. Proxy.newProxyInstance();

		}
		// return 返回被改造之后的bean ，aop就是这样实现的
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof UserDao){
			System.out.println("two after");
		}
		return bean;
	}

	@Override
	public int getOrder() {
		return 200;
	}
}
