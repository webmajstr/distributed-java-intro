package pl.edu.amu.dji.jms.lab4.converter;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component("productMsgConverter")
public class ProductMsgConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        MapMessage message = session.createMapMessage();
        message.setDouble("price", ((ProductMsg) o).getPrice());
        message.setString("name", ((ProductMsg) o).getName());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        String name = mapMessage.getString("name");
        double price = mapMessage.getDouble("price");
        return new ProductMsg(name, price);
    }
}
