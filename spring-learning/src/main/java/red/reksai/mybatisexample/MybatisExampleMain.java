package red.reksai.mybatisexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import red.reksai.mybatisexample.config.MybatisConfig;
import red.reksai.mybatisexample.servcie.QuestionServcie;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:25
 */
public class MybatisExampleMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MybatisConfig.class);
		QuestionServcie questionServcie = annotationConfigApplicationContext.getBean(QuestionServcie.class);
		System.out.println(questionServcie.selectById(1));
	}
}
