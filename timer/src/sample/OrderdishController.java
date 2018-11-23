package sample;
import dish.Dish;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import member.MemberDB;
import member.MemberList;
import orderDish.LoadDB;
import orderDish.OrderDish;
import orderDish.OrderDishDB;
import table.Table;
import table.TableDB;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import stock.Ingredient;

public class OrderdishController {

    private TimeCustomer timeCustomer1 = new TimeCustomer();
//    private LoadDB orderDish = new LoadDB();
    private LoadDB loadTable = new LoadDB();
//    private OrderDish orderDish = new OrderDish();
            
    private ObservableList tablename = FXCollections.observableArrayList();
    private ArrayList<Dish> dishList = new ArrayList<>();
    private ArrayList<HBox> menulistHbpx = new ArrayList<>();
    private TextField allTextField[] = new TextField[100];
//    private int amoutFood[] = new int[orderDish.getDishList().size()];
//    private int old_Amount[] = new int[orderDish.getDishList().size()];
//    private String nameFood[] = new String[orderDish.getDishList().size()];
    private ArrayList<OrderDish> orderList = new ArrayList<>();
    private OrderDishDB orderDB = new OrderDishDB();
    private ArrayList<OrderDish> orderLs = new ArrayList<>();
   
    private String select = "";
    private Label header1 = new Label("Dish Name");
    private Label header2 = new Label("Type");
    private Label header3 = new Label("Price");
    private Label header4 = new Label("Amount");
    private String table_nameToDB = "";

    @FXML
    private ListView<String> listView;

    @FXML
    private HBox headerHbox, tryDish_hbox;

    @FXML
    private Label secondTime0;

    @FXML
    private Label minuteTime0;

    @FXML
    private Label hourTime0;
    @FXML
    private ComboBox<String> input_member;

