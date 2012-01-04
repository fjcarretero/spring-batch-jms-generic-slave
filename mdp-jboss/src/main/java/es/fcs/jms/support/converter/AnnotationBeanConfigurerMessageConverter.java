package es.fcs.jms.support.converter;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotationBeanWiringInfoResolver;
import org.springframework.beans.factory.wiring.BeanConfigurerSupport;
import org.springframework.jms.support.converter.SimpleMessageConverter;

public class AnnotationBeanConfigurerMessageConverter extends SimpleMessageConverter implements BeanFactoryAware, InitializingBean {

	private BeanConfigurerSupport beanConfigurerSupport = new BeanConfigurerSupport();

	private BeanFactory beanFactory;
	

	@Override
	protected Serializable extractSerializableFromMessage(ObjectMessage message)
			throws JMSException {
		Serializable bean = super.extractSerializableFromMessage(message);
		System.out.println("-------------KKOTA----------" + bean.getClass());
		beanConfigurerSupport.configureBean(bean);
		
		return bean;
	}

	public void afterPropertiesSet() throws Exception {
		beanConfigurerSupport.setBeanFactory(beanFactory);
		beanConfigurerSupport.setBeanWiringInfoResolver(new AnnotationBeanWiringInfoResolver());
		beanConfigurerSupport.afterPropertiesSet();
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
