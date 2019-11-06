package red.reksai.simulationmybatisexample.importbeandefinitionregistrar;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import red.reksai.simulationmybatisexample.factorybean.MyFactoryBean;
import red.reksai.simulationmybatisexample.handler.MyInvocationHandler;
import red.reksai.simulationmybatisexample.mapper.SubjectMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 02:21
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		/**
		 * 因为代理对象spring不能帮我们new出来，所以注入不进去到servcie
		 */
		/*SubjectMapper subjectMapper = (SubjectMapper) Proxy.newProxyInstance(MyImportBeanDefinitionRegistrar.class.getClassLoader(), new Class<?>[]{SubjectMapper.class}, new MyInvocationHandler());
		subjectMapper.selectById(1);
		String[] beanDefinitionNames = registry.getBeanDefinitionNames();
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(subjectMapper.getClass());
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
		genericBeanDefinition.getBeanClassName();
		// $.Proxy.class --> genericBeanDefinition subjectMapper
		registry.registerBeanDefinition("subjectMapper",genericBeanDefinition);*/

		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SubjectMapper.class);
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("red.reksai.simulationmybatisexample.mapper.SubjectMapper");
		genericBeanDefinition.setBeanClass(MyFactoryBean.class);
		registry.registerBeanDefinition("subjectMapper",genericBeanDefinition);

	}

}
