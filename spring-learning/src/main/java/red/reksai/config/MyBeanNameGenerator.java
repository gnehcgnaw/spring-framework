package red.reksai.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.StringUtils;

/**
 *
 * 自定义BeanName生成器
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/2 18:22
 */
public class MyBeanNameGenerator implements BeanNameGenerator {

	private final static String  POREFIX_NAME = "test";
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		if (definition instanceof AnnotatedBeanDefinition) {
			String[] split = definition.getBeanClassName().split("\\.");
			String suffix = split[split.length-1];
			String beanName =POREFIX_NAME+suffix;
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
		return null;
	}
}
