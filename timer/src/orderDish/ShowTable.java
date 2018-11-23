package orderDish;

import java.util.ArrayList;

public class ShowTable {
    ArrayList<String> name_order;
    ArrayList<String> price_order;
    ArrayList<String> amount_order;

    public ShowTable(ArrayList<String> name_order, ArrayList<String> price_order, ArrayList<String> amount_order) {
        this.name_order = name_order;
        this.price_order = price_order;
        this.amount_order = amount_order;
    }

    public ArrayList<String> getName_order() {
        return name_order;
    }

    public void setName_order(ArrayList<String> name_order) {
        this.name_order = name_order;
    }

    public ArrayList<String> getPrice_order() {
        return price_order;
    }

    public void setPrice_order(ArrayList<String> price_order) {
        this.price_order = price_order;
    }

    public ArrayList<String> getAmount_order() {
        return amount_order;
    }

    public void setAmount_order(ArrayList<String> amount_order) {
        this.amount_order = amount_order;
    }
}
