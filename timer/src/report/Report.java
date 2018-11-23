package report;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Report implements Serializable {
    private String report_dateTime;
    private String report_dishName;
    private int report_amount;
    private int report_totalPrice;

    public Report(){}
    public Report(String report_dateTime, String report_dishName, int report_amount, int report_totalPrice) {
        this.report_dateTime = report_dateTime;
        this.report_dishName = report_dishName;
        this.report_amount = report_amount;
        this.report_totalPrice = report_totalPrice;
    }

    public String getReport_dateTime() {
        return report_dateTime;
    }

    public void setReport_dateTime(String report_dateTime) {
        this.report_dateTime = report_dateTime;
    }

    public String getReport_dishName() {
        return report_dishName;
    }

    public void setReport_dishName(String report_dishName) {
        this.report_dishName = report_dishName;
    }

    public int getReport_amount() {
        return report_amount;
    }

    public void setReport_amount(int report_amount) {
        this.report_amount = report_amount;
    }

    public int getReport_totalPrice() {
        return report_totalPrice;
    }

    public void setReport_totalPrice(int report_totalPrice) {
        this.report_totalPrice = report_totalPrice;
    }
}
