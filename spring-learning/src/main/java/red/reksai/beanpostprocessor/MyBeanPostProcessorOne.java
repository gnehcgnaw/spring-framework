package red.reksai.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import red.reksai.dao.UserDao;


/**
 * 自定义后置处理器
 *
 * @see PriorityOrdered	设置执行顺序
 * @see org.springframework.core.Ordered 设置执行顺序 ，PriorityOrdered是Ordered的接口实现
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/1 01:22
 */
@Component
public class MyBeanPostProcessorOne implements BeanPostProcessor , PriorityOrdered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (("testUserDaoImpl").equals(beanName)){
			System.out.println("one before");
			// todo  e.g. Proxy.newProxyInstance();
		}
		// return 返回被改造之后的bean ，aop就是这样实现的
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof UserDao){
			System.out.println("one after");
		}
		return bean;
	}

	@Override
	public int getOrder() {
		return 300;
	}
}
