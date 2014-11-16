/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import javax.jms.MapMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;

/**
 *
 * @author Ozga
 */
public class OfferConverter implements MessageConverter {

    public Object fromMessage(Message<?> msg, Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Message<?> toMessage(Object o, MessageHeaders mh) {
        Offer offer = (Offer) o;
//        this.
//        Message msg = new Message();
//                msg.setStringProperty("retailerID", this.getClass().getName());
//                msg.setDoubleProperty("quantity", generator.nextDouble());
//                return msg;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
