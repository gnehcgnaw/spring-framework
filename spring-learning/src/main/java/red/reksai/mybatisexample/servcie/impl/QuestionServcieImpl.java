package red.reksai.mybatisexample.servcie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.reksai.mybatisexample.mapper.QuestionMapper;
import red.reksai.mybatisexample.servcie.QuestionServcie;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:22
 */
@Service
public class QuestionServcieImpl  implements QuestionServcie {
	@Autowired
	private QuestionMapper questionMapper ;

	@Override
	public Map<String ,String> selectById(int id) {
		return questionMapper.selectById(id);
	}
}
