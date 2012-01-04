package es.fcs.poc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class ByteArrayXmlApplicationContext extends AbstractXmlApplicationContext {
	
	private Resource[] configResources;


	/**
	 * Create a new ByteArrayXmlApplicationContext for bean-style configuration.
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public ByteArrayXmlApplicationContext() {
	}

	/**
	 * Create a new ByteArrayXmlApplicationContext for bean-style configuration.
	 * @param parent the parent context
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public ByteArrayXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * Create a new ByteArrayXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * @param configResources resource
	 * @throws BeansException if context creation failed
	 */
	public ByteArrayXmlApplicationContext(byte[] xml) throws BeansException {
		this(new byte[][] {xml}, true, null);
	}

	/**
	 * Create a new ByteArrayXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param configResources array of resources
	 * @throws BeansException if context creation failed
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls) throws BeansException {
		this(xmls, true, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param configLocations array of resource locations
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls, ApplicationContext parent) throws BeansException {
		this(xmls, true, parent);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML files.
	 * @param configLocations array of resource locations
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls, boolean refresh) throws BeansException {
		this(xmls, refresh, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files.
	 * @param configLocations array of resource locations
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls, boolean refresh, ApplicationContext parent)
			throws BeansException {

		super(parent);
		
//		setConfigLocations(configLocations);
		this.configResources = new Resource[xmls.length];
		for (int i = 0; i < xmls.length; i++) {
			this.configResources[i] = new ByteArrayResource(xmls[i]);
		}
		if (refresh) {
			refresh();
		}
	}


	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * <p>This is a convenience method to load class path resources relative to a
	 * given Class. For full flexibility, consider using a GenericApplicationContext
	 * with an XmlBeanDefinitionReader and a ClassPathResource argument.
	 * @param path relative (or absolute) path within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ByteArrayXmlApplicationContext(byte[] xml, Class clazz) throws BeansException {
		this(new byte[][] {xml}, clazz);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param paths array of relative (or absolute) paths within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls, Class clazz) throws BeansException {
		this(xmls, clazz, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param paths array of relative (or absolute) paths within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ByteArrayXmlApplicationContext(byte[][] xmls, Class clazz, ApplicationContext parent)
			throws BeansException {

		super(parent);
		Assert.notNull(xmls, "Path array must not be null");
		Assert.notNull(clazz, "Class argument must not be null");
		this.configResources = new Resource[xmls.length];
		for (int i = 0; i < xmls.length; i++) {
			this.configResources[i] = new ByteArrayResource(xmls[i]);
		}
		refresh();
	}


	protected Resource[] getConfigResources() {
		return this.configResources;
	}
}
