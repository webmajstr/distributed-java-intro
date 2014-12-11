package pl.edu.amu.dji.jms.lab4;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.pointOfSales.PointOfSalesSvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class PointOfSalesApp {

    private static BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(System.in));

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static final PointOfSalesSvc pointOfSalesService = (PointOfSalesSvc) context.getBean("pointOfSalesSvc");

    private static void printProductList() {
        System.out.println("List of products");
        Iterator<Product> iter = pointOfSalesService.getProducts().values().iterator();

        Product tmpProduct;
        while (iter.hasNext()) {
            tmpProduct = iter.next();
            System.out.println(tmpProduct.getName() + " " + tmpProduct.getPrice());
        }

    }

    private static void buy() throws IOException {
        System.out.print("Name: ");
        String name = inputStreamReader.readLine();
        Product product = pointOfSalesService.getProducts().get(name);
        if (product != null) {
            pointOfSalesService.sendSalesInfo(product);
        }

    }

    public static void main(String[] args) throws Exception {

        String input = "";

        System.out.println("Type 'quit' to terminate.\nAvailable options: \n1. Buy product\n2. Print product list");

        while (!input.equalsIgnoreCase("quit")) {
            
            System.out.println(">");
            input = inputStreamReader.readLine();
            if (NumberUtils.isNumber(input)) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        buy();
                        break;
                    case 2:
                        printProductList();
                        break;
                             
                }
            }
        }
    }
}
