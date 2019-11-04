package red.reksai.simulationmybatisexample.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import red.reksai.simulationmybatisexample.handler.MyInvocationHandler;
import red.reksai.simulationmybatisexample.mapper.SubjectMapper;

import java.lang.reflect.Proxy;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 02:57
 */
public class MyFactoryBean implements FactoryBean<Object> {

	private Class<?> aClass ;

	public MyFactoryBean(Class<?> aClass) {
		this.aClass = aClass;
	}

	@Override
	public Object getObject() throws Exception {
		Object object = Proxy.newProxyInstance(MyFactoryBean.class.getClassLoader(), new Class<?>[]{aClass}, new MyInvocationHandler());

		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return aClass;
	}
}
