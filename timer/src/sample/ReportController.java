package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import orderDish.OrderDish;
import orderDish.OrderDishDB;
import report.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import report.ReportDB;
import report.ReportDate;
import report.ReportDish;
import table.Table;
import table.TableDB;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class ReportController {
    private TimeCustomer timeCustomer1 = new TimeCustomer();

    @FXML
    private Label secondTime0, minuteTime0, hourTime0;
    @FXML
    private ComboBox<String> dateBox;

    @FXML
    private TableView<Report> reportTable;
    @FXML
    private TableColumn<Report, String> col_dateTime;
    @FXML
    private TableColumn<Report, String> col_dishName;
    @FXML
    private TableColumn<Report, Integer>col_Amount, col_totalPrice;
    @FXML
    private BarChart<?, ?> incomeChart;
    @FXML
    private CategoryAxis chart_x;
    @FXML
    private NumberAxis chart_y;

    @FXML
    private AnchorPane paneShow;

    private ArrayList<Report> reports = new ArrayList<>();
    private ReportDB reportDB = new ReportDB();
    private LocalDate localDateTime =  LocalDate.now();

    private XYChart.Series set1 =new XYChart.Series<>();
    private OrderDishDB orderDishDB= new OrderDishDB();
    private TableDB tableDB = new TableDB();
    private ArrayList<Table> tables = new ArrayList<>();
    private OrderDish orderDish = new OrderDish();
    private ArrayList<OrderDish> order_available = new ArrayList<>();
    private ArrayList<ReportDate> reportDates = new ArrayList<>();
    private ArrayList<String> order_getTimes = new ArrayList<>();
    @FXML
    public  void initialize() {
        startTimer();

        tables = tableDB.loadDB();
        for (Table ele : tables){
            orderDish = orderDishDB.loadTmpDB(ele.getTableNum());
            if(orderDish.size() != 0){
                order_available.add(orderDish);
            }
        }
        int count_order_getTimes = 0;

        for (int i = 0; i < order_available.size(); i++) {
            System.out.println(order_available.get(i).getTimeActive());
            // Get date
            if(i == 0){
                ArrayList<ReportDish> reportDishes = new ArrayList<>();

                for (int j = 0; j < order_available.get(i).size(); j++) {                                   // add dish
                    reportDishes.add(new ReportDish(order_available.get(i).getDish(j).getName(),
                                                    order_available.get(i).getDish(j).getPrice(),
                                                    order_available.get(i).getDish(j).getAmount()));
                }
                order_getTimes.add(order_available.get(i).getTimeActive().substring(0, 10));

                reportDates.add(new ReportDate(order_getTimes.get(count_order_getTimes)));            // add date to report
                for (int z = 0; z < reportDates.size(); z++) {              // add dish to report
                    System.out.println("date : " +reportDates.get(z).getDate());
                    for (int j = 0; j < reportDishes.size(); j++) {
                        reportDates.get(z).addDish(reportDishes.get(j));
                        System.out.println(reportDates.get(z).getDish(j).getAmount());
                    }
                    System.out.println("===");
                }

            }else{
                for (int j = 0; j < order_getTimes.size(); j++) {
                    if(!(order_available.get(i).getTimeActive().substring(0, 10).equals(order_getTimes.get(j)))  ) { //  Not duplicate date
                        if (j==order_getTimes.size() -1){
                            count_order_getTimes++;
                            ArrayList<ReportDish> reportDishes = new ArrayList<>();

                            for (int a = 0; a < order_available.get(i).size(); a++) {                                   // add dish
                                reportDishes.add(new ReportDish(order_available.get(i).getDish(a).getName(),
                                        order_available.get(i).getDish(a).getPrice(),
                                        order_available.get(i).getDish(a).getAmount()));
                            }

                            order_getTimes.add(order_available.get(i).getTimeActive().substring(0, 10));

                            reportDates.add(new ReportDate(order_getTimes.get(count_order_getTimes)));            // add date to report
                            System.out.println("date : " +reportDates.get(count_order_getTimes).getDate());

                            for (int k = 0; k < reportDishes.size(); k++) {
                                reportDates.get(count_order_getTimes).addDish(reportDishes.get(k));             // add dish to report
                                System.out.println(reportDates.get(count_order_getTimes).getDish(k).getAmount());
                            }
                            System.out.println("===");
                            break;

                        }

                    }
                    else {                          //   duplicate date
                        for (int k = 0; k < reportDates.size(); k++) {
                            if(order_available.get(i).getTimeActive().substring(0, 10).equals(reportDates.get(k).getDate()) ){

                                System.out.println(reportDates.get(k).getDate());
                                    for (int m = 0; m < order_available.get(i).size(); m++) {
                                        for (int l = 0; l < reportDates.get(k).size(); l++) {
                                            if(order_available.get(i).getDish(m).getName().equals(reportDates.get(k).getDish(l).getName())){
                                                reportDates.get(k).getDish(l).setAmount(order_available.get(i).getDish(m).getAmount()
                                                        + reportDates.get(k).getDish(l).getAmount());
                                                break;
                                            }else if(l == (reportDates.get(k).size()-1)){
                                                ReportDish reportDishes2 = new ReportDish(order_available.get(i).getDish(m).getName(),
                                                                                            order_available.get(i).getDish(m).getPrice(),
                                                                                            order_available.get(i).getDish(m).getAmount());

                                                reportDates.get(k).addDish(reportDishes2);
                                                break;
                                        }
                                    }
                                }
                            }
                        }


                        break;
                    }
                }
            }
        }
        Collections.sort(order_getTimes);
        for (int i = 0; i < order_getTimes.size(); i++) {           // Add item combo box
            dateBox.getItems().addAll(order_getTimes.get(i));
        }

        System.out.println("==========================================================================================");
        for (int i = 0; i < reportDates.size(); i++) {
            System.out.println(reportDates.get(i).getDate());

            for (int j = 0; j < reportDates.get(i).size(); j++) {
                System.out.println(reportDates.get(i).getDish(j).getAmount());
            }
            System.out.println("======");
        }
    }
    @FXML
    void select_dateBT(ActionEvent event) {
        String chooseDate = dateBox.getValue();
//        showToTable();
//        System.out.println(chooseDate);
        for (int i = 0; i < reportDates.size(); i++) {
            if(chooseDate.equals(reportDates.get(i).getDate())){
                System.out.println("select date :" + reportDates.get(i).getDate());
                showToTable(reportDates.get(i));

            }
        }

    }

    public void showToTable(ReportDate date){
        // Clear
        incomeChart.getData().clear();
        incomeChart.layout();
        set1.getData().clear();
        reportTable.getItems().clear();

        for (int a=0; a<date.size();a++) {
            set1.getData().add(new XYChart.Data(date.getDish(a).getName(),date.getDish(a).getAmount()));
        }
        incomeChart.getData().addAll(set1);

        // Table
        ArrayList<Report> report = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            report.add(new Report(date.getDate(), date.getDish(i).getName(), date.getDish(i).getAmount(),
                    Integer.parseInt(date.getDish(i).getPrice()) *  date.getDish(i).getAmount()));
        }
        for(Report element : report){
            addToTable(element);
        }


    }
    public void addToTable(Report tableCustomer){
        reportTable.getItems().add(tableCustomer);

        col_dateTime.setCellValueFactory(new PropertyValueFactory<>("report_dateTime"));
        col_dishName.setCellValueFactory(new PropertyValueFactory<>("report_dishName"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("report_amount"));
        col_totalPrice.setCellValueFactory(new PropertyValueFactory<>("report_totalPrice"));

    }
    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
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
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Member.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabReport(ActionEvent event) throws IOException{
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
