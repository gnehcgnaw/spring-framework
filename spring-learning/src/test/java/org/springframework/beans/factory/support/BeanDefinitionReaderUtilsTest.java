package org.springframework.beans.factory.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2020/1/4 01:49
 */
public class BeanDefinitionReaderUtilsTest {

	private static final BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(UserService.class);
	private static final BeanDefinitionRegistry registry =new SimpleBeanDefinitionRegistry();
	private BeanDefinition beanDefinition ;
	@BeforeEach
	public void setup(){
		beanDefinition = bdb.getBeanDefinition();
		
	}

    @Test
    void createBeanDefinition() {
    }

    @Test
    void generateBeanName() {
		String beanName = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, registry);
		assertThat(beanName).contains(UserService.class.getSimpleName()+"#");
	}

    @Test
    void testGenerateBeanName() {
		PropertyValue pv = new PropertyValue("orderService",OrderService.class);
		beanDefinition.getPropertyValues().addPropertyValue(pv);
		System.out.println(beanDefinition);
	}

    @Test
    void uniqueBeanName() {
		String beanClassName = beanDefinition.getBeanClassName();
		String beanName = BeanDefinitionReaderUtils.uniqueBeanName(beanClassName, registry);
		assertThat(beanName).contains(UserService.class.getSimpleName()+"#");
	}

    @Test
    void registerBeanDefinition() {
    }

    @Test
    void registerWithGeneratedName() {
    }
}

class UserService{
	private BeanDefinitionHolder beanDefinitionHolder ;

	public UserService(BeanDefinitionHolder beanDefinitionHolder) {
		this.beanDefinitionHolder = beanDefinitionHolder;
	}
}

class OrderService{
	private String id ;
	private String creatTime ;

}