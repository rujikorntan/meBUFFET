package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import info.Info;
import info.InfoDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class loginController {


    @FXML
    private JFXPasswordField  pwLogin;

    @FXML
    private JFXButton loginBT;

    @FXML
    private JFXTextField usernameLogin;

    @FXML
    private Text invalidLogin;

    @FXML
    private JFXButton cancelBT;

    @FXML
    void cancelLogin(ActionEvent event) {
        // Close window
        Stage stage = (Stage) cancelBT.getScene().getWindow();
        stage.close();
    }

    @FXML
    void goTologin(ActionEvent event) {
        invalidLogin.setVisible(false);
        String username = usernameLogin.getText();
        String password = pwLogin.getText();
        InfoDB infoDB = new InfoDB();
        Info info = new Info();
        info = infoDB.loadDB();

        if(username.equals(info.getInfo_username()) && password.equals(info.getInfo_password())){
            System.out.println("Welcome");
            Stage stage = (Stage) cancelBT.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI_Orderdish.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("meBUFFET");
                stage2.setScene(new Scene(root1));
                stage2.show();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        else {
            invalidLogin.setVisible(true);

        }

    }


}
