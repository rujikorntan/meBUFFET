package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import table.Table;
import table.TableDB;
import java.util.ArrayList;
import orderDish.OrderDish;
import orderDish.OrderDishDB;


public class AddTableController {
    @FXML
    private ComboBox<String> status_box, capacity_box;
    @FXML
    private JFXTextArea tb_name;


    @FXML
    private JFXTextArea tb_capacity;
    @FXML
    private JFXButton cancelBT, comfirmBT;

    private String newTable_name ,newTable_capacity, newTable_status ,newTable_available;
    
    private OrderDishDB orderDB = new OrderDishDB();

    public  void initialize(){
        status_box.getItems().addAll("Active","Inactive");
        status_box.setValue("Status");

        capacity_box.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        capacity_box.setValue("Capacity");

        status_box.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            {
                                super.setPrefWidth(100);
                            }
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    if (item.contains("Inactive")) {
                                        setTextFill(Color.RED);
                                    }
                                    else if (item.contains("Active")){
                                        setTextFill(Color.GREEN);
                                    }
                                }
                                else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });


    }
    @FXML
    void cancelBTgo(ActionEvent event) {
        Stage stage = (Stage) cancelBT.getScene().getWindow();
        stage.close();
    }

    @FXML
    void comfirmBTgo(ActionEvent event) {
         newTable_name = tb_name.getText();
         newTable_capacity = capacity_box.getValue();
         if(newTable_capacity.equals("Capacity")){
             newTable_capacity = "1";
         }
         newTable_status = status_box.getValue();
         if(newTable_status.equals("Status")){
            newTable_status = "Inactive";
         }
        newTable_available = "true";
        if(newTable_name.equals("") || newTable_name.equals(null) || Valid.isTableNameDup(newTable_name)){
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
                         tb_name.setText("");

                     }
                 });
             } catch (Exception e) {
                 System.out.println(e);
             }

         }
         else{



//        System.out.println(newTable_name+ newTable_capacity + newTable_status + newTable_available);

//  Save to Database ===================================================================================================
            ArrayList<Table> tables = new ArrayList<>();
            TableDB tableDB = new TableDB();

            tables = tableDB.loadTempDB();

            Table tableCustomer_Save = new Table(newTable_name, newTable_capacity,
                    newTable_available, newTable_status);

            tables.add(tableCustomer_Save);
            tableDB.saveTempDB(tables);

            //create order_db for new table
            OrderDish orderDish = new OrderDish();
            orderDish.setTno(newTable_name);
            orderDB.saveDB(orderDish);
            OrderDish orderDish2 = new OrderDish();
            orderDish2.setTno(newTable_name);
            orderDB.saveTmpDB(orderDish2);
//======================================================================================================================

            Stage stage = (Stage) cancelBT.getScene().getWindow();
            stage.close();
            System.out.println("closed");
        }

    }



}
