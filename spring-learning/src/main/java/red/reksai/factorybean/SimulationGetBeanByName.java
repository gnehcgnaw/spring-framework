package red.reksai.factorybean;


/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/18 09:39
 */
public class SimulationGetBeanByName {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.getBean("xxxBeanName");
	}
}

interface BeanFactory{
	Object getBean(String name);
}

abstract class AbstractBeanFactory implements BeanFactory{
	@Override
	public Object getBean(String name) {
		return doGetBean(name);
	}

	protected  Object doGetBean(String name){
		return getObjectForBeanInstance(name);
	}

	Object getObjectForBeanInstance(String name){
		return new Object();
	}
}

abstract  class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
	@Override
	Object getObjectForBeanInstance(String name) {
		return super.getObjectForBeanInstance(name);
	}
}

class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory {
	public Object getBean(Class<?> type){
		return new Object();
	}
}

abstract  class AbstractApplicationContext implements BeanFactory{
	@Override
	public Object getBean(String name) {
		return new DefaultListableBeanFactory().getBean(name);
	}
}

class AnnotationConfigApplicationContext extends AbstractApplicationContext {

}

