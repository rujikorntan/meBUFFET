package orderDish;
import java.io.*;
import java.util.ArrayList;

public class OrderDishDB {
    public  OrderDishDB(){}

    public void saveDB(OrderDish orderedDish){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\orderDish\\tableNO\\orderDishDB"+orderedDish.getTno()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderedDish);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public OrderDish loadDB(String tno) {
        OrderDish loadOrderDishCustomer = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\orderDish\\tableNO\\orderDishDB"+tno+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadOrderDishCustomer = ( OrderDish ) in.readObject();
            in.close();
            fileIn.close();
            return loadOrderDishCustomer;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        } 
        return loadOrderDishCustomer;
    }

    public void saveTmpDB(OrderDish orderedDish){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\orderDish\\tableNO\\orderDishTmpDB"+orderedDish.getTno()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderedDish);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    public OrderDish loadTmpDB(String tno) {
        OrderDish loadOrderDishCustomer = new OrderDish();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\orderDish\\tableNO\\orderDishTmpDB"+tno+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadOrderDishCustomer = ( OrderDish ) in.readObject();
            in.close();
            fileIn.close();
            return loadOrderDishCustomer;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return loadOrderDishCustomer;
    }
}
