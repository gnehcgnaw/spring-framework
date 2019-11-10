package red.reksai.imports;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import red.reksai.dao.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/7 11:05
 */
public class MyImplortSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//EchoDao echoDao = (EchoDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{EchoDao.class}, new MyInvocationHandler());
		//importingClassMetadata.geti
		Class<? extends AnnotationMetadata> aClass = importingClassMetadata.getClass();
		String name = aClass.getName();
		System.out.println(name);
		return new String[]{EchoDaoImpl.class.getName()};
	}
}

class  MyInvocationHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy execution");
		return proxy;
	}
}

@Configuration
@Import(MyImplortSelector.class)
class AppConfig {

}


class EchoDaoImpl implements EchoDao{

	@Override
	public String echo() {
		return this.getClass().getName();
	}
}

interface EchoDao{
	String echo();
}

class Main{
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		EchoDao echoDaoImpl =  annotationConfigApplicationContext.getBean(EchoDao.class);
		System.out.println(echoDaoImpl.echo());
	}
}