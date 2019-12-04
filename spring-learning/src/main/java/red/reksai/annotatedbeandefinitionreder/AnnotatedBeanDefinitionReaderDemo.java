package red.reksai.annotatedbeandefinitionreder;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/19 15:33
 */
public class AnnotatedBeanDefinitionReaderDemo {
	public static void main(String[] args) {
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(new DefaultListableBeanFactory());
	}
}
