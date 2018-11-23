package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dish.Dish;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import stock.Ingredient;

import java.io.*;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DishController {

    @FXML
    private TableView<Dish> dishTable;

    @FXML
    private JFXButton orderDishButton,orderListButton,tableButton,dishButton,storageButton,employeeButton,memberButton,reportButton,infoButton;

    @FXML
    private TableColumn<Dish, String> nameCol;

    @FXML
    private TableColumn<Dish, String> typeCol;

    @FXML
    private TableColumn<Dish, String> priceCol;

    @FXML
    private TableColumn<Dish, String> statusCol;

    @FXML
    private AnchorPane showDish, addDishPage;

    @FXML
    private ImageView imageDish, previewImage;

    @FXML
    private Label nameDish;
    
    @FXML
    private Label secondTime0,minuteTime0,hourTime0;

    @FXML
    private TableView<Ingredient> ingreTable;

    @FXML
    private TableColumn<Ingredient, String> nameDishCol;

    @FXML
    private TableColumn<Ingredient, String> amountDishCol;

    @FXML
    private TextField name_ingre;

    @FXML
    private TextField amountDish;

    @FXML
    private TextField amountStorage;

    @FXML
    private VBox vbox;

    @FXML
    private JFXTextField dishName;

    @FXML
    private JFXComboBox<String> dishType;

    @FXML
    private JFXTextField dishPrice;

    @FXML
    private JFXButton addPageBtn,removeBtn,editBtn,cancelEditButton,confirmEditButton;

    @FXML
    private TextArea textAreaIngre;

    private final TimeCustomer timeCustomer1 = new TimeCustomer();

    private String fileAsString;
    private ArrayList<Ingredient> ingredientStorage;   // for show all ingredient
    private ArrayList<TextField> textFields = new ArrayList<>();
    private ArrayList<Ingredient> tmpNewIngredient = new ArrayList<>();     // ingredient in table
    private ArrayList<Ingredient> ingreUseInDish = new ArrayList<>();  // for ingredient that use in dish
    private ArrayList<Dish> dishList = new ArrayList<>();

    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }
    
    @FXML
    public void initialize() {
        String allTypeName[] = {"appetizer","noodle","slad", "main","steak","soup","snack","dessert"};
        for(String typeName : allTypeName ){
            dishType.getItems().add(typeName);
        }
        startTimer();
        showDish.setVisible(false);
        dishList = loadDB();
        dishTable.getItems().clear();
        loadFromStock();
  
        if(dishList != null) {
            updateStatus();
            for (Dish el : dishList) {
                addToTable(el);
            }
        }
        dishTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                showDetail();
            }
        });

    }

    @FXML
    void showDetail() {
        showDish.setVisible(true);
        Dish selectedDish = dishTable.getSelectionModel().getSelectedItem();
        if (selectedDish != null) {
            imageDish.setImage(new Image(selectedDish.getPicPath()));
            nameDish.setText(selectedDish.getName());
            String messege = "";
            for (Ingredient el : selectedDish.getIngredients()) {
                messege += (el.getName() + "  " + el.getAmount() + " g.\n");
            }
            textAreaIngre.setText(messege);
        }
    }

    @FXML
    public void addToTable(Dish newOne) {
        dishTable.getItems().add(newOne);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void add_TableBT(ActionEvent event) {
        try {
            dishTable.setVisible(false);
            showDish.setVisible(false);
            addPageBtn.setVisible(false);
            removeBtn.setVisible(false);
            cancelEditButton.setVisible(false);
            confirmEditButton.setVisible(false);
            dishName.setText("");
            dishType.setValue("");
            dishPrice.setText("");
            name_ingre.setText("");
            amountDish.setText("");
            amountStorage.setText("");
            addDishPage.setVisible(true);
            tmpNewIngredient.clear();
            ingreTable.getItems().clear();
            ingreUseInDish.clear();
            previewImage.setImage(null);
            vbox.getChildren().clear();
            vbox.setSpacing(20);
            textFields.clear();
            HBox row = new HBox();
            for (int i = 0; i < ingredientStorage.size(); i++) {
                row.setPadding(new Insets(10,0,0,0));
                Label tmpName = new Label(ingredientStorage.get(i).getName());
                tmpName.setPrefWidth(160);
                tmpName.setAlignment(Pos.CENTER);
                TextField tmpText = new TextField();
                tmpText.setStyle("-fx-border-insets : 10px;");
                tmpText.setPrefWidth(80);
                tmpName.setPadding(new Insets(10, 20, 0, 10));
                textFields.add(tmpText);
                row.getChildren().addAll(tmpName, tmpText);
                if (i % 2 != 0 || i == ingredientStorage.size() - 1) {
                    vbox.getChildren().add(row);
                    row = new HBox();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void addPicPath(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = chooser.showOpenDialog(window);
        if (file != null) {
            fileAsString = file.toURL().toString();
//            int indexTemp = fileAsString.indexOf("sample");
//            fileAsString = fileAsString.substring(indexTemp);
            previewImage.setImage(new Image(fileAsString));
        }
    }

    private void loadFromStock() {
        ArrayList<Ingredient> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\stock\\storageDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = (ArrayList<Ingredient>) in.readObject();
            in.close();
            fileIn.close();
            ingredientStorage = loadtableCustomer;
        } catch (IOException i) {
            ingredientStorage = loadtableCustomer;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        ingredientStorage = loadtableCustomer;
    }

    private void updateStatus(){
        loadFromStock();
        for(Dish el: dishList){
            ArrayList<Ingredient> checkIngre = el.getIngredients();
            for(Ingredient item : checkIngre){
                int amountInDish = Integer.parseInt(item.getAmount());
                float amountInStorage = findIngreAmount(item.getName());
               if(amountInDish <= amountInStorage){
                   el.setStatus("Active");
               }
               else{
                   el.setStatus("Out of stock");
               }
            }
        }
    }

    private float findIngreAmount(String name){
        for(Ingredient el: ingredientStorage){
            if(el.getName().equalsIgnoreCase(name)){
                float amount = Float.parseFloat(el.getAmount());
                amount *= 1000;
                return amount;
            }
        }
        return 0;
    }

    @FXML
    private void removeDish(){
        Dish delDish = dishTable.getSelectionModel().getSelectedItem();
        dishList.remove(delDish);
        dishTable.getItems().clear();
        for(Dish el: dishList){
            addToTable(el);
        }
        showDish.setVisible(false);
    }

    @FXML
    private  void editDish(){
        saveDB(dishList);
        editBtn.setVisible(false);
        addPageBtn.setVisible(true);
        removeBtn.setVisible(true);
        cancelEditButton.setVisible(true);
        confirmEditButton.setVisible(true);
    }

    @FXML
    private void cancelEdit(){
        editBtn.setVisible(true);
        addPageBtn.setVisible(false);
        removeBtn.setVisible(false);
        cancelEditButton.setVisible(false);
        confirmEditButton.setVisible(false);
        dishList = loadDB();
        dishTable.getItems().clear();
        for(Dish el: dishList){
            addToTable(el);
        }

    }

//    @FXML
//    void remove_TableBT(ActionEvent event) {
//        Ingredient delIngre = storageTable.getSelectionModel().getSelectedItem();
//        listOfIngredient.remove(delIngre);
//        storageDB.saveDB(listOfIngredient);
//        storageTable.getItems().clear();
//        for(Ingredient el : listOfIngredient){
//            addToTable(el);
//        }
//    }
    //    ------------------- add page ----------------------------------------------------------------------
//     ----------------------------------------------------------------------------------------------------
    @FXML
    void addNewIngredient() {
        String name = name_ingre.getText();
        String amountInStorage = amountStorage.getText();
        String amountInDish = amountDish.getText();
        Ingredient newOne = new Ingredient(name, amountInStorage);
        tmpNewIngredient.add(newOne);
        name_ingre.setText("");
        amountDish.setText("");
        amountStorage.setText("");
        ingreTable.getItems().clear();
        for (Ingredient el : tmpNewIngredient) {
            addToTable_Ingredient(el);
        }

    }

    @FXML
    void addToTable_Ingredient(Ingredient newOne) {
        ingreTable.getItems().add(newOne);
        nameDishCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountDishCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }


    private void getIngredientInDish() {

        for (int i = 0; i < textFields.size(); i++) {
            if (!(textFields.get(i).getText().equalsIgnoreCase(""))) {
                String amountInDish = textFields.get(i).getText();
                String name = ingredientStorage.get(i).getName();
                ingreUseInDish.add(new Ingredient(name, amountInDish));
            }
        }
    }

    @FXML
    void saveDish() {

        getIngredientInDish();
        for(Ingredient el : tmpNewIngredient){
            ingreUseInDish.add(el);
        }

        if(Valid.isFilled(dishName.getText()) && Valid.isFilled(dishType.getValue()) && Valid.isFilled(dishPrice.getText()) && Valid.isFilled(fileAsString) && ingreUseInDish.size() > 0 && !(Valid.isDishNameDup(dishName.getText())) && Valid.isNumber(dishPrice.getText())) {
            ArrayList<Ingredient> tmpIngre = new ArrayList<>();
            if (ingreUseInDish.size() > 0) {
                for (Ingredient el : ingreUseInDish) {
                    tmpIngre.add(new Ingredient(el.getName(), el.getAmount()));
                }
            }
            dishList.add(new Dish(dishName.getText(), dishType.getValue(), dishPrice.getText(), fileAsString, tmpIngre));
            dishTable.getItems().clear();
            updateStatus();
            for (Dish el : dishList) {
                addToTable(el);
            }


            fileAsString ="";
            addDishPage.setVisible(false);
            dishTable.setVisible(true);
            addPageBtn.setVisible(true);
            showDish.setVisible(false);
            removeBtn.setVisible(true);
            cancelEditButton.setVisible(true);
            confirmEditButton.setVisible(true);
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
    private void saveEdit(){
        loadFromStock();
        for (Ingredient all : tmpNewIngredient) {
            ingredientStorage.add(all);
        }
        saveToStock(ingredientStorage);
        saveDB(dishList);
        dishTable.getItems().clear();
        updateStatus();
        for (Dish el : dishList) {
            addToTable(el);
        }
        addDishPage.setVisible(false);
        dishTable.setVisible(true);
        addPageBtn.setVisible(false);
        showDish.setVisible(false);
        removeBtn.setVisible(false);
        editBtn.setVisible(true);
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);

    }

    public void saveToStock(ArrayList<Ingredient> tableCustomer){
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

    void saveDB(ArrayList<Dish> dishlist){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\dish\\dishDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dishlist);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    private ArrayList<Dish> loadDB() {
        ArrayList<Dish> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\dish\\dishDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Dish> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            saveDB(loadtableCustomer);
            loadtableCustomer =loadDB();
        } catch (Exception e) {
            System.out.println(e);
        }
        return loadtableCustomer;
    }

    @FXML
    void cancel(){
        fileAsString ="";
        addDishPage.setVisible(false);
        dishTable.setVisible(true);
        addPageBtn.setVisible(true);
        showDish.setVisible(false);
        removeBtn.setVisible(true);
        cancelEditButton.setVisible(true);
        confirmEditButton.setVisible(true);
    }



    @FXML
    void menuSelection(ActionEvent event) throws IOException{
        if(event.getSource()==orderDishButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Orderdish.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==orderListButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_OrderList.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==tableButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_table.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==dishButton){
        }
        else if(event.getSource()==storageButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Storage.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==employeeButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Employee.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==memberButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Member.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==reportButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Report.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
        else if(event.getSource()==infoButton){
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Info.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
        }
    }

}
