package sample;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import table.Table;
import table.TableDB;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.ArrayList;

public class TableController  {
    private ArrayList<Table> tables = new ArrayList<>();
    private TableDB tableDB = new TableDB();
    private TimeCustomer timeCustomer1 = new TimeCustomer();

    @FXML
    private Label secondTime0;

    @FXML
    private Label minuteTime0;

    @FXML
    private Label hourTime0;
    @FXML
    private TableView<Table> tableList;

    @FXML
    private TableColumn<Table, String> tableum_col;

    @FXML
    private TableColumn<Table, String> capacity_col;

    @FXML
    private TableColumn<Table, String> avaliable_col;

    @FXML
    private TableColumn<Table, String> status_col;

    @FXML
    private JFXButton removeBT;
    @FXML
    private JFXButton addBT;
    @FXML
    private JFXButton saveEditBT;
    @FXML
    private JFXButton cancelEditBT;
    @FXML
    private JFXButton editBT;



    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }
    @FXML
    void add_TableBT(ActionEvent event) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTablepopup.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

                stage2.setOnHidden(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        tableList.getItems().clear();
                        tables = tableDB.loadTempDB();
                        for(Table element : tables){
                            addToTable(element);
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
    }


    @FXML
    public  void initialize(){
        startTimer();

        tables = tableDB.loadDB();
        tableDB.saveTempDB(tables);

        for(Table element : tables){
            addToTable(element);
        }
        saveEditBT.setVisible(false);
        cancelEditBT.setVisible(false);
        addBT.setVisible(false);
        removeBT.setVisible(false);
    }
    @FXML
    void editData(ActionEvent event) {
        editBT.setVisible(false);
        saveEditBT.setVisible(true);
        cancelEditBT.setVisible(true);
        addBT.setVisible(true);
        removeBT.setVisible(true);
        editableCols();
        tableList.setEditable(true);
        tableDB.saveTempDB(tables);
    }

    public void addToTable(Table tableCustomer){
        tableList.getItems().add(tableCustomer);

        tableum_col.setCellValueFactory(new PropertyValueFactory<>("tableNum"));
        capacity_col.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        avaliable_col.setCellValueFactory(new PropertyValueFactory<>("available"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));

    }
    private void editableCols(){
            tableum_col.setCellFactory(TextFieldTableCell.forTableColumn());
            tableum_col.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setTableNum(e.getNewValue());
            });

            capacity_col.setCellFactory(TextFieldTableCell.forTableColumn());
            capacity_col.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setCapacity(e.getNewValue());
            });

            avaliable_col.setCellFactory(TextFieldTableCell.forTableColumn());
            avaliable_col.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAvailable(e.getNewValue());
            });

            status_col.setCellFactory(TextFieldTableCell.forTableColumn());
            status_col.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setStatus(e.getNewValue());
            });
    }
    @FXML
    void onEditChanged(TableColumn.CellEditEvent<Table,String> tableStringCellEditEvent) {

        Table table = tableList.getSelectionModel().getSelectedItem();
        table.setTableNum(tableStringCellEditEvent.getNewValue());

    }
    @FXML
    void save_Edit(ActionEvent event) {
        tableDB.saveDB(tables);
        tableList.setEditable(false);
        saveEditBT.setVisible(false);
        cancelEditBT.setVisible(false);
        addBT.setVisible(false);
        removeBT.setVisible(false);
        editBT.setVisible(true);
    }

    @FXML
    void remove_TableBT(ActionEvent event) {
        Table deltable = tableList.getSelectionModel().getSelectedItem();
        tables.remove(deltable);

        tableDB.saveTempDB(tables);
        tableList.getItems().clear();
        for(Table element : tables){
            addToTable(element);
        }
    }
    @FXML
    void cancel_Edit(ActionEvent event) {
        tableList.getItems().clear();

        tables = tableDB.loadDB();
        for(Table element : tables){
            addToTable(element);
        }
        tableList.setEditable(false);
        saveEditBT.setVisible(false);
        cancelEditBT.setVisible(false);
        addBT.setVisible(false);
        removeBT.setVisible(false);
        editBT.setVisible(true);
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
