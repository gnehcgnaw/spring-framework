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

package org.springframework.beans;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.lang.Nullable;

/**
 * 描述bean定义中的property属性
 * Holder containing one or more {@link PropertyValue} objects,
 * typically comprising one update for a specific target bean.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 13 May 2001
 * @see PropertyValue
 */
public interface PropertyValues extends Iterable<PropertyValue> {

	/**
	 * 在属性值上返回一个{@link Iterator}。
	 * Return an {@link Iterator} over the property values.
	 * @since 5.1
	 */
	@Override
	default Iterator<PropertyValue> iterator() {
		return Arrays.asList(getPropertyValues()).iterator();
	}

	/**
	 * 在属性值上返回一个{@link Spliterator}。
	 * Return a {@link Spliterator} over the property values.
	 * @since 5.1
	 */
	@Override
	default Spliterator<PropertyValue> spliterator() {
		return Spliterators.spliterator(getPropertyValues(), 0);
	}

	/**
	 * 返回包含属性值的顺序{@link Stream}。
	 * Return a sequential {@link Stream} containing the property values.
	 * @since 5.1
	 */
	default Stream<PropertyValue> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	/**
	 * 返回此对象中保存的PropertyValue对象的数组。
	 * Return an array of the PropertyValue objects held in this object.
	 */
	PropertyValue[] getPropertyValues();

	/**
	 * 返回具有给定名称的属性值（如果有）。
	 * Return the property value with the given name, if any.
	 * @param propertyName the name to search for	要搜索的名称
	 * @return the property value, or {@code null} if none		属性值；如果没有，则为{@code null}
	 */
	@Nullable
	PropertyValue getPropertyValue(String propertyName);

	/**
	 * Return the changes since the previous PropertyValues.
	 * Subclasses should also override {@code equals}.
	 * @param old old property values
	 * @return the updated or new properties.
	 * Return empty PropertyValues if there are no changes.
	 * @see Object#equals
	 */
	PropertyValues changesSince(PropertyValues old);

	/**
	 * 此属性是否有一个属性值（或其他处理条目）？
	 * Is there a property value (or other processing entry) for this property?
	 * @param propertyName the name of the property we're interested in
	 * @return whether there is a property value for this property
	 */
	boolean contains(String propertyName);

	/**
	 * 此持有人是否根本不包含任何PropertyValue对象？
	 * Does this holder not contain any PropertyValue objects at all?
	 */
	boolean isEmpty();

}
