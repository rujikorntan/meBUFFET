package report;

import dish.Dish;

import java.util.ArrayList;

public class ReportDate {

    private ArrayList<ReportDish> orderDish = new ArrayList<>();
    private String date ;
    public ReportDate(){}

    public ReportDate(String date) {
        this.date = date;
    }

    public ArrayList<ReportDish> getOrderDish() {
        return orderDish;
    }
    public ReportDish getDish(int index) {
        return orderDish.get(index);
    }
    public void setOrderDish(ArrayList<ReportDish> orderDish) {
        this.orderDish = orderDish;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int size(){
        return orderDish.size();
    }
    public void addDish(ReportDish dish){
        orderDish.add(dish);
    }

}
