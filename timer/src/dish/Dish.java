package dish;

import stock.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;

public class Dish implements Serializable {
    private String name;
    private String type;
    private String price;
    private String status;
    private String picPath;
    private int amount;
    private ArrayList<Ingredient> ingredients;

    public Dish(String name,String type,String price,String picPath,ArrayList<Ingredient> ingredients){
        this.name = name;
        this.type = type;
        this.price = price;
        this.picPath = picPath;
        this.ingredients = ingredients;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
