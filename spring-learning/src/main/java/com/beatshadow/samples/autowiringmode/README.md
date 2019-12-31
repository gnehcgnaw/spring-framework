1. 首先介绍依赖注入的两种方式
https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-collaborators
* AutowiringModeDemo.java
```
public class AutowiringModeDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowiringModeDemo.xml");
		System.out.println(applicationContext.getBean("orderService"));
	}
}

class UserService {

}

class OrderService{
	private UserService userService ;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

```
* autowiringModeDemo.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:beans="http://www.springframework.org/schema/util"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">
	<bean id="userService" class="com.beatshadow.samples.autowiringmode.UserService"/>
	<bean id="orderService" class="com.beatshadow.samples.autowiringmode.OrderService" >
		<property name="userService" ref="userService"/>
	</bean>
</beans>
```
