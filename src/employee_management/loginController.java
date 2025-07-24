package employee_management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML private TextField uid_field;
    @FXML private TextField p_field;
    @FXML private Button Btn1;

    private final String AUTH_ID = "admin";
    private final String AUTH_PASS = "9999";

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    private void Login(ActionEvent event) {
        String username = uid_field.getText();
        String password = p_field.getText();

        if(username.equals(AUTH_ID) && password.equals(AUTH_PASS)) {
            try {
                Stage currentStage = (Stage) Btn1.getScene().getWindow();
                currentStage.close();

                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Employee Management System - Dashboard");
                stage.setScene(new Scene(root));
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password");
            alert.showAndWait();
        }
    }
}
