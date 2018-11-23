package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import info.Info;
import info.InfoDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ResetPasswordPopupController {
    @FXML
    private JFXButton confirmBT;

    @FXML
    private JFXButton cancelBT;

    @FXML
    private JFXPasswordField password_new1;

    @FXML
    private JFXPasswordField password_new2;

    @FXML
    private JFXPasswordField password_old;

    @FXML
    void cancelReset(ActionEvent event) {
        Stage stage = (Stage) cancelBT.getScene().getWindow();
        stage.close();
    }
    @FXML
    private InfoDB infoDB = new InfoDB();
    private Info  info = new Info();
    public  void initialize(){
         info = infoDB.loadTempDB();
    }
    @FXML
    void confirmReset(ActionEvent event) {

        if(password_old.getText().equals(info.getInfo_password())){
            if(password_new1.getText().equals(password_new2.getText())){
                info.setInfo_password(password_new2.getText());
                infoDB.saveTempDB(info);
                Stage stage = (Stage) confirmBT.getScene().getWindow();
                stage.close();
            }
        }


    }
}
