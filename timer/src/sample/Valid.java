package sample;

import dish.Dish;
import employee.Employee;
import employee.EmployeeList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import member.MemberList;
import stock.Ingredient;
import stock.StorageDB;
import table.Table;
import table.TableDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public  class Valid {


    static public boolean isNumber(String input){
        try {
            double d = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static public boolean isFilled(String input){
        if(input == null || input.equals("")){
            return false;
        }else {
            return true;
        }
    }

    static public boolean isStringValid (String input){
        // only A-Z a-z 0-9
        if(input.length() < 10 && input.matches("\\w+")){
            return true;
        }else {
            return false;
        }
    }

    static public boolean isMemberNameOrIdDup (String input){
        MemberList mem = new MemberList();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\employee\\memberList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            mem = (MemberList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            System.out.println(c);
        }
        for(int i=0;i<mem.size();i++){
            if(input.equalsIgnoreCase(mem.getMember(i).getName()) || input.equalsIgnoreCase(mem.getMember(i).getId())){
                return true;
            }
        }
        return false;
    }

    static public boolean isEmployeeNameOrIdDup (String input){
        EmployeeList emp = new EmployeeList();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\employee\\employeeList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            emp = (EmployeeList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            System.out.println(c);
        }
        for(int i=0;i<emp.size();i++){
            if(input.equalsIgnoreCase(emp.getEmployee(i).getName()) || input.equalsIgnoreCase(emp.getEmployee(i).getId())){
                return true;
            }
        }
        return false;
    }

    static public boolean isTableNameDup (String input){
        TableDB tableDB = new TableDB();
        ArrayList<Table> tables = new ArrayList<>();
        tables = tableDB.loadTempDB();

        for (int i = 0; i < tables.size(); i++) {
            if(input.equalsIgnoreCase(tables.get(i).getTableNum())){
                return true;
            }
        }
        return false;
    }

    public  static  boolean isDishNameDup (String input){
        ArrayList<Dish> allDish = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\dish\\dishDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allDish = ( ArrayList<Dish> ) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }

        for(Dish el: allDish){
            if(el.getName().equalsIgnoreCase(input)){
                return true;
            }
        }
        return false;
    }

    public static boolean isIngredientDup(String input, ArrayList<Ingredient> someStock){
        StorageDB stock = new StorageDB();
        ArrayList<Ingredient> allIngre = stock.loadDB();
        for(Ingredient el : allIngre){
            if(input.equalsIgnoreCase(el.getName())){
                return true;
            }
        }
        for(Ingredient el : someStock){
            if(input.equalsIgnoreCase(el.getName())){
                return true;
            }
        }
        return false;
    }


}
