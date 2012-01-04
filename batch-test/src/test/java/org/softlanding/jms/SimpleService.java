package org.softlanding.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import junit.framework.TestCase;

/**
 * Simple service that illustrates if you had an
 * application that needed access to the jms queue to set things on that
 * queue using a service.
 */
public class SimpleService extends TestCase {

    private BaseQueue subscriptionQueue;
    private Logger log = Logger.getLogger(SimpleService.class.getName());
    /**
     * Simple method that creates a bunch of messages
     * and sends them to a queue. This queue is injected from
     * the BaseQueue object, that extends spring's jms template.
     * @throws IOException 
     */
    public void testMDP() throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("send-jboss.xml");
    	log.log(Level.INFO, "Simple Service ADDING ID TO QUEUE ");
        subscriptionQueue = (BaseQueue)ctx.getBean("senderBaseQueue");
    	subscriptionQueue.sendText(readfile("footballJob.xml"));
        
    }
    
    /**
     * Sets the jms queue template in this service.
     * @param aQueue spring templated jms queue.
     */
    public void setSubscriptionQueue(BaseQueue aQueue) {
        this. subscriptionQueue = aQueue;
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
