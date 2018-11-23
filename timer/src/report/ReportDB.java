package report;

import java.io.*;
import java.util.ArrayList;

public class ReportDB {
    public ReportDB(){}

    public void saveDB(ArrayList<Report> tableCustomer){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\Report\\reportDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tableCustomer);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public ArrayList<Report> loadDB() {
        ArrayList<Report> loadtableCustomer = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\Report\\reportDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Report> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadtableCustomer;
    }
}
