package employee_management;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {

    @FXML
    private TextField uid_field;

    @FXML
    private TextField p_field;

    @FXML
    private Button Btn1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void Login(ActionEvent event) {
        String uid = uid_field.getText();
        String pass = p_field.getText();

        if(uid.equals("admin") && pass.equals("9999")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome, Admin!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid ID or Password");
            alert.showAndWait();
        }
    }
}
