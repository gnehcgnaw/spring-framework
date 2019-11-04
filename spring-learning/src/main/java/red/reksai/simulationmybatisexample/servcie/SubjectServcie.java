package red.reksai.simulationmybatisexample.servcie;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:00
 */
public interface SubjectServcie {
	Map<String,String> selectById(int id);
}
