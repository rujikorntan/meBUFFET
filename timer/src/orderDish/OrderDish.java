
package orderDish;

import dish.Dish;
import java.io.Serializable;
import java.util.ArrayList;

public class OrderDish implements Serializable {
    
    private String billno="0";
    private String tno;
    private String timeActive;
    private String member="";
    private ArrayList<Dish> orderDish = new ArrayList<>();
    
    
    
    public OrderDish() {}
    
    public OrderDish(String tno) {
        this.tno = tno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
    
    public void addDish(Dish dish){
        orderDish.add(dish);
    }
    
    public int size(){
        return orderDish.size();
    }
    
    public Dish getDish(int index){
        return orderDish.get(index);
    }
    
    public void clearOrder(){
        orderDish.clear();
    }
    
    public ArrayList<Dish> getOrderDishList(){
        return orderDish;
    }

    public String getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(String timeActive) {
        this.timeActive = timeActive;
    }
    
    public void setMember(String member){
        this.member = member;
    }
    
    public String getMember(){
        return member;
    }
}

