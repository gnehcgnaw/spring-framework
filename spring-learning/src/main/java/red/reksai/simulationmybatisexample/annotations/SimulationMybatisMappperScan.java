package red.reksai.simulationmybatisexample.annotations;


import org.springframework.context.annotation.Import;
import red.reksai.simulationmybatisexample.importbeandefinitionregistrar.MyImportBeanDefinitionRegistrar;

import java.lang.annotation.*;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface SimulationMybatisMappperScan {
	String basePackages();
}
