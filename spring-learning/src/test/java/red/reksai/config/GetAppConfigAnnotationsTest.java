package red.reksai.config;


import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/2 12:58
 */
public class GetAppConfigAnnotationsTest {

	@Test
	public void test1() throws ClassNotFoundException {
		Class<?> appConfigClass = Class.forName("red.reksai.config.AppConfig");
		Annotation[] annotations = appConfigClass.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			System.out.println(annotation);
			//执行结果如下所示：
			//@org.springframework.context.annotation.Configuration(value=)
			//@org.springframework.context.annotation.ComponentScan(scopeResolver=class org.springframework.context.annotation.AnnotationScopeMetadataResolver, lazyInit=false, resourcePattern=**/*.class, useDefaultFilters=true, excludeFilters=[], scopedProxy=DEFAULT, basePackageClasses=[], nameGenerator=interface org.springframework.beans.factory.support.BeanNameGenerator, basePackages=[red.reksai.*], value=[], includeFilters=[])
		}
	}
}
