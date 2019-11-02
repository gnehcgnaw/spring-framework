/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

/**
 * Spring 5 支持在应用程序上下文中以函数式方式注册 bean。简单地说，您可以通过在 GenericApplicationContext 类中定义的一个新 registerBean() 方法重载来完成。
 * registerBean() API 可以接收两种类型的函数式接口作为参数：
 * 		{@link org.springframework.context.support.GenericApplicationContext#registerBean(Class, BeanDefinitionCustomizer...)}
 *  	{@link org.springframework.context.support.GenericApplicationContext#registerBean(String, Class, BeanDefinitionCustomizer...)}
 *  这个参数是一个函数式回调，我们可以使用它来设置 bean 属性，如 autowire-candidate 标志或 primary 标志。
 * Callback for customizing a given bean definition.
 * Designed for use with a lambda expression or method reference.
 *
 * @author Juergen Hoeller
 * @since 5.0
 * @see org.springframework.beans.factory.support.BeanDefinitionBuilder#applyCustomizers
 */
@FunctionalInterface
public interface BeanDefinitionCustomizer {

	/**
	 * Customize the given bean definition.
	 */
	void customize(BeanDefinition bd);

}
