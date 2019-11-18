package red.reksai.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/15 11:36
 */

@Component("userDao")
public class MyFactoryBean implements FactoryBean<Object> {

	@Override
	public Object getObject() throws Exception {
		return new UserDao();
	}

	@Override
	public Class<?> getObjectType() {
		return UserDao.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}


class UserDao {
	public void printUserMsg(){
		System.out.println("userMsg");
	}
}

@SuppressWarnings("all")
class FactoryBeanMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyFactoryBean.class);
		/**
		 * Exception in thread "main" java.lang.ClassCastException: red.reksai.factorybean.UserDao cannot be cast to red.reksai.factorybean.MyFactoryBean
		 * 	at red.reksai.factorybean.FactoryBeanMain.main(MyFactoryBean.java:40)
		 */
		//MyFactoryBean myFactoryBean = (MyFactoryBean) annotationConfigApplicationContext.getBean("myFactoryBean");

		/**
		 * 为什么下面会返回UserDao的实例？
		 * 要想解释这个问题就要查看FactoryBean的源码了。
		 */
		UserDao userDao = (UserDao) annotationConfigApplicationContext.getBean("userDao");
		userDao.printUserMsg();
		MyFactoryBean myFactoryBean = (MyFactoryBean) annotationConfigApplicationContext.getBean("&userDao");
	}
}
