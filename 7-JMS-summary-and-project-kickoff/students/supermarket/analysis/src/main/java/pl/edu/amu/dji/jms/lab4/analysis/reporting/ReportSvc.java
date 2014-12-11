package pl.edu.amu.dji.jms.lab4.analysis.reporting;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import pl.edu.amu.dji.jms.lab4.converter.ProductMsg;

@Service("reportSvc")
public class ReportSvc {

    private List<ProductMsg> salesInfo = new ArrayList<ProductMsg>();

    @Transactional
    public void addSalesInfo(final ProductMsg saleInfo) {
        this.salesInfo.add(saleInfo);
    }

    public List<ProductMsg> getSalesInfo() {
        return salesInfo;
    }

    public void setSalesInfo(List<ProductMsg> saleInfo) {
        this.salesInfo = saleInfo;
    }

}
