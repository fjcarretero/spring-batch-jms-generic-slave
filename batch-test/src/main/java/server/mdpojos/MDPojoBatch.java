package server.mdpojos;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import es.fcs.poc.ByteArrayXmlApplicationContextJobFactory;

public class MDPojoBatch implements MessageListener, ApplicationContextAware {

	private ApplicationContext parent;
	
	public void onMessage(Message arg0) {
		
		try {
			String xml = ((TextMessage)arg0).getText();
			System.out.println("xml=" + xml);
//			ApplicationContext parent = new ClassPathXmlApplicationContext("simple-job-launcher-context.xml");
			ByteArrayXmlApplicationContextJobFactory factory = new ByteArrayXmlApplicationContextJobFactory("footballJob", xml, parent);
			JobLauncher jl = (JobLauncher)parent.getBean("jobLauncher");
			JobParametersBuilder jpb = new JobParametersBuilder();
			jpb.addDouble("job.commit.interval", new Double(50))
				.addString("games.file.name", "file:C:/temp/games-small.csv")
				.addString("player.file.name", "file:C:/temp/player-small1.csv");
//			Map<String, JobParameter> kk = new HashMap<String, JobParameter>();
//			kk.put("job.commit.interval", new JobParameter((double)50));
//			kk.put("games.file.name", new JobParameter("file:C:/temp/games-small.csv"));
//			kk.put("player.file.name", new JobParameter("file:C:/temp/player-small1.csv"));
			jl.run(factory.createJob(), jpb.toJobParameters());//new JobParameters(kk));
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.out.println("init");
		parent = arg0;
	}
}
