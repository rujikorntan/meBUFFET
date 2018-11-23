package info;

import orderDish.OrderDish;

import java.io.*;
import java.util.ArrayList;

public class InfoDB {
    public  InfoDB(){}

    public void saveDB(Info orderedDish){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\info\\info.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderedDish);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public Info loadDB() {
        Info loadOrderDishCustomer = new Info("", "7", "", "", "3", "", "staff", "0000");
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\info\\info.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadOrderDishCustomer = (Info) in.readObject();
            in.close();
            fileIn.close();
            return loadOrderDishCustomer;
        } catch (IOException i) {
            saveDB(loadOrderDishCustomer);
            i.printStackTrace();
            return loadOrderDishCustomer;

        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadOrderDishCustomer;
    }
    public void saveTempDB(Info orderedDish){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\info\\infoTemp.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderedDish);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public Info loadTempDB() {
        Info loadOrderDishCustomer = new Info("", "7", "", "", "3", "", "staff", "0000");

        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\info\\infoTemp.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadOrderDishCustomer = (Info) in.readObject();
            in.close();
            fileIn.close();
            return loadOrderDishCustomer;
        } catch (IOException i) {
            i.printStackTrace();
            saveDB(loadOrderDishCustomer);
            i.printStackTrace();
            return loadOrderDishCustomer;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadOrderDishCustomer;
    }
}
