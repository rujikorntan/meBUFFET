/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import member.Member;
import member.MemberList;
import table.Table;


/**
 * FXML Controller class
 *
 * @author User
 */
public class MemberUIController implements Initializable {

    @FXML
    private Label secondTime0,minuteTime0,hourTime0;
    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> codeCol,nameCol,telCol,birthCol,enrollCol;
    @FXML
    private JFXButton addButton,removeButton,confirmEditButton,cancelEditButton, editBT;
    
    private MemberList memberList = new MemberList();
    private ArrayList<String> delList = new ArrayList<>();
    private final TimeCustomer timeCustomer1 = new TimeCustomer();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startTimer();
        refreshTable();
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        removeButton.setVisible(false);
        addButton.setVisible(false);
    }    
    
    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }

    @FXML
    private void editTable(ActionEvent event) {
        try{
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\member\\tempMemberList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memberList);
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
        memberTable.setEditable(true);
        codeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        telCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthCol.setCellFactory(TextFieldTableCell.forTableColumn());
        enrollCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void confirmEdit(ActionEvent event) {
        editBT.setVisible(true);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        memberTable.setEditable(false);
        for(int i=0;i<delList.size();i++){
            memberList.remove(delList.get(i));
        }
        saveDb();
        refreshTable();
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        editBT.setVisible(true);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        memberTable.setEditable(false);
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\member\\tempMemberList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            memberList = (MemberList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
        } catch (ClassNotFoundException c) {
            System.out.println("Member class not found");
            System.out.println(c);
        }
        saveDb();
        delList.clear();
        refreshTable();
    }

    @FXML
    private void onCodeChanged(TableColumn.CellEditEvent<Member,String> memStringCellEditEvent) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        mem.setId(memStringCellEditEvent.getNewValue());
    }

    @FXML
    private void onNameChanged(TableColumn.CellEditEvent<Member,String> memStringCellEditEvent) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        mem.setName(memStringCellEditEvent.getNewValue());
    }

    @FXML
    private void onTelChanged(TableColumn.CellEditEvent<Member,String> memStringCellEditEvent) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        mem.setTel(memStringCellEditEvent.getNewValue());
    }

    @FXML
    private void onBirthChanged(TableColumn.CellEditEvent<Member,String> memStringCellEditEvent) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        mem.setBirth(memStringCellEditEvent.getNewValue());
    }

    @FXML
    private void onEnrollChanged(TableColumn.CellEditEvent<Member,String> memStringCellEditEvent) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        mem.setEnroll(memStringCellEditEvent.getNewValue());
    }
    
    public void loadDb(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            memberList = (MemberList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            saveDb();
            loadDb();
        } catch (ClassNotFoundException c) {
            System.out.println("Member class not found");
            System.out.println(c);
        }
    }
    
    public void saveDb(){
        try{
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memberList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void popupAddMember(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMemberPopup.fxml"));    
        Parent root = loader.load();  
        Scene scene = new Scene(root);
        Stage stage = new Stage();        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
              memberTable.getItems().clear();
              loadDb();
              displayMemberList();
          }
      });  
    }
    
    void displayMemberList(){
        loadDb();
        for(int i=0;i<memberList.size();i++){
            memberTable.getItems().add(memberList.getMember(i));
            codeCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
            birthCol.setCellValueFactory(new PropertyValueFactory<>("birth"));
            enrollCol.setCellValueFactory(new PropertyValueFactory<>("enroll"));
        }
    }

    @FXML
    private void removeMember(ActionEvent event) {
        Member mem = memberTable.getSelectionModel().getSelectedItem();
        memberTable.getItems().remove(mem);
        delList.add(mem.getName());
        memberList.remove(mem.getName());
//        System.out.println(employeeList.size());
        saveDb();
    }
    
    public void refreshTable(){
        memberTable.getItems().clear();
        displayMemberList();
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
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Employee.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabMember(ActionEvent event) throws IOException{
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
