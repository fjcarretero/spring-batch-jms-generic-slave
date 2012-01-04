package org.softlanding.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * This uses the JMSTemplate in spring this will take
 * messages and send them to the jms queue. See in the
 * jms-beans.xml how we configure this bean.
 */
public class BaseQueue extends JmsTemplate  {

    /**
     * Sends a text message to the queue.
     * @param message the string message want to put on the jms queue
     */
    public void sendText( final String message ) {
        this.send( new MessageCreator() {
            public Message createMessage( Session session ) throws JMSException {
                return session.createTextMessage( message );
            }
        } );
    }
}
