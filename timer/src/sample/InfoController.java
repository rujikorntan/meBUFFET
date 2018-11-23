package sample;

import com.jfoenix.controls.JFXButton;
import info.Info;
import info.InfoDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class InfoController {
    private TimeCustomer timeCustomer1 = new TimeCustomer();
    private Info info ;
    private InfoDB infoDB = new InfoDB();


    @FXML
    private Label secondTime0, minuteTime0, hourTime0;
    @FXML
    private JFXButton editBT,confirmEditButton, cancelEditButton;
    @FXML
    private Text info_company, info_vat, info_phone, info_discount;
    @FXML
    private TextArea input_address, input_detail;
    @FXML
    private TextField input_companyName, input_phone ;
    @FXML
    private AnchorPane paneEdit, paneShow;
    @FXML
    private Label info_address, info_detail;
    @FXML
    private ComboBox<String> input_vatbox, input_discountbox;

    private String username, password;

    void startTimer(){
        timeCustomer1.startTimer(secondTime0, minuteTime0, hourTime0);
    }
    private Info init_info = new Info();

    @FXML
    public  void initialize(){
        paneShow.toFront();
        paneEdit.toBack();
        startTimer();
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);


        init_info = infoDB.loadDB();
        infoDB.saveTempDB(init_info);

        info_company.setText(init_info.getInfo_company());
        info_vat.setText(init_info.getInfo_vat());
        info_address.setText(init_info.getInfo_address());
        info_phone.setText(init_info.getInfo_phone());
        info_discount.setText(init_info.getInfo_discount());
        info_detail.setText(init_info.getInfo_detail());

        info_company.setWrappingWidth(600);
        info_vat.setWrappingWidth(50);
//        info_address.setWrappingWidth(600);
        info_phone.setWrappingWidth(600);
        info_discount.setWrappingWidth(50);
//        info_detail.setWrappingWidth(600);

        info_company.prefHeight(600);
        info_vat.prefHeight(50);
//        info_address.maxHeight(20);
        info_phone.prefHeight(600);
        info_discount.prefHeight(50);
//        info_detail.prefHeight(600);

        input_vatbox.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        input_vatbox.setValue("Service Charge");

        input_discountbox.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    @FXML
    void cancelEdit(ActionEvent event) {
        paneEdit.toBack();
        paneShow.toFront();
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        editBT.setVisible(true);

        input_companyName.setText("");
        input_vatbox.setValue("");
        input_address.setText("");
        input_phone.setText("");
        input_discountbox.setValue("");
        input_detail.setText("");

        init_info = infoDB.loadDB();
        infoDB.saveTempDB(init_info);


    }

    @FXML
    void confirmEdit(ActionEvent event) {
        paneEdit.toBack();
        paneShow.toFront();
        confirmEditButton.setVisible(false);
        cancelEditButton.setVisible(false);
        editBT.setVisible(true);

        info_company.setText(input_companyName.getText());
        info_vat.setText(input_vatbox.getValue());
        info_address.setText(input_address.getText());
        info_phone.setText(input_phone.getText());
        info_discount.setText(input_discountbox.getValue());
        info_detail.setText(input_detail.getText());

        init_info =  infoDB.loadTempDB();

        info = new Info(input_companyName.getText(),input_vatbox.getValue(),input_address.getText(),
                        input_phone.getText(), input_discountbox.getValue(), input_detail.getText(),
                init_info.getInfo_username(),init_info.getInfo_password());

        infoDB.saveDB(info);
    }

    @FXML
    void editTable(ActionEvent event) {
        paneEdit.toFront();
        paneShow.toBack();
        confirmEditButton.setVisible(true);
        cancelEditButton.setVisible(true);
        editBT.setVisible(false);

        input_companyName.setText(info_company.getText());
        input_vatbox.setValue(info_vat.getText());
        input_address.setText(info_address.getText());
        input_phone.setText(info_phone.getText());
        input_discountbox.setValue(info_discount.getText());
        input_detail.setText(info_detail.getText());

    }

    @FXML
    void reset_passwordBT(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reset_Password.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("meBUFFET");
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(new Scene(root1));
            stage2.show();

            stage2.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    init_info = infoDB.loadTempDB();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

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
        Parent storageParent = FXMLLoader.load(getClass().getResource("UI_Report.fxml"));
        Scene storageScene = new Scene(storageParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(storageScene);
        window.show();
        timeCustomer1.stopTimer();
    }
    @FXML
    void go_tabInfo(ActionEvent event)throws IOException {
    }
//======================================================================================================================
}
