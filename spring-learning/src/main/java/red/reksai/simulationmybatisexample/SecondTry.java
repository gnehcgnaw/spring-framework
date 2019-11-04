package red.reksai.simulationmybatisexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.simulationmybatisexample.config.SimulationMybatisExampleConfig;
import red.reksai.simulationmybatisexample.servcie.SubjectServcie;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 02:45
 */
public class SecondTry {
	/**
	 * Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'subjectMapper':
	 * Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
	 * No qualifying bean of type 'java.lang.reflect.InvocationHandler' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SimulationMybatisExampleConfig.class);
	}
}
