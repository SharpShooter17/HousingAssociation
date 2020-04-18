package pl.dmcs.bujazdowski.service;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.domain.report.Column;
import pl.dmcs.bujazdowski.exception.ApplicationException;

import java.time.LocalDate;
import java.util.*;

import static ar.com.fdvs.dj.domain.AutoText.*;

@Service
public class ReportService {

    private final Set<Column> billColumns = new HashSet<>();
    private final Column typeColumn;
    private final Column dateColumn;
    private final Column amountColumn;
    private final MessageSource messageSource;

    @Autowired
    public ReportService(MessageSource messageSource) {
        this.messageSource = messageSource;

        typeColumn = new Column(String.class, "type", "label.type");
        dateColumn = new Column(LocalDate.class, "date", "label.date");
        amountColumn = new Column(Double.class, "amount", "label.amount");

        billColumns.add(typeColumn);
        billColumns.add(dateColumn);
        billColumns.add(amountColumn);
    }

    byte[] prepareReport(Bill bill) {
        try {
            DynamicReportBuilder drb = new DynamicReportBuilder();
            drb.setDefaultEncoding("UTF-8");
            drb.setPrintColumnNames(true);
            drb.setWhenResourceMissing(DJConstants.WHEN_RESOURCE_MISSING_TYPE_EMPTY);
            drb.addAutoText(AUTOTEXT_PAGE_X_SLASH_Y, POSITION_FOOTER, ALIGMENT_CENTER);
            drb.setHeaderHeight(30);
            drb.setAllowDetailSplit(false);
            drb.setDetailHeight(30);
            drb.setUseFullPageWidth(true);
            drb.setDefaultStyles(buildTitleStyle(), buildSubtitle(), buildHeaderColumnStyle(), buildColumnStyle());

            drb.setTitle(message("report.header") + ": " + bill.getApartment().displayName());
            drb.setReportName(bill.displayName());

            drb.setTopMargin(5);

            billColumns.forEach(column -> drb.addColumn(prepareColumn(column)));
            List<Map<String, Object>> dataSource = prepareValues(bill);

            DynamicReport dr = drb.build();

            JRDataSource ds = new JRBeanCollectionDataSource(dataSource);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds, parameters);
            return JasperExportManager.exportReportToPdf(jp);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    private List<Map<String, Object>> prepareValues(Bill bill) {
        List<Map<String, Object>> rows = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put(typeColumn.property, message("billing.type." + bill.getType().name()));
        row.put(dateColumn.property, bill.getDate());
        row.put(amountColumn.property, bill.getAmount());
        rows.add(row);
        return rows;
    }

    private AbstractColumn prepareColumn(Column column) {
        return ColumnBuilder.getNew()
                .setColumnProperty(column.property, column.type)
                .setTitle(message(column.header))
                .build();
    }

    private Style buildColumnStyle() {
        return new StyleBuilder(false)
                .setName("columnStyle")
                .setHorizontalAlign(HorizontalAlign.CENTER)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .build();
    }

    private Style buildHeaderColumnStyle() {
        return new StyleBuilder(false)
                .setName("headerColumnStyle")
                .setHorizontalAlign(HorizontalAlign.CENTER)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .build();
    }

    private Style buildTitleStyle() {
        return new StyleBuilder(false)
                .setName("titleStyle")
                .build();
    }

    private Style buildSubtitle() {
        return new StyleBuilder(false)
                .setName("subtitleStyle")
                .build();
    }

    private String message(String code) {
        return messageSource.getMessage(code, null, locale());
    }

    private Locale locale() {
        return LocaleContextHolder.getLocale();
    }
}
