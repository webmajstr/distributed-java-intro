package pl.edu.amu.dji.jms.lab4;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.warehouse.WarehouseSvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WarehouseApp {

    private static final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(System.in));
    private static final Map<String, Product> products = new HashMap<String, Product>();
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static final WarehouseSvc warehouseService = (WarehouseSvc) context.getBean("warehouseSvc");

    private static void printProductList() {

        System.out.println("List of products");

        Iterator<Product> iter = products.values().iterator();

        Product tmpProduct;
        while (iter.hasNext()) {
            tmpProduct = iter.next();
            System.out.println(tmpProduct.getName() + " " + tmpProduct.getPrice() + "$");
        }
    }

    private static void addProduct() throws IOException {
        System.out.print("Name: ");
        String name = inputStreamReader.readLine();
        
        System.out.print("Price: ");
        String priceString = inputStreamReader.readLine();
        double price = Double.parseDouble(priceString);
        
        products.put(name, new Product(name, price));
        warehouseService.sendProductList(Lists.newArrayList(products.values()));
    }

    private static void deleteProduct() throws IOException {
        System.out.print("Name: ");
        String name = inputStreamReader.readLine();
        
        products.remove(name);
        warehouseService.sendProductList(Lists.newArrayList(products.values()));
    }

    private static void changeProductPrice() throws IOException {
        System.out.print("Name: ");
        String name = inputStreamReader.readLine();
        
        System.out.print("Enter new product price: ");
        String priceString = inputStreamReader.readLine();
        double price = Double.parseDouble(priceString);
        
        products.put(name, new Product(name, price));
        warehouseService.sendPriceChange(name, price);
    }

    private static void sendProductList() throws IOException {
        warehouseService.sendProductList(Lists.newArrayList(products.values()));
    }

    public static void main(String[] args) throws Exception {

        String input = "";

        System.out.println("Type 'quit' to terminate.\nAvailable options: \n1. Add product\n2. Change product's price\n3. Delete product\n4. Send product list to points of sales\n5. Show product list");

        while (!input.equalsIgnoreCase("quit")) {
            System.out.println(">");
            input = inputStreamReader.readLine();
            if (NumberUtils.isNumber(input)) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        changeProductPrice();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        sendProductList();
                        break;
                    case 5:
                        printProductList();
                        break;
                }
            }
        }

    }
}
