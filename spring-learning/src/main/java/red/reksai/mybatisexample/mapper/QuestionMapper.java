package red.reksai.mybatisexample.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:06
 */
public interface QuestionMapper {
	@Select("SELECT * FROM t_question WHERE id = #{id}")
	Map<String,String> selectById(int id);
}