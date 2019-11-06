package red.reksai.annotationconfigapplicationcontext.constructorsamples;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.config.AppConfig;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/6 22:42
 */
public class ParameterComponentClassesDemo {
	public static void main(String[] args) {
		//调用spring原生的构造
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		/**
		 * 对{@link AnnotationConfigApplicationContext#AnnotationConfigApplicationContext(Class[])}进行改造，让其不调用无参就能完成参数的初始化。
		 *
		 */
		//AnnotationConfigApplicationContext(AppConfig.class );
	}
}
