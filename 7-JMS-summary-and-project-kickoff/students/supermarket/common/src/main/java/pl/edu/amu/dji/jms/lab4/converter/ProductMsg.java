package pl.edu.amu.dji.jms.lab4.converter;

public class ProductMsg {

    private String name;
    private Double price;

    public ProductMsg(String productName, Double price) {
        this.name = productName;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setNewPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
