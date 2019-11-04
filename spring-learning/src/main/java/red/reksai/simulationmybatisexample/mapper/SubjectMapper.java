package red.reksai.simulationmybatisexample.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 01:02
 */
public interface SubjectMapper {
	@Select("SELECT * FROM t_subject WHERE id = #{id}")
	Map<String,String> selectById(int id);
}
