package pl.edu.amu.dji.jms.lab4.converter;

import java.util.ArrayList;
import pl.edu.amu.dji.jms.lab4.Product;

import java.util.List;

public class ProductList {

    private List<Product> productList = new ArrayList<Product>();

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
