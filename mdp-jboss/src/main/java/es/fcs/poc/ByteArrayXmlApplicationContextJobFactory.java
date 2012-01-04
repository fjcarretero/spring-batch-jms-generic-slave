package es.fcs.poc;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.support.ApplicationContextJobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


public class ByteArrayXmlApplicationContextJobFactory implements JobFactory {

	private ApplicationContext parent;
	
	private String xml;
	
	private String beanName;
	
	public ByteArrayXmlApplicationContextJobFactory(String beanName, String xml, ApplicationContext parent) {
		super();
		this.beanName = beanName;
		this.xml = xml;
		this.parent = parent;
	}

	
	public Job createJob() {
//		GenericApplicationContext context = new GenericApplicationContext();
//		context.setParent(parent);
//		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
//		xmlReader.loadBeanDefinitions(new ByteArrayResource(xml.getBytes()));
//		context.refresh();
		ByteArrayXmlApplicationContext context = new ByteArrayXmlApplicationContext(new byte[][] {xml.getBytes()}, parent);
		Job job = (Job)context.getBean(beanName, Job.class);
		return new ContextClosingJob(job, context);
	}


	public String getJobName() {
		return beanName;
	}

  	private static class ContextClosingJob implements Job {
  	     		private Job delegate;
  	     		private ConfigurableApplicationContext context;
  	     		/**
  	     		 * @param delegate
  	     		 * @param context
  	     		 */
  	     		public ContextClosingJob(Job delegate, ConfigurableApplicationContext context) {
  	     			super();
  	     			this.delegate = delegate;
  	     			this.context = context;
  	     		}
  	     		/**
  	     		 * @param execution
  	     		 * @throws JobExecutionException
  	     		 * @see org.springframework.batch.core.Job#execute(org.springframework.batch.core.JobExecution)
  	     		 */
  	     		public void execute(JobExecution execution){
  	     			try {
  	     				delegate.execute(execution);
  	     			} finally {
  	     				context.close();
  	     			}
  	     		}
  	     		/**
  	     		 * @return
  	     		 * @see org.springframework.batch.core.Job#getName()
  	     		 */
  	     		public String getName() {
  	     			return delegate.getName();
  	     		}
   	     		/**
  	     		 * @return
  	     		 * @see org.springframework.batch.core.Job#isRestartable()
  	     		 */
  	     		public boolean isRestartable() {
  	     			return delegate.isRestartable();
  	     		}
				
				public JobParametersIncrementer getJobParametersIncrementer() {
					// TODO Auto-generated method stub
					return null;
				}
				public JobParametersValidator getJobParametersValidator() {
					// TODO Auto-generated method stub
					return new VoidJobParametersValidator();
				}
  	    
  	     	}	
}
