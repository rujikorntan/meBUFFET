package table;

import javafx.scene.control.Tab;

import java.io.*;
import java.util.ArrayList;

public class TableDB  {
    private static final long serialVersionUID = 1;

    public TableDB(){
    }
    public void saveTempDB(ArrayList<Table> tableCustomer){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\table\\pretableDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tableCustomer);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public ArrayList<Table> loadTempDB() {
        ArrayList<Table> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\table\\pretableDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Table> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            i.printStackTrace();
            saveTempDB(loadtableCustomer);
            return loadtableCustomer;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadtableCustomer;
    }
    public void saveDB(ArrayList<Table> tableCustomer){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\table\\tableDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tableCustomer);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public ArrayList<Table> loadDB() {
        ArrayList<Table> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\table\\tableDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Table> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            saveDB(loadtableCustomer);
            loadtableCustomer = loadDB();
            
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadtableCustomer;
    }
}
