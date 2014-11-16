/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

/**
 *
 * @author Ozga
 */
public class Order {
    private Integer quantity;
    private String retailerId;

    public Order(Integer quantity, String retailerId) {
        this.quantity = quantity;
        this.retailerId = retailerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }
    
    
}
