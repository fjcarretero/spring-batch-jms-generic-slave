package es.fcs.poc;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;


public class NonValidatingContextLoader extends GenericXmlContextLoader {
	
	protected BeanDefinitionReader createBeanDefinitionReader(final GenericApplicationContext context) {
		XmlBeanDefinitionReader reader = (XmlBeanDefinitionReader)super.createBeanDefinitionReader(context);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		reader.setNamespaceAware(true);
		return reader;
	}
}
