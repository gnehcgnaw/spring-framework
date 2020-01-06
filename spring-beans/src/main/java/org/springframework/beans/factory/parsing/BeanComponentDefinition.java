/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.beans.factory.parsing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.lang.Nullable;

/**
 * 基于标准BeanDefinition的ComponentDefinition，它公开了给定的bean定义以及给定bean的内部bean定义和bean引用。
 * ComponentDefinition based on a standard BeanDefinition, exposing the given bean
 * definition as well as inner bean definitions and bean references for the given bean.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public class BeanComponentDefinition extends BeanDefinitionHolder implements ComponentDefinition {

	private BeanDefinition[] innerBeanDefinitions;

	private BeanReference[] beanReferences;


	/**
	 * Create a new BeanComponentDefinition for the given bean.
	 * @param beanDefinition the BeanDefinition
	 * @param beanName the name of the bean
	 */
	public BeanComponentDefinition(BeanDefinition beanDefinition, String beanName) {
		this(new BeanDefinitionHolder(beanDefinition, beanName));
	}

	/**
	 * Create a new BeanComponentDefinition for the given bean.
	 * @param beanDefinition the BeanDefinition
	 * @param beanName the name of the bean
	 * @param aliases alias names for the bean, or {@code null} if none
	 */
	public BeanComponentDefinition(BeanDefinition beanDefinition, String beanName, @Nullable String[] aliases) {
		this(new BeanDefinitionHolder(beanDefinition, beanName, aliases));
	}

	/**
	 * 为给定的bean创建一个新的BeanComponentDefinition。
	 * Create a new BeanComponentDefinition for the given bean.
	 * @param beanDefinitionHolder the BeanDefinitionHolder encapsulating
	 * the bean definition as well as the name of the bean	封装了Bean定义以及Bean名称的BeanDefinitionHolder
	 */
	public BeanComponentDefinition(BeanDefinitionHolder beanDefinitionHolder) {
		super(beanDefinitionHolder);
		//用于存放内部bean的list集合
		List<BeanDefinition> innerBeans = new ArrayList<>();
		//用于存放引用的list集合
		List<BeanReference> references = new ArrayList<>();
		//获取当前bean定义的PropertyValues
		//换而言之：就是获取bean标签中的property子标签对象的集合
		PropertyValues propertyValues = beanDefinitionHolder.getBeanDefinition().getPropertyValues();
		//遍历PropertyValues，得到所有的PropertyValue
		for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
			//获取PropertyValue的value值
			Object value = propertyValue.getValue();
			//判断当前对象是不是BeanDefinitionHolder
			if (value instanceof BeanDefinitionHolder) {
				innerBeans.add(((BeanDefinitionHolder) value).getBeanDefinition());
			}
			//判断当前对象是不是BeanDefinition
			else if (value instanceof BeanDefinition) {
				innerBeans.add((BeanDefinition) value);
			}
			//判断当前对象是不是BeanReference ，即是不是<ref>标签
			else if (value instanceof BeanReference) {
				references.add((BeanReference) value);
			}
		}
		//如果bean定义中的property属性包含了其他bean的定义，那么就叫做innerBeanDefinition对象
		this.innerBeanDefinitions = innerBeans.toArray(new BeanDefinition[0]);
		//将BeanReference对象的list集合转换为类型为BeanReference的数组
		this.beanReferences = references.toArray(new BeanReference[0]);
	}


	@Override
	public String getName() {
		return getBeanName();
	}

	@Override
	public String getDescription() {
		return getShortDescription();
	}

	@Override
	public BeanDefinition[] getBeanDefinitions() {
		return new BeanDefinition[] {getBeanDefinition()};
	}

	@Override
	public BeanDefinition[] getInnerBeanDefinitions() {
		return this.innerBeanDefinitions;
	}

	@Override
	public BeanReference[] getBeanReferences() {
		return this.beanReferences;
	}


	/**
	 * This implementation returns this ComponentDefinition's description.
	 * @see #getDescription()
	 */
	@Override
	public String toString() {
		return getDescription();
	}

	/**
	 * This implementations expects the other object to be of type BeanComponentDefinition
	 * as well, in addition to the superclass's equality requirements.
	 */
	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || (other instanceof BeanComponentDefinition && super.equals(other)));
	}

}
