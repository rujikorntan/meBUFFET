/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import employee.Employee;
import employee.EmployeeList;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class EmployeeUIController {

    @FXML
    private Label secondTime0,minuteTime0,hourTime0;
    @FXML
    private JFXButton confirmEditButton,cancelEditButton,removeButton,addButton, editBT;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> codeCol,nameCol,birthCol,startCol,wageCol,telCol;
    
    private final TimeCustomer timeCustomer1 = new TimeCustomer();
//    private Employee emp = new Employee();
    private EmployeeList employeeList = new EmployeeList();
    private ArrayList<Employee> delList = new ArrayList<>();
    
    @FXML
    void popupAddEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployeePopup.fxml"));    
        Parent root = loader.load();  
        Scene scene = new Scene(root);
        Stage stage = new Stage();        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
              refreshTable();
          }
      });  
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
    
    public void loadDb(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\employee\\employeeList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employeeList = (EmployeeList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            saveDb();
            loadDb();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            System.out.println(c);
        }
    }
    
    void displayEmployeeList(){
        loadDb();
        for(int i=0;i<employeeList.size();i++){
            employeeTable.getItems().add(employeeList.getEmployee(i));
            codeCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
            birthCol.setCellValueFactory(new PropertyValueFactory<>("birth"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("firstDate"));
            wageCol.setCellValueFactory(new PropertyValueFactory<>("wage")); 
        }
    }
    
    @FXML
    void editTable(ActionEvent event){
        try{
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\employee\\tempEmployeeList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employeeList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch(IOException e){
            System.out.println(e);
        }
        editBT.setVisible(false);
        addButton.setVisible(true);
        removeButton.setVisible(true);
        confirmEditButton.setVisible(true);
        cancelEditButton.setVisible(true);
        employeeTable.setEditable(true);
        codeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthCol.setCellFactory(TextFieldTableCell.forTableColumn());
        startCol.setCellFactory(TextFieldTableCell.forTableColumn());
        wageCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    @FXML
    void onCodeChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setId(empStringCellEditEvent.getNewValue());
    }
    @FXML
    void onNameChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setName(empStringCellEditEvent.getNewValue());
    }
    @FXML
    void onTelChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setTel(empStringCellEditEvent.getNewValue());
    }
    @FXML
    void onBirthChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setBirth(empStringCellEditEvent.getNewValue());
    }
    @FXML
    void onStartChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setFirstDate(empStringCellEditEvent.getNewValue());
    }
    @FXML
    void onWageChanged(TableColumn.CellEditEvent<Employee,String> empStringCellEditEvent) {
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        emp.setWage(empStringCellEditEvent.getNewValue());
    }
    
    @FXML
    void cancelEdit(ActionEvent event){
        editBT.setVisible(true);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        employeeTable.setEditable(false);
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\employee\\tempEmployeeList.ser");
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
        saveDb();
        delList.clear();

        refreshTable();
    }
    
    @FXML
    void confirmEdit(ActionEvent event){
        editBT.setVisible(true);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        employeeTable.setEditable(false);
        for(int i=0;i<delList.size();i++){
            employeeList.remove(delList.get(i).getName());
        }
        delList.clear();
        saveDb();
        refreshTable();
    }
    
    @FXML
    void removeEmployee(ActionEvent event){
        Employee emp = employeeTable.getSelectionModel().getSelectedItem();
        employeeTable.getItems().remove(emp);
        delList.add(emp);
        employeeList.remove(emp.getName());
        saveDb();
    }
    
    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }
    
    public void refreshTable(){
        employeeTable.getItems().clear();
        displayEmployeeList();
    }
    
    @FXML
    public  void initialize(){
        startTimer();
        refreshTable();
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        removeButton.setVisible(false);
        addButton.setVisible(false);
    }
    //======================================================================================================================
    @FXML
    void go_tabOrderDish(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Orderdish.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabBill(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_OrderList.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabTable(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_table.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabDish(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Dish.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabStorage(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Storage.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabEmployee(ActionEvent event)throws IOException {
    }
    @FXML
    void go_tabMember(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Member.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabReport(ActionEvent event) throws IOException{
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Report.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabInfo(ActionEvent event)throws IOException {
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Info.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
//======================================================================================================================
}
