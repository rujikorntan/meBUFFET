package orderDish;

import java.io.Serializable;

public class TempDish implements Serializable{
    
    private String name_dish;
    private int amount_dish;
    private int price_dish;
    private String type_dish;

    public TempDish(){}
    
    public TempDish(String name_dish,String type,int amount_dish, int price) {
        this.name_dish = name_dish;
        this.amount_dish = amount_dish;
        this.price_dish = price;
        this.type_dish = type;
    }
    
    public String getType_dish() {
        return type_dish;
    }

    public void setType_dish(String type_dish) {
        this.type_dish = type_dish;
    }

    public int getPrice_dish() {
        return price_dish;
    }

    public void setPrice_dish(int price_dish) {
        this.price_dish = price_dish;
    }

    public String getName_dish() {
        return name_dish;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public int getAmount_dish() {
        return amount_dish;
    }

    public void setAmount_dish(int amount_dish) {
        this.amount_dish = amount_dish;
    }
}
