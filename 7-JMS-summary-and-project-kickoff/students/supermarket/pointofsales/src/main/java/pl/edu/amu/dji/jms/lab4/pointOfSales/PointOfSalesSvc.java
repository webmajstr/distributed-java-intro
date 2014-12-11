package pl.edu.amu.dji.jms.lab4.pointOfSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Product;
import pl.edu.amu.dji.jms.lab4.converter.ProductList;
import pl.edu.amu.dji.jms.lab4.converter.ProductMsg;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service("pointOfSalesSvc")
public class PointOfSalesSvc {

    private Map<String, Product> products = new HashMap<String, Product>();

    @Autowired
    @Qualifier("reportJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("reportQueue")
    private Destination reportQueue;

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    @Transactional
    public void setNewProductList(final ProductList productList) {
        products.clear();

        Iterator<Product> iter = productList.getProductList().iterator();

        Product tmpProduct;
        while (iter.hasNext()) {
            tmpProduct = iter.next();
            products.put(tmpProduct.getName(), tmpProduct);
        }
    }

    @Transactional
    public void setNewPrice(final ProductMsg productMsg) {
        Product product = products.get(productMsg.getName());

        if (product != null) {
            product.setPrice(productMsg.getPrice());
            products.put(product.getName(), product);
        }

    }

    @Transactional
    public void sendSalesInfo(final Product product) {
        jmsTemplate.convertAndSend(new ProductMsg(product.getName(), product.getPrice()));
    }
}
