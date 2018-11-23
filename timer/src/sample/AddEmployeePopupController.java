/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import employee.Employee;
import employee.EmployeeList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddEmployeePopupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton closeButton,confirmButton;
    
    @FXML
    private JFXTextField nameField,idField,wageField,phoneField;
    
    @FXML
    private JFXDatePicker birthField,startField;

    private EmployeeList employeeList = new EmployeeList();
    
    private Employee emp = new Employee(); 
    
    
    public void loadDb(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\employee\\employeeList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employeeList = (EmployeeList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            System.out.println(c);
        }
    }
    
    public void saveDb(){
        try{
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\employee\\employeeList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employeeList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
    @FXML
    public void createEmployee() throws IOException {

        if(nameField.getText().equals("") || Valid.isEmployeeNameOrIdDup(nameField.getText())){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(idField.getText().equals("") || Valid.isEmployeeNameOrIdDup(idField.getText())){
//            Stage stage = (Stage) comfirmBT.getScene().getWindow();
//            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(wageField.getText().equals("")){
//            Stage stage = (Stage) comfirmBT.getScene().getWindow();
//            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(phoneField.getText().equals("")){
//            Stage stage = (Stage) comfirmBT.getScene().getWindow();
//            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(birthField.getValue() == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {

                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(startField.getValue() == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {

                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            loadDb();
            emp.setName(nameField.getText());
            emp.setBirth(birthField.getValue().toString());
            emp.setId(idField.getText());
            emp.setFirstDate(startField.getValue().toString());
            emp.setWage(wageField.getText());
            emp.setTel(phoneField.getText());

            employeeList.append(emp);
            for(Employee e:employeeList.getDelList()){
                System.out.println(e.getName());
                employeeList.remove(e.getName());
            }
            saveDb();
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        }

    }
    
    @FXML
    public void closePopup(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        loadDb();
    }    
    
}
