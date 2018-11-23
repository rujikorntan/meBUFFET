package table;

import java.io.Serializable;

public class Table implements Serializable {

    private String tableNum;
    private String capacity;
    private String available;
    private String status;

    
    public Table(String tableNum, String capacity, String available, String status){
        this.tableNum = tableNum;
        this.capacity = capacity;
        this.available = available;
        this.status = status;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableNum() {
        return tableNum;
    }

    public String getCapacity() {
        return capacity;
    }

    public String isAvailable() {
        return available;
    }

    public String getStatus() {
        return status;
    }


}
