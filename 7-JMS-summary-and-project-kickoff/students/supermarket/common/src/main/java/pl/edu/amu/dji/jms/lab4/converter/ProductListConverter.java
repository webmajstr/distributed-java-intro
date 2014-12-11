package pl.edu.amu.dji.jms.lab4.converter;

import java.io.Serializable;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.Product;

import javax.jms.*;
import java.util.List;

@Component("productListConverter")
public class ProductListConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        ObjectMessage message = session.createObjectMessage();
        message.setObject((Serializable) ((ProductList) o).getProductList());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        List<Product> productList = (List<Product>) objectMessage.getObject();
        return new ProductList(productList);
    }
}
