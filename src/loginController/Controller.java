package loginController;


import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginApp.LoginModel;
import loginApp.option;
import user.UserController;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    LoginModel loginModel = new LoginModel();


    @FXML
    private Label dbstatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("Not Connected To Database");
        }
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));

    }

    @FXML
    public void Login(ActionEvent event) {
        try {

            if (this.loginModel.isLogin(this.userName.getText(), this.password.getText(), ((option) this.combobox.getValue()).toString())) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option) this.combobox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "User":
                        userLogin();
                        break;

                }
            } else {
                this.loginStatus.setText("Wrong User Name or Password");
            }

        } catch (Exception localException) {
            JOptionPane.showMessageDialog(null,localException.getMessage().toString());

        }


    }

    public void userLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/user/userFXML.fxml").openStream());

            UserController userController = (UserController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("User Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());
            AdminController adminController = (AdminController) adminLoader.getController();
            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Administrator Dashboard");
            adminStage.setResizable(false);
            adminStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
