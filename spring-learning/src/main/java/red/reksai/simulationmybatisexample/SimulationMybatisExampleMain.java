package red.reksai.simulationmybatisexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.mybatisexample.config.MybatisExampleConfig;
import red.reksai.mybatisexample.servcie.QuestionServcie;
import red.reksai.simulationmybatisexample.config.SimulationMybatisExampleConfig;
import red.reksai.simulationmybatisexample.servcie.SubjectServcie;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:25
 */
public class SimulationMybatisExampleMain {
	/**
	 *
	 * 1. 产生对象
	 * 2. 实现接口
	 * 		{@link org.springframework.beans.factory.config.BeanFactoryPostProcessor } 失败
	 * 		{@link org.springframework.context.annotation.ImportSelector} 失败
	 * 		{@link org.springframework.context.annotation.ImportBeanDefinitionRegistrar}
	 *
	 * 3. 放入到spring map中
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SimulationMybatisExampleConfig.class);
		SubjectServcie subjectServcie = annotationConfigApplicationContext.getBean(SubjectServcie.class);
		System.out.println(subjectServcie.selectById(1));
	}

}
