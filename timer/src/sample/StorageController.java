package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import stock.Ingredient;
import stock.StorageDB;
import table.Table;

import java.io.IOException;
import java.util.ArrayList;

public class StorageController {
    @FXML
    private Label secondTime0;

    @FXML
    private Label minuteTime0;

    @FXML
    private Label hourTime0;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton removeBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton saveEditBtn;

    @FXML
    private JFXButton cancelEditBtn;

    @FXML
    private TableView<Ingredient> storageTable;

    @FXML
    private TableColumn<Ingredient, String> rawCol;

    @FXML

    private TableColumn<Ingredient, String> amountCol;

    @FXML
    private AnchorPane addPage;

    @FXML
    private JFXTextField nameAdd;

    @FXML
    private JFXTextField amountAdd;

    @FXML
    private JFXButton addAddBtn;

    @FXML
    private TableView<Ingredient> addTable;

    @FXML
    private TableColumn<Ingredient, String> rawAddCol;

    @FXML
    private TableColumn<Ingredient, String> amountAddCol;

    @FXML
    private JFXButton cancelAddBtn;

    @FXML
    private JFXButton saveAddBtn;



    private ArrayList<Ingredient> listOfIngredient = new ArrayList<>();
    private ArrayList<Ingredient> listAtAddPage = new ArrayList<>();
    private TimeCustomer timeCustomer1 = new TimeCustomer();
    private StorageDB storageDB = new StorageDB();

    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }

    @FXML
    public void initialize() {
        listOfIngredient= storageDB.loadDB();
        startTimer();
        storageTable.getItems().clear();
        for( Ingredient el : listOfIngredient){
            addToTable(el);
        }
    }
    @FXML
    public void addToTable(Ingredient newOne){
        storageTable.getItems().add(newOne);
        rawCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
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
//        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Storage.fxml"));
//        Scene storageScene = new Scene(storageParent);
//        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
//        window.setScene(storageScene);
//        window.show();
//        timeCustomer1.stopTimer();
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

    @FXML
       void add_TableBT(ActionEvent event) {
        try{
            listAtAddPage.clear();
            addTable.getItems().clear();
            storageTable.setVisible(false);
            addBtn.setVisible(false);
            removeBtn.setVisible(false);
            cancelEditBtn.setVisible(false);
            saveEditBtn.setVisible(false);
            addPage.setVisible(true);


        }catch (Exception e){
            System.out.println(e);
        }
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTablepopup.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage2 = new Stage();
//            stage2.setTitle("meBUFFET");
//            stage2.initStyle(StageStyle.UNDECORATED);
//            stage2.setScene(new Scene(root1));
//            stage2.show();
//
//            stage2.setOnHidden(new EventHandler<WindowEvent>() {
//                public void handle(WindowEvent we) {
//                    tableList.getItems().clear();
//                    tables = tableDB.loadDB();
//                    for(Table element : tables){
//                        addToTable(element);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            System.out.println(e);
//        }
      }

        @FXML
        void editData(ActionEvent event) {
            editBtn.setVisible(false);
            saveEditBtn.setVisible(true);
            cancelEditBtn.setVisible(true);
            addBtn.setVisible(true);
            removeBtn.setVisible(true);
            editableCols();
            storageTable.setEditable(true);
            storageDB.saveTempDB(listOfIngredient);
        }
    @FXML
    private void editableCols(){
            rawCol.setCellFactory(TextFieldTableCell.forTableColumn());
            rawCol.setOnEditCommit(e-> {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
            });

            amountCol.setCellFactory(TextFieldTableCell.forTableColumn());
            amountCol.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAmount((e.getNewValue()));
            });
    }
    @FXML
    void onEditChanged(TableColumn.CellEditEvent<Ingredient,String> tableStringCellEditEvent) {

        Ingredient ingredient= storageTable.getSelectionModel().getSelectedItem();
        ingredient.setName(tableStringCellEditEvent.getNewValue());

    }
    @FXML
    void save_Edit(ActionEvent event) {
        storageDB.saveDB(listOfIngredient);
        storageTable.setEditable(false);
        saveEditBtn.setVisible(false);
        cancelEditBtn.setVisible(false);
        addBtn.setVisible(false);
        removeBtn.setVisible(false);
        editBtn.setVisible(true);
    }
    @FXML
    void remove_TableBT(ActionEvent event) {
        Ingredient delIngre = storageTable.getSelectionModel().getSelectedItem();
        listOfIngredient.remove(delIngre);
        storageDB.saveDB(listOfIngredient);
        storageTable.getItems().clear();
        for(Ingredient el : listOfIngredient){
            addToTable(el);
        }
    }
    @FXML
    void cancel_Edit(ActionEvent event) {
        storageTable.getItems().clear();
        listOfIngredient = storageDB.loadTempDB();
        for(Ingredient el : listOfIngredient){
            addToTable(el);
        }
        storageTable.setEditable(false);
        saveEditBtn.setVisible(false);
        cancelEditBtn.setVisible(false);
        addBtn.setVisible(false);
        removeBtn.setVisible(false);
        editBtn.setVisible(true);
    }
//--------------------------------- ADD PAGE --------------------------------------------
    @FXML
    void backToEditPage(){
        storageTable.setVisible(true);
        addBtn.setVisible(true);
        removeBtn.setVisible(true);
        cancelEditBtn.setVisible(true);
        saveEditBtn.setVisible(true);
        addPage.setVisible(false);
    }

    @FXML
    public void addToTable_ADD(Ingredient newOne){
        addTable.getItems().add(newOne);
        rawAddCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountAddCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @FXML
    public void addNewIngre(){
        String name = nameAdd.getText();
        String amount = amountAdd.getText();
        if(Valid.isFilled(name) && Valid.isFilled(amount) && !Valid.isIngredientDup(name,listAtAddPage) && Valid.isNumber(amount)) {
            Ingredient ingre = new Ingredient(name, amount);
            listAtAddPage.add(ingre);
            addToTable_ADD(ingre);
            nameAdd.setText("");
            amountAdd.setText("");
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fillOut_info.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(new Scene(root1));
                stage2.show();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    public void addConfirm(){
        for(Ingredient el: listAtAddPage){
            listOfIngredient.add(el);
        }
        storageTable.getItems().clear();
        for(Ingredient el: listOfIngredient){
            addToTable(el);
        }
        backToEditPage();
    }

}
