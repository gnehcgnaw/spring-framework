package red.reksai.simulationmybatisexample.handler;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:37
 */
public class MyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("proxy");
		Method currentMethod = proxy.getClass().getInterfaces()[0].getMethod(method.getName(), int.class);
		System.out.println(currentMethod.getName());
		Select select = currentMethod.getAnnotation(Select.class);
		String[] value = select.value();
		System.out.println(value[0]);
		return null;
	}
}
