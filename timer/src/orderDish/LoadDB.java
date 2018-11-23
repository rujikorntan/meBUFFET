package orderDish;
import table.Table;
import table.TableDB;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadDB implements Serializable {

    private ArrayList<Table> tableList = new ArrayList<>();
    private TableDB databaseTable = new TableDB();
    private ArrayList<TempDish> dishList = new ArrayList<>();

    public LoadDB() {

        TempDish dish1 = new TempDish("Beacon with soup", "bra",20, 200);
        TempDish dish2 = new TempDish("Meetball","brabra", 20, 320);
        TempDish dish3 = new TempDish("Egg star","fried", 30, 150);
        TempDish dish4 = new TempDish("Pad kra pao","fried", 30, 50);
        TempDish dish5 = new TempDish("Pork","fried", 30, 500);

        dishList.add(dish1);
        dishList.add(dish2);
        dishList.add(dish3);
        dishList.add(dish4);
        dishList.add(dish5);

        tableList = databaseTable.loadDB();

    }

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public ArrayList<TempDish> getDishList() {
        return dishList;
    }
}
