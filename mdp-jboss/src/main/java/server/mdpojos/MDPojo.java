package server.mdpojos;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class MDPojo implements MessageListener {

	public void onMessage(Message arg0) {
		
		try {
			String msg = ((TextMessage)arg0).getText();
			System.out.println("Hello " + msg);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