    @FXML
    private VBox menuVbox, tryDish_vbox1, tryDish_vbox2, tryDish_vbox3, tryDish_vbox4;


    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }

    //======================================================================================================================
    @FXML
    void go_tabOrderDish(ActionEvent event) throws IOException{
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
    
    private ArrayList<Dish> loadDB() throws ClassNotFoundException {
        ArrayList<Dish> loadtableCustomer = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\dish\\dishDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = ( ArrayList<Dish> ) in.readObject();
            in.close();
            fileIn.close();
            return loadtableCustomer;
        } catch (IOException i) {
            i.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return loadtableCustomer;
    }

    @FXML
    public  void initialize() throws ClassNotFoundException{
        startTimer();
        for(Table ele : loadTable.getTableList()){
            if(ele.isAvailable().equals("true")){
                tablename.add(ele.getTableNum());
            }
        }
        listView.setMaxSize(100, 250);
        listView.setItems(tablename);
        dishList = loadDB();

        MemberDB memberDB = new MemberDB();
        MemberList memberList = new MemberList();
        memberList = memberDB.loadDB();

        for (int i = 0; i <memberList.size() ; i++) {
            input_member.getItems().add(memberList.getMember(i).getName());
        }


//        for(TempDish ele : loadTable.getDishList()){
//            dishList.add(ele);
//        }
//        System.out.println(dishList.get(0).getName());
        headerHbox.setPadding(new Insets(15, 0, 15, 0));
        headerHbox.getChildren().addAll(header1, header2, header3, header4);
        HBox.setHgrow(header1, Priority.ALWAYS);
        HBox.setHgrow(header2, Priority.ALWAYS);
        HBox.setHgrow(header3, Priority.ALWAYS);
        HBox.setHgrow(header4, Priority.ALWAYS);
        header1.setMaxWidth(255);
        header2.setMaxWidth(135);
        header3.setMaxWidth(150);
        header4.setMaxWidth(60);

        HBox menuList = new HBox();
        menuList.setPadding(new Insets(10, 0, 0, 10));

        listView.setOnMouseClicked(e ->{
            showToTable();
            table_nameToDB = listView.getSelectionModel().getSelectedItem();
        });

    }
    public void clearTable(){
        tryDish_vbox1.getChildren().clear();
        tryDish_vbox2.getChildren().clear();
        tryDish_vbox3.getChildren().clear();
        tryDish_vbox4.getChildren().clear();
    }
    public void showToTable(){
        clearTable();
        tryDish_vbox1.setSpacing(27);
        tryDish_vbox2.setSpacing(27);
        tryDish_vbox3.setSpacing(27);
        tryDish_vbox4.setSpacing(15);

        ArrayList<String> nameDish = new ArrayList<>();
        ArrayList<String> typeDish = new ArrayList<>();
        ArrayList<String> priceDish = new ArrayList<>();
        ArrayList<Integer> amountDish = new ArrayList<>();


        ArrayList<Ingredient> tmpIngre2 = loadFromStock();
        System.out.println(dishList.size());
        for(Dish el: dishList){
            ArrayList<Ingredient> checkIngre2 = el.getIngredients();
            for(Ingredient item : checkIngre2){
                int amountInDish = Integer.parseInt(item.getAmount());
                for(Ingredient oneIngre: tmpIngre2){
                    if(oneIngre.getName().equalsIgnoreCase(item.getName())){
                        float amountInStorage = Float.parseFloat(oneIngre.getAmount())*1000;
                        if(amountInDish <= amountInStorage){
                            el.setStatus("Active");
                        }
                        else{
                            el.setStatus("Out of stock");
                        }

                    }

                }
            }
        }
        for (Dish box : dishList) {
//            System.out.println(box.getStatus());
            if(box.getStatus().equalsIgnoreCase("Active")){
                nameDish.add(box.getName());
                typeDish.add(box.getType());
                priceDish.add(box.getPrice());
                amountDish.add(box.getAmount());
            }
        }
        for (String name : nameDish){
            tryDish_vbox1.getChildren().add(new Text(name));
        }
        for (String type : typeDish){
            tryDish_vbox2.getChildren().add(new Text(type));
        }
        for (String price : priceDish){
            tryDish_vbox3.getChildren().add(new Text(price));
        }

        for (int i = 0; i < priceDish.size(); i++) {
            TextField tempTextField = new TextField();
            tempTextField.setMaxWidth(80);
            tempTextField.setMinWidth(80);
            tempTextField.setAlignment(Pos.CENTER_RIGHT);

            allTextField[i] = tempTextField;
            tryDish_vbox4.getChildren().addAll( allTextField[i]);
        }
    }

    void clearArray(){
//        amoutFood = null;
        for (int i = 0; i < dishList.size(); i++) {
            allTextField[i].setText("");
        }
    }
    
    @FXML
    void send_order(ActionEvent event)  {


        boolean edited = false;
        boolean firstTime = true;
        
        OrderDish orderDish = new OrderDish();
        OrderDish orderDish2 = new OrderDish();
        orderDish.setTno(table_nameToDB);
        orderDish2.setTno(table_nameToDB);
        orderDish = orderDB.loadDB(table_nameToDB);
        orderDish2 = orderDB.loadTmpDB(table_nameToDB);

        if(orderDish.size()!=0) {
            firstTime = false;
        }
        if(firstTime){
            orderDish.setMember(input_member.getValue());
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            orderDish.setTimeActive(date.toString()+" "+time.toString());
            orderDish2.setTimeActive(date.toString()+" "+time.toString());
        }
        for (int a = 0; a < dishList.size(); a++) {
            if(allTextField[a].getText() == null || allTextField[a].getText().trim().isEmpty()){
                for(int i=0;i<orderDish2.size();i++) {
                    if(dishList.get(a).getName().equalsIgnoreCase(orderDish2.getDish(i).getName())) {
//                        orderDish2.getDish(i).setAmount(0);
                        orderDish2.getDish(i).setAmount(orderDish2.getDish(i).getAmount() + 0);
                        edited = true;
                        break;
                    }
                }
                if(edited){}
                else{
                    dishList.get(a).setAmount(0);
                    orderDish2.addDish(dishList.get(a));
                }
                edited = false;
//                orderDish2.addDish(dishList.get(a));
            }
            else{
                for(int i=0;i<orderDish.size();i++){
                    if(dishList.get(a).getName().equals(orderDish.getDish(i).getName())){
                        orderDish.getDish(i).setAmount(orderDish.getDish(i).getAmount()+Integer.parseInt(allTextField[a].getText()));
                        edited = true;
                        break;
                    }
                }
                if(edited){}
                else{
                    dishList.get(a).setAmount(Integer.parseInt(allTextField[a].getText()));
                    orderDish.addDish(dishList.get(a));
                }
                edited = false;
                for(int i=0;i<orderDish2.size();i++){
                    if(dishList.get(a).getName().equals(orderDish2.getDish(i).getName())){
                        edited = true;
                        orderDish2.getDish(i).setAmount(orderDish2.getDish(i).getAmount()+Integer.parseInt(allTextField[a].getText()));
                        break;
                    }
                }
                if(edited){}
                else{
                    dishList.get(a).setAmount(Integer.parseInt(allTextField[a].getText()));
                    orderDish2.addDish(dishList.get(a));
                }
                edited = false;
            }
        }
        
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        orderDish.setTimeActive(sdf.format(cal.getTime()));
        
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        orderDish.setTimeActive(date.toString()+" "+time.toString());
//        orderDish2.setTimeActive(date.toString()+" "+time.toString());

        TableDB tableDB = new TableDB();
        ArrayList<Table> tables = new ArrayList<>();
        tables = tableDB.loadDB();
        int max=0;
        for(Table t : tables){
            if("Active".equals(t.getStatus())){
                OrderDish tmpOrder = new OrderDish();
                tmpOrder = orderDB.loadDB(t.getTableNum());
                if(Integer.parseInt(tmpOrder.getBillno())>max){
                    max = Integer.parseInt(tmpOrder.getBillno());
                }
            }
        }
        if(firstTime){
            orderDish.setBillno(Integer.toString(max+1));
            orderDish2.setBillno(Integer.toString(max+1));
        }
        
        
        
        
        for(int i=0;i<orderDish2.size();i++){
            System.out.println("dish2");
            System.out.println(orderDish2.getDish(i).getName());
            System.out.println(orderDish2.getDish(i).getAmount());
        }
        System.out.println("--------------------");
        
        // load table database
        ArrayList<Table> editTables = new ArrayList<>();
        
        editTables = tableDB.loadDB();
        
        for(Table ele : editTables){
            if(table_nameToDB.equals(ele.getTableNum())){
                ele.setStatus("Active");
            }
        }
        
        tableDB.saveDB(editTables);
        System.out.println("11111");
        ArrayList<Dish> tmpDish = orderDish.getOrderDishList();
        ArrayList<Ingredient> tmpIngre = loadFromStock();
        float amountInStorage=0;
        float newAmount;
         for(Dish el: tmpDish){
            ArrayList<Ingredient> checkIngre = el.getIngredients();
            for(Ingredient item : checkIngre){
                int amountInDish = Integer.parseInt(item.getAmount());                
                for(Ingredient oneIngre: tmpIngre){
                    if(oneIngre.getName().equalsIgnoreCase(item.getName())){
                        float amount = Float.parseFloat(oneIngre.getAmount());
                        amount *= 1000;
                        amountInStorage = amount;
                        amountInDish *= el.getAmount();
                        newAmount = (amountInStorage - amountInDish)/1000;   
                        System.out.println(newAmount);
                        oneIngre.setAmount(Float.toString(newAmount));
                    }
                    if(amountInDish <= Float.parseFloat(oneIngre.getAmount())){
                        System.out.println(oneIngre.getAmount());
                        el.setStatus("Active");
                    }
                    else{
                        System.out.println(oneIngre.getAmount());
                        el.setStatus("Out of stock");
                    }
                }
            }
        }
              
                
        saveToStock(tmpIngre);
        
        orderDB.saveDB(orderDish);
        orderDB.saveTmpDB(orderDish2);
        clearArray();
    }

    @FXML
    void cancel_order(ActionEvent event) {

// Load
//        orderLs = orderDB.loadDB();
//        System.out.println(orderLs);
//
//        for(OrderDish i : orderLs){
//            System.out.println("Table No. "+ i.getTable_order());
//            int[] test = i.getAmount_order();
//            String[] name = i.getDish_order();
//
//            for (int j = 0; j < test.length  ; j++) {
//                System.out.println(test[j]);
//            }
//            for (int k = 0; k < name.length  ; k++) {
//                System.out.println(name[k]);
//            }
//        }
//
//
        input_member.getItems().clear();
        clearArray();
        
    }
    
private ArrayList<Ingredient> loadFromStock() {
        ArrayList<Ingredient> loadtableCustomer = null;
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\stock\\storageDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadtableCustomer = (ArrayList<Ingredient>) in.readObject();
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
        } catch (Exception e){
            e.printStackTrace();
        }

    }
        


}
