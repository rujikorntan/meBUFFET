/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import member.Member;
import member.MemberList;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddMemberPopupController implements Initializable {

    @FXML
    private JFXTextField nameField,idField,phoneField;
    @FXML
    private JFXButton closeButton,confirmButton;
    @FXML
    private JFXDatePicker birthField,enrollField;
    
    private Member mem = new Member();
    private MemberList memberList = new MemberList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        loadDb();
    }    
    
    public void loadDb(){
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            memberList = (MemberList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
        } catch (ClassNotFoundException c) {
            System.out.println("Member class not found");
            System.out.println(c);
        }
    }
    
    public void saveDb(){
        try{
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memberList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void closePopup(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createMember(ActionEvent event) throws IOException {

        if(nameField.getText().equals("") || Valid.isMemberNameOrIdDup(nameField.getText())){
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
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(idField.getText().equals("") || Valid.isMemberNameOrIdDup(idField.getText())){
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
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(phoneField.getText().equals("")){
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
//                        nameField.setText("");
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(birthField.getValue() == null){
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

                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(enrollField.getValue() == null){
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

                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            loadDb();
            mem.setName(nameField.getText());
            mem.setBirth(birthField.getValue().toString());
            mem.setId(idField.getText());
            mem.setEnroll(enrollField.getValue().toString());
            mem.setTel(phoneField.getText());
            memberList.append(mem);
            saveDb();
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        }

    }
    
}
