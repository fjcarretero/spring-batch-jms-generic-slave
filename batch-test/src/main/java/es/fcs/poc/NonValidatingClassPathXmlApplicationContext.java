package es.fcs.poc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NonValidatingClassPathXmlApplicationContext extends
		ClassPathXmlApplicationContext {

	public NonValidatingClassPathXmlApplicationContext(String configLocation)
			throws BeansException {
		super(configLocation);
		// TODO Auto-generated constructor stub
	}
	
	protected void initBeanDefinitionReader(XmlBeanDefinitionReader beanDefinitionReader){
		super.initBeanDefinitionReader(beanDefinitionReader);
		beanDefinitionReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		beanDefinitionReader.setNamespaceAware(true);
	}
}
