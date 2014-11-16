package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.springframework.transaction.annotation.Transactional;

public class OrderService implements MessageListener {

    @Override
    @Transactional
    public void onMessage(Message message) {
        Message msg = (MapMessage)message;
        try {
            System.out.println(msg.getStringProperty("retailerID") + " " + Double.toString(msg.getDoubleProperty("quantity")));
        } catch (JMSException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
