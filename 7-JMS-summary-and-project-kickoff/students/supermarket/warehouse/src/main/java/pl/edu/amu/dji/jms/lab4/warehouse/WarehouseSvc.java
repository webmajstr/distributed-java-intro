package pl.edu.amu.dji.jms.lab4.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Product;
import pl.edu.amu.dji.jms.lab4.converter.ProductList;
import pl.edu.amu.dji.jms.lab4.converter.ProductMsg;

import javax.jms.Destination;
import java.util.List;

@Service("warehouseSvc")
public class WarehouseSvc {
    @Autowired
    @Qualifier("productListJmsTemplate")
    private JmsTemplate productListJmsTemplate;

    @Autowired
    @Qualifier("priceChangeJmsTemplate")
    private JmsTemplate priceChangeJmsTemplate;

    @Autowired
    @Qualifier("productListTopic")
    private Destination productListTopic;

    @Autowired
    @Qualifier("priceChangeTopic")
    private Destination priceChangeTopic;

    @Transactional
    public void sendPriceChange(final String productName, final double price){
        ProductMsg priceChange = new ProductMsg(productName, price);
        priceChangeJmsTemplate.convertAndSend(priceChange);
    }
    @Transactional
    public void sendProductList(final List<Product> productListIn){
        ProductList productList = new ProductList(productListIn);
        productListJmsTemplate.convertAndSend(productList);
    }
}
