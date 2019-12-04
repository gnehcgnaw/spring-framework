/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.context.annotation;

import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Standalone application context, accepting <em>component classes</em> as input &mdash;
 * in particular {@link Configuration @Configuration}-annotated classes, but also plain
 * {@link org.springframework.stereotype.Component @Component} types and JSR-330 compliant
 * classes using {@code javax.inject} annotations.
 *
 * <p>Allows for registering classes one by one using {@link #register(Class...)}
 * as well as for classpath scanning using {@link #scan(String...)}.
 *
 * <p>In case of multiple {@code @Configuration} classes, {@link Bean @Bean} methods
 * defined in later classes will override those defined in earlier classes. This can
 * be leveraged to deliberately override certain bean definitions via an extra
 * {@code @Configuration} class.
 *
 * <p>See {@link Configuration @Configuration}'s javadoc for usage examples.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 3.0
 * @see #register
 * @see #scan
 * @see AnnotatedBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.support.GenericXmlApplicationContext
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

	private final AnnotatedBeanDefinitionReader reader;

	private final ClassPathBeanDefinitionScanner scanner;

	protected final Log logger = LogFactory.getLog(getClass());
	/**
	 * AnnotationConfigApplicationContext的继承关系如下所示：
	 * 		1. ${@link GenericApplicationContext}
	 * 			2. ${@link org.springframework.context.support.AbstractApplicationContext }
	 * 				3. ${@link org.springframework.core.io.DefaultResourceLoader}	,
	 * 		故初始化的过程是 3 -》2 -》1 ——》AnnotationConfigApplicationContext
	 * 其中最重要的是在GenericApplicationConext的无参构造中，初始化了一个bean的工厂{@link DefaultListableBeanFactory};
	 *
	 * Create a new AnnotationConfigApplicationContext that needs to be populated
	 * through {@link #register} calls and then manually {@linkplain #refresh refreshed}.
	 */
	public AnnotationConfigApplicationContext() {
		logger.info("AnnotationConfigApplicationContext constructor execution");
		/**
		 * 发现在初始化AnnotatedBeanDefinitionReader的时候需要一个注册器，而传入的值是this ，那么表明 AnnotationConfigApplicationContext肯定是注册器的一个实现i类，
		 * 通过查看AnnotationConfigApplicationContext的接口，发现如下所示：
		 * 		 ${@link AnnotationConfigRegistry }继承了${@link GenericApplicationContext} ,
		 * 		 而GenericApplicationContext实现了${@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
		 */
		if (this instanceof BeanDefinitionRegistry){
			logger.info("AnnotationConfigApplicationContext is ths implementation class of  BeanDefinitionRegistry");
		}
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 创建一个AnnotationConfigApplicationContext使用自定义的一个DefaultListableBeanFactory，
	 * 这个DefaultListableBeanFactory将一直运用于应用的上下文。
	 * Create a new AnnotationConfigApplicationContext with the given DefaultListableBeanFactory.
	 * @param beanFactory the DefaultListableBeanFactory instance to use for this context
	 */
	public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		/**
		 * 为什么会有一句super(beanFactory)呢（{@link GenericApplicationContext#GenericApplicationContext(DefaultListableBeanFactory)}）？
		 * 因为这个beanFactory是我们自定义传入的，而GenericApplicationContext类中，其实就初始化了一个DefaultListableBeanFactory ，而我们现在要使用自己的，
		 * 所以要将值覆盖掉。
		 * 因此调用super(beanFactory)，主要做一下事情：
		 *	 1. 第一步做的是断言，也就是参数的校验；
		 * 	 2. {@link GenericApplicationContext#beanFactory} = 传入的beanFactory 。
		 */
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 由代码我可以发现，其入参是一个可变的配置类的数组，同时我也发现这个构造方法的作用了，其实就是一部到位。
	 * 什么是一部到位呢?
	 * 		其实就是初始化AnnotationConfigApplicationContext；
	 * 		注册配置类；
	 * 		刷新初始话bean；
	 * 	这三步同时在此构造方法中一并完成，不用像上面两个构造方法一样，后续还要进行分步调用，手动刷新。
	 * Create a new AnnotationConfigApplicationContext, deriving bean definitions
	 * from the given component classes and automatically refreshing the context.
	 * @param componentClasses one or more component classes &mdash; for example,
	 * {@link Configuration @Configuration} classes
	 */
	public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
		/**
		 * 有参构造不会自动调用无参构造的，那这里为什么要调用无参构造呢？
		 * 		其实往往，我们在有参构造中调用无参够着，是为了初始化一些参数的值，
		 * 那会有人问，为什么不在调用有参构造的时候，一并初始化呢？
		 *		由以下注释掉的渣渣代码，可以看出，我们又多写了两行的冗余代码
		 *				this.reader = new AnnotatedBeanDefinitionReader(this);
		 * 				this.scanner = new ClassPathBeanDefinitionScanner(this);
		 * 			没有this()简洁。
		 */
		this();
		register(componentClasses);
		refresh();
	}

	/**
	 * 自己写的渣渣代码
	 * @param componentClasses
	 *//*
	public AnnotationConfigApplicationContext(Class<?> ... componentClasses ){
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
		register(componentClasses);
		refresh();
	}*/

	/**
	 * 这种参数是一个字符串，也就是传入的参数不是一个可变的配置类，我们首先要进行扫描包操作，然后进行刷新，当然了这两步也是在这个
	 * 构造方法中一并调用的，和{@link #AnnotationConfigApplicationContext(Class[])}差不多。
	 * Create a new AnnotationConfigApplicationContext, scanning for components
	 * in the given packages, registering bean definitions for those components,
	 * and automatically refreshing the context.
	 * @param basePackages the packages to scan for component classes
	 */
	public AnnotationConfigApplicationContext(String... basePackages) {
		this();
		scan(basePackages);
		refresh();
	}


	/**
	 * 将给定的自定义{@code Environment}传播到底层
	 * 我们可以不进行自定义环境的设置，因为在AnnotationConfigApplicationContext后续的初始化过程中，spring会去进行如下操作：
	 * 		{@link AnnotatedBeanDefinitionReader#getOrCreateEnvironment(BeanDefinitionRegistry)}
	 * 		{@link ClassPathBeanDefinitionScanner#getOrCreateEnvironment(BeanDefinitionRegistry)}
	 * 也就是说，即便使我们不自定义初始化环境信息，后续也会获取程序运行环境信息，如果没有就添加一个默认的。
	 * Propagate the given custom {@code Environment} to the underlying
	 *
	 * {@link AnnotatedBeanDefinitionReader} and {@link ClassPathBeanDefinitionScanner}.
	 */
	@Override
	public void setEnvironment(ConfigurableEnvironment environment) {
		super.setEnvironment(environment);
		this.reader.setEnvironment(environment);
		this.scanner.setEnvironment(environment);
	}

	/**
	 * 设置自定义的BeanNameGenerator
	 * 当然如果我们没有设置自定义的BeanNameGenerator，Spring也会在后续的初始化过程中进行设置。
	 * Provide a custom {@link BeanNameGenerator} for use with {@link AnnotatedBeanDefinitionReader}
	 * and/or {@link ClassPathBeanDefinitionScanner}, if any.
	 * <p>Default is {@link org.springframework.context.annotation.AnnotationBeanNameGenerator}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 * @see AnnotatedBeanDefinitionReader#setBeanNameGenerator
	 * @see ClassPathBeanDefinitionScanner#setBeanNameGenerator
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		this.reader.setBeanNameGenerator(beanNameGenerator);
		this.scanner.setBeanNameGenerator(beanNameGenerator);
		getBeanFactory().registerSingleton(
				AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
	}

	/**
	 * 设置ScopeMetadata解析器。
	 * 但是设置ScopeMetadata解析器必须在调用{@link #register(Class...)}或{@link #scan(String...)}之前，这是为什么呢？
	 *
	 * Set the {@link ScopeMetadataResolver} to use for registered component classes.
	 * <p>The default is an {@link AnnotationScopeMetadataResolver}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 */
	public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
		this.reader.setScopeMetadataResolver(scopeMetadataResolver);
		this.scanner.setScopeMetadataResolver(scopeMetadataResolver);
	}


	//---------------------------------------------------------------------
	// Implementation of AnnotationConfigRegistry
	// 以下方法实现的是AnnotationConfigRegistry中的方法
	//---------------------------------------------------------------------

	/**
	 * Register one or more component classes to be processed.
	 * 注册一个或多个要处理的组件类 ,例如：spring-learning中的${@link red.reksai.dao.UserDao}或者${@link red.reksai.config.AppConfig},
	 * 换句话说这个方法不仅可以注册配置类，也可以注册单个bean。
	 * 在注册之后，必须调用{@link #refresh()} 才能完全解析上下文。
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 * @param componentClasses one or more component classes &mdash; for example, {@link Configuration @Configuration} classes ，参数类型是一个可变的数组
	 * @see #scan(String...)
	 * @see #refresh()
	 */
	@Override
	public void register(Class<?>... componentClasses) {
		Assert.notEmpty(componentClasses, "At least one component class must be specified");
		this.reader.register(componentClasses);
	}

	/**
	 * 在指定的基本程序包中执行扫描。
	 * 这种方法我们其实很少使用，因为我们现在编程大多数情况采用的都是JavaConfig的形式，
	 * 这个方法很少使用就导致了ClassPathBeanDefinitionScanner很少使用，那么其实bean的注册工作大部分情况都是有
	 * {@link AnnotatedBeanDefinitionReader#register(Class[])}来完成的。
	 * Perform a scan within the specified base packages.
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 * @param basePackages the packages to scan for component classes
	 * @see #register(Class...)
	 * @see #refresh()
	 */
	@Override
	public void scan(String... basePackages) {
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		this.scanner.scan(basePackages);
	}


	//---------------------------------------------------------------------
	// Convenient methods for registering individual beans
	//---------------------------------------------------------------------

	/**
	 * {@link AnnotationConfigApplicationContext#registerBean(Class, Object...)}
	 * Register a bean from the given bean class, deriving its metadata from
	 * class-declared annotations, and optionally providing explicit constructor
	 * arguments for consideration in the autowiring process.
	 * <p>The bean name will be generated according to annotated component rules.
	 * @param beanClass the class of the bean
	 * @param constructorArguments argument values to be fed into Spring's
	 * constructor resolution algorithm, resolving either all arguments or just
	 * specific ones, with the rest to be resolved through regular autowiring
	 * (may be {@code null} or empty)
	 * @since 5.0
	 */
	public <T> void registerBean(Class<T> beanClass, Object... constructorArguments) {
		registerBean(null, beanClass, constructorArguments);
	}

	/**
	 * Register a bean from the given bean class, deriving its metadata from
	 * class-declared annotations, and optionally providing explicit constructor
	 * arguments for consideration in the autowiring process.
	 * @param beanName the name of the bean (may be {@code null})
	 * @param beanClass the class of the bean
	 * @param constructorArguments argument values to be fed into Spring's
	 * constructor resolution algorithm, resolving either all arguments or just
	 * specific ones, with the rest to be resolved through regular autowiring
	 * (may be {@code null} or empty)
	 * @since 5.0
	 */
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass, Object... constructorArguments) {
		this.reader.doRegisterBean(beanClass, null, beanName, null,
				bd -> {
					for (Object arg : constructorArguments) {
						bd.getConstructorArgumentValues().addGenericArgumentValue(arg);
					}
				});
	}

	@Override
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass, @Nullable Supplier<T> supplier,
			BeanDefinitionCustomizer... customizers) {

		this.reader.doRegisterBean(beanClass, supplier, beanName, null, customizers);
	}

}
