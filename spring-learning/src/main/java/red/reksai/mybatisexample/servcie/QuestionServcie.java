package red.reksai.mybatisexample.servcie;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:22
 */
public interface QuestionServcie {
	Map<String,String> selectById(int id);
}
