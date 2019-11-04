package red.reksai.simulationmybatisexample.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import red.reksai.simulationmybatisexample.FirstTryMain;
import red.reksai.simulationmybatisexample.annotations.SimulationMybatisMappperScan;
import red.reksai.simulationmybatisexample.importbeandefinitionregistrar.MyImportBeanDefinitionRegistrar;

import javax.sql.DataSource;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @since : 2019/11/5 00:09
 */
@Configuration
@ComponentScan(basePackages = "red.reksai.simulationmybatisexample")
@Import(MyImportBeanDefinitionRegistrar.class)
//@MapperScan(basePackages ="red.reksai.simulationmybatisexampl.mapper")
@SimulationMybatisMappperScan(basePackages = "red.reksai.simulationmybatisexample.mapper")
public class SimulationMybatisExampleConfig {
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
