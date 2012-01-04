package org.softlanding.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.fcs.poc.ByteArrayXmlApplicationContextJobFactory;
import es.fcs.poc.NonValidatingContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:job-launcher-context.xml"}, loader=NonValidatingContextLoader.class)
public class testJMS extends AbstractJUnit4SpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	@Test public void test() throws DuplicateJobException{
		try {
	        String xml = readfile("footballRemoteJob.xml");
	        System.out.println("2");
	        ByteArrayXmlApplicationContextJobFactory factory = new ByteArrayXmlApplicationContextJobFactory("footballJob", xml, applicationContext);
	        
	        System.out.println("3");
			JobParametersBuilder jpb = new JobParametersBuilder();
			jpb.addDouble("job.commit.interval", new Double(10))
				.addString("games.file.name", "file:C:/$felipe/$WKS/spring-batch/poc-spring-batch/src/main/resources/data/footballjob/input/games-small.csv")
				.addString("player.file.name", "file:C:/$felipe/$WKS/spring-batch/poc-spring-batch/src/main/resources/data/footballjob/input/player-small2.csv")
				.addString("chunkyStep", "gameLoad");
			jobRegistry.register(factory);
			Job job = factory.createJob();
			System.out.println("------------------------------" + job.getName());
			jobLauncher.run(job, jpb.toJobParameters());//new JobParameters(kk));
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private String readfile(String string) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		Resource jobxml = new ClassPathResource(string);
		BufferedReader br = new BufferedReader(new InputStreamReader(jobxml.getInputStream()));
		String a = "";
		while((a = br.readLine())!=null){
			sb.append(a);
		}
		
		return sb.toString();
	}
}
