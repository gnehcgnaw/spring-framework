package red.reksai.mybatisexample.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:09
 */
@Configuration
@ComponentScan(basePackages = "red.reksai.mybatisexample")
@MapperScan(basePackages = "red.reksai.mybatisexample.mapper")
public class MybatisConfig {
	@Bean
	public SqlSessionFactory sqlSessionFactory () throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}

	private DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/exam?serverTimezone=Asia/Shanghai&characterEncoding=utf8");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("1qaz2wsx!@#");
		return driverManagerDataSource ;
	}
}
