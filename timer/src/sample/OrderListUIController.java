/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;


import com.jfoenix.controls.JFXButton;
import dish.Dish;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import info.Info;
import info.InfoDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import member.Member;
import member.MemberDB;
import member.MemberList;
import orderDish.OrderDish;
import orderDish.OrderDishDB;
import receipt.Receipt;
import table.Table;
import table.TableDB;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OrderListUIController implements Initializable {

    @FXML
    private JFXButton orderDishButton,orderListButton,tableButton,dishButton,storageButton,employeeButton,memberButton,reportButton,infoButton;

    @FXML
    private Label secondTime0,minuteTime0,hourTime0;
    
    @FXML
    private Pane tablePane,detailPane,proceedPane;
    
    @FXML
    private HBox bodyTable;
    
    @FXML
    private Text grossText,vatText,discountText,netText,discountTxt,vatTxt;
    
    @FXML
    private Text billDetail,dateDetail,paidDetail;
    
    @FXML
    private TableView<Dish> totalTable;
     
    @FXML
    private TableColumn<Dish, String> nameCol,priceCol;
    @FXML
    private TableColumn<Dish, Integer> amountCol;
    
    @FXML
    private JFXButton backButton, proceedButton,confirmButton;
    
    private VBox vBill = new VBox(),vTable = new VBox(),vTotal = new VBox(),vStatus = new VBox(),vDetail = new VBox();
    
//    private OrderDishDB orderDishDB = new OrderDishDB();
    private final TimeCustomer timeCustomer1 = new TimeCustomer();
//    private LoadDB newDB = new LoadDB();
//    private ArrayList<OrderDish> orderDish = new ArrayList<>(orderDishDB.loadDB());
    private ArrayList<OrderDish> orderList = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    private OrderDishDB orderDB = new OrderDishDB();
    private TableDB tableDB = new TableDB();
    private InfoDB infoDB = new InfoDB();

    Info info = new Info();
    
    MemberDB memberDB = new MemberDB();
    MemberList memberList = new MemberList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startTimer();
        info = infoDB.loadDB();
        tables = tableDB.loadDB();
        for(Table t : tables){
            if("Active".equals(t.getStatus())){
                OrderDish orderDish = new OrderDish();
                orderDish = orderDB.loadDB(t.getTableNum());
                orderList.add(orderDish);
            }
        }
        for(int i=0;i<orderList.size()-1;i++){
            for(int j=0;j<orderList.size()-1;j++){
                if(Integer.parseInt(orderList.get(j).getBillno())>Integer.parseInt(orderList.get(j+1).getBillno())){
                    OrderDish tmp = new OrderDish();
                    tmp = orderList.get(j);
                    orderList.set(j, orderList.get(j+1));
                    orderList.set(j+1, tmp);
                }
            }
        }
        tablePane.toFront();
        setup();
        createRow(orderList.size());
        totalTable.setSelectionModel(null);
        proceedPane.setVisible(false);
        confirmButton.setVisible(false);
        memberList = memberDB.loadDB();

    }    
    
    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }
    
    public void setup(){
        Text billNO = new Text("Bill no.");
        billNO.setFont(new Font(20));
        billNO.setStyle("-fx-font-weight: bold");
        Text tableNO = new Text("Table no.");
        tableNO.setFont(new Font(20));
        tableNO.setStyle("-fx-font-weight: bold");
        Text totalAm = new Text("Total amount");
        totalAm.setFont(new Font(20));
        totalAm.setStyle("-fx-font-weight: bold");
        Text paidSt = new Text("Paid Status");
        paidSt.setFont(new Font(20));
        paidSt.setStyle("-fx-font-weight: bold");
        
        bodyTable.setSpacing(70);
        bodyTable.setPadding(new Insets(15, 15, 15, 50));
        
        bodyTable.getChildren().add(vBill);
        bodyTable.getChildren().add(vTable);
        bodyTable.getChildren().add(vTotal);
        bodyTable.getChildren().add(vStatus);
        bodyTable.getChildren().add(vDetail);
        
        vBill.getChildren().add(billNO);
        vTable.getChildren().add(tableNO);
        vTotal.getChildren().add(totalAm);
        vStatus.getChildren().add(paidSt);
        vDetail.getChildren().add(new Text(""));
        
        vBill.setSpacing(20);
        vBill.setAlignment(Pos.CENTER);
        
        vTable.setSpacing(20);
        vTable.setAlignment(Pos.CENTER);
        
        vTotal.setSpacing(20);
        vTotal.setAlignment(Pos.CENTER);
        
        vStatus.setSpacing(20);
        vStatus.setAlignment(Pos.CENTER);
        
        vDetail.setSpacing(12);
        vDetail.setAlignment(Pos.BOTTOM_CENTER);
    }
    
    public void displayTotalTable(OrderDish o){
//        for(int i=0;i<orderList.get(index).size();i++){
//            totalTable.getItems().add(orderList.get(index).getDish(i));
//            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        }
        for(int i=0;i<o.size();i++){
            totalTable.getItems().add(o.getDish(i));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        }
    }
    
    public void refreshTable(){
        totalTable.getItems().clear();
    }
    
    public void createRow(int size){
        for(int i=0;i<size;i++){
            Text tmpBill = new Text(orderList.get(i).getBillno());
            Text tmpTno = new Text (orderList.get(i).getTno());
            Text tmpTime = new Text (orderList.get(i).getTimeActive());
            Text tmpMember = new Text (orderList.get(i).getMember());
            Text billText = new Text("BILL-"+orderList.get(i).getBillno());
            billText.setFont(new Font(17));
            Text tableText = new Text(orderList.get(i).getTno());
            tableText.setFont(new Font(17));
            OrderDish orderDish3 = new OrderDish();
            orderDish3 = orderDB.loadDB(tmpTno.getText());
            Text totalText = new Text(Float.toString(sumCost(orderDish3)+calVat(orderDish3)));
            totalText.setFont(new Font(17));
            Text statusText = new Text("Not paid");
            statusText.setFont(new Font(17));
            JFXButton detailBtn = new JFXButton(" Detail ");
            detailBtn.setStyle("-fx-background-color:lightgray");

            detailBtn.setOnAction((ActionEvent e) ->{
                vatTxt.setText("Service Charge ("+info.getInfo_vat()+"%) :");
                discountTxt.setText("Discount ("+info.getInfo_discount()+"%) :");
                detailPane.toFront();
                billDetail.setText(billText.getText());
                dateDetail.setText(tmpTime.getText());
                paidDetail.setText("NO");
                refreshTable();
                OrderDish orderDish = new OrderDish();
                orderDish = orderDB.loadDB(tmpTno.getText());
                displayTotalTable(orderDish);
                grossText.setText(Integer.toString(sumCost(orderDish))+"  Baht");
                vatText.setText(Float.toString(calVat(orderDish))+"  Baht");
                discountText.setText("0"+"  Baht");
                netText.setText(Float.toString(sumCost(orderDish)+
                        calVat(orderDish))+"  Baht");
                for(Member m : memberList.getList()){
                    if(m.getName().equals(tmpMember.getText())){
                        discountText.setText(Float.toString(discount(orderDish))+"  Baht");
                        netText.setText((Float.toString(sumCost(orderDish)+
                        calVat(orderDish)-discount(orderDish)))+"  Baht");
                        break;
                    }
                } 
                confirmButton.setOnAction((ActionEvent event) ->{
                    proceedPane.setVisible(false);
                    vBill.getChildren().remove(billText);
                    vTable.getChildren().remove(tableText);
                    vTotal.getChildren().remove(totalText);
                    vStatus.getChildren().remove(statusText);
                    vDetail.getChildren().remove(detailBtn);
                    confirmButton.setVisible(false);
                    tablePane.toFront();
                    for(Table t : tables){
                        if(t.getTableNum().equals(tmpTno.getText())){
                            t.setStatus("Inactive");
                        }
                    }
                    TableDB tableDB = new TableDB();
                    tableDB.saveDB(tables);
                    Receipt receipt = new Receipt();
                    receipt.saveDb(tmpTno.getText());

                    OrderDish orderDish2 = new OrderDish();
                    OrderDishDB orderDB = new OrderDishDB();
                    orderDish2 = orderDB.loadDB(tmpTno.getText());
                    orderDish2.setBillno("0");
                    orderDish2.clearOrder();
                    orderDish2.setMember("");
                    orderDB.saveDB(orderDish2);
                    
                });
            });
            vBill.getChildren().add(billText);
            vTable.getChildren().add(tableText);
            vTotal.getChildren().add(totalText);
            vStatus.getChildren().add(statusText);
            vDetail.getChildren().add(detailBtn);
        }
    }
    
    public float discount(OrderDish o){
        String discount = info.getInfo_discount();
        return (float) ((float)sumCost(o)*(Float.parseFloat(discount)/100));
    }
    
    public int sumCost(OrderDish o){
        int sum=0;
        for(int i=0;i<o.size();i++){
            sum += (o.getDish(i).getAmount())*(Integer.parseInt(o.getDish(i).getPrice()));
        }
        return sum;
    }
    
    public float calVat(OrderDish o){
        String vat = info.getInfo_vat();
        return (float) (sumCost(o)*(Float.parseFloat(vat)/100));
    }
    
    @FXML
    void selectService(ActionEvent event) {
          if(event.getSource() == backButton){
              tablePane.toFront();
              proceedPane.setVisible(false);
              confirmButton.setVisible(false);
          }
          else if(event.getSource() == proceedButton){
              proceedPane.setVisible(true);
              confirmButton.setVisible(true);
          }
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
            Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Dish.fxml"));
            Scene storageScene = new Scene(storageParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(storageScene);
            window.show();
            timeCustomer1.stopTimer();
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
