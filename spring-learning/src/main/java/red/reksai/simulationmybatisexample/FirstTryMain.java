package red.reksai.simulationmybatisexample;

import red.reksai.simulationmybatisexample.handler.MyInvocationHandler;
import red.reksai.simulationmybatisexample.mapper.SubjectMapper;

import java.lang.reflect.Proxy;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:11
 */
public class FirstTryMain {
	public static void main(String[] args) {
		/**
		 * 出现：SubjectMapper subjectMapper = (SubjectMapper)Proxy.newProxyInstance(FirstTryMain.class.getClassLoader(), new Class[]{SubjectMapper.class}, new MyInvocationHandler());^
		 *   缺少泛型类Class<T>的类型参数
		 *   其中, T是类型变量:
		 *     T扩展已在类 Class中声明的Object
		 *   改为 <?>
		 */
		SubjectMapper subjectMapper = (SubjectMapper)Proxy.newProxyInstance(FirstTryMain.class.getClassLoader(), new Class<?>[]{SubjectMapper.class}, new MyInvocationHandler());
		subjectMapper.selectById(1);

	}
}
