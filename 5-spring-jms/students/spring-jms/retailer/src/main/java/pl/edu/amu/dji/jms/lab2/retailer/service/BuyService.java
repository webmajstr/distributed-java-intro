package pl.edu.amu.dji.jms.lab2.retailer.service;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        Message msg = (MapMessage)message;
        final Random generator = new Random();
        Double price;
        try {
            price = msg.getDoubleProperty("price");
            if (this.maxPrice.compareTo(price) == 1) {
                this.jmsTemplate.send(message.getJMSReplyTo(), new MessageCreator() {

            public Message createMessage(Session sn) throws JMSException {
                Message msg = sn.createMapMessage();
                msg.setStringProperty("retailerID", this.getClass().getName());
                msg.setDoubleProperty("quantity", generator.nextDouble());
                return msg;
            }
        });
            }
        } catch (JMSException ex) {
            Logger.getLogger(BuyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
