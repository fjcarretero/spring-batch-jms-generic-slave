package org.softlanding.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import es.fcs.poc.ByteArrayXmlApplicationContextJobFactory;
import es.fcs.poc.NonValidatingClassPathXmlApplicationContext;

public class CopyOftestJMS extends TestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		System.setProperty("http.proxySet", "true");
//		System.setProperty("http.proxyHost", "proxy.es.mapfre.net");
//		System.setProperty("http.proxyPort", "80");
//		System.setProperty("http.proxyUserName", "DES000\\felcarr");
//		System.setProperty("http.proxyUser", "DES000\\felcarr");
//		System.setProperty("http.proxyPassword", "Fcs0111");	
//		AbstractBeanFactory
	}

	public void test(){
		try {
	        System.out.println("hola");
			ApplicationContext parent = new NonValidatingClassPathXmlApplicationContext("WEB-INF/mdp-job-launcher-context-2.xml");
	        JobLauncher jl = (JobLauncher)parent.getBean("jobLauncher");
			JobParametersBuilder jpb = new JobParametersBuilder();
			jpb.addDouble("job.commit.interval", new Double(50))
				.addString("games.file.name", "file:C:/temp/games-small.csv")
				.addString("player.file.name", "file:C:/temp/player-small1.csv");
			Job job = (Job)parent.getBean("footballJob");
			System.out.println("------------------------------" + job.getName());
			jl.run(job, jpb.toJobParameters());//new JobParameters(kk));
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
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
