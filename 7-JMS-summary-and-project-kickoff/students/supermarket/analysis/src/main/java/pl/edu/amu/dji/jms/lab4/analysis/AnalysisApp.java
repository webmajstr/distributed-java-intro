package pl.edu.amu.dji.jms.lab4.analysis;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.analysis.reporting.ReportSvc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import pl.edu.amu.dji.jms.lab4.converter.ProductMsg;

public class AnalysisApp {

    private static final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(System.in));

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    private static final ReportSvc reportSvc = (ReportSvc) context.getBean("reportSvc");

    public static void main(String[] args) throws Exception {

        String input = "";

        System.out.println("Type 'quit' to terminate.\nAvailable options: \n1.Check report");

        while (!input.equalsIgnoreCase("quit")) {
            System.out.println(">");
            input = inputStreamReader.readLine();
            
            if (NumberUtils.isNumber(input)) {
                if (Integer.parseInt(input) == 1) {
                    printReport();
                }
            }
        }
    }

    private static void printReport() {
        Iterator<ProductMsg> iter = reportSvc.getSalesInfo().iterator();
        ProductMsg tmpInfo;
        while (iter.hasNext()) {
            tmpInfo = iter.next();
            System.out.println(tmpInfo.getName() + ": " + tmpInfo.getPrice());
        }

    }
}
