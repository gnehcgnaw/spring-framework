package red.reksai.simulationmybatisexample.servcie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.reksai.simulationmybatisexample.mapper.SubjectMapper;
import red.reksai.simulationmybatisexample.servcie.SubjectServcie;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:00
 */
@Service
public class SubjectServiceImpl implements SubjectServcie {
	@Autowired
	private SubjectMapper subjectMapper ;
	@Override
	public Map<String, String> selectById(int id) {
		return subjectMapper.selectById(id);
	}

}
