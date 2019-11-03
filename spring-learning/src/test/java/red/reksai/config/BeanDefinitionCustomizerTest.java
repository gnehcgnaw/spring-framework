package red.reksai.config;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.beandefinitioncustomizer.MyServcie;

/**
 *
 * Spring 5 支持在应用程序上下文中以函数式方式注册 bean。简单地说，您可以通过在 GenericApplicationContext 类中定义的一个新 registerBean() 方法重载来完成。
 * registerBean() API 可以接收两种类型的函数式接口作为参数：
 *		{@link org.springframework.context.support.GenericApplicationContext#registerBean(Class, BeanDefinitionCustomizer...)}
 * 		{@link org.springframework.context.support.GenericApplicationContext#registerBean(String, Class, BeanDefinitionCustomizer...)}
 * 这个参数是一个函数式回调，我们可以使用它来设置 bean 属性，如 autowire-candidate 标志或 primary 标志。
 * 参看 https://www.bbsmax.com/A/o75NXp2WzW/
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/3 01:54
 */
public class BeanDefinitionCustomizerTest {
	@Test
	public void test1() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.registerBean(MyServcie.class,()->new MyServcie());
		annotationConfigApplicationContext.registerBean("myCallbackService", MyServcie.class,
				() -> new MyServcie(), bd -> {bd.setAutowireCandidate(false) ;bd.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);} );
		annotationConfigApplicationContext.refresh();
		MyServcie myServcie1 = annotationConfigApplicationContext.getBean(MyServcie.class);
		System.out.println(myServcie1.getRandomNumber());
		MyServcie myServcie2 = (MyServcie) annotationConfigApplicationContext.getBean("myCallbackService");
		System.out.println(myServcie2.getRandomNumber());
	}

}
