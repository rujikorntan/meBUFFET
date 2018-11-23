package stock;

import stock.Ingredient;

import java.io.*;
import java.util.ArrayList;

public class StorageDB{
    public StorageDB(){
    }

    public void saveTempDB(ArrayList<Ingredient> tableCustomer){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\stock\\preStorageDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tableCustomer);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public ArrayList<Ingredient> loadTempDB() {
        ArrayList<Ingredient> loadtableCustomer = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\stock\\preStorageDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Ingredient> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Egggg class not found");
            c.printStackTrace();
        }
        return loadtableCustomer;
    }
    public void saveDB(ArrayList<Ingredient> tableCustomer){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\stock\\storageDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tableCustomer);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public ArrayList<Ingredient> loadDB() {
        ArrayList<Ingredient> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\stock\\storageDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Ingredient> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            saveDB(loadtableCustomer);
            loadtableCustomer = loadDB();
        } catch (ClassNotFoundException c) {
            System.out.println("Edfghjkee class not found");
            c.printStackTrace();
        }
        return loadtableCustomer;
    }
}
