package employee_management;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DashboardController implements Initializable {

    @FXML private TextField emid_field;
    @FXML private TextField fn_field;
    @FXML private TextField mail_field;
    @FXML private TextField dept_field;
    @FXML private TextField degis_fiekd;
    @FXML private TextField salary_field;
    @FXML private TextField gender_field;
    @FXML private TextField edu_qalification;
    @FXML private TextField skill_field;
    @FXML private ListView<Employee> listView;
    @FXML private TextField search_field;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(employeeList);

        // ListView ক্লিক করলে popup তে দেখাবে
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if(newSel != null) {
                showEmployeePopup(newSel);
            }
        });
    }

    @FXML
    private void added(ActionEvent event) {
        String id = emid_field.getText();
        String fullName = fn_field.getText();
        String email = mail_field.getText();
        String dept = dept_field.getText();
        String degis = degis_fiekd.getText();
        String salaryStr = salary_field.getText();
        String gender = gender_field.getText();
        String edu = edu_qalification.getText();
        String skill = skill_field.getText();

        if(id.isEmpty() || fullName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID and Full Name are required.");
            return;
        }

        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch(NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Salary must be a number.");
            return;
        }

        Employee emp = new Employee(id, fullName, email, dept, degis, salary, gender, edu, skill);

        employeeList.add(emp);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added successfully.");
    }

    @FXML
    private void update(ActionEvent event) {
        Employee selected = listView.getSelectionModel().getSelectedItem();
        if(selected == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select an employee to update.");
            return;
        }

        try {
            selected.setId(emid_field.getText());
            selected.setFullName(fn_field.getText());
            selected.setEmail(mail_field.getText());
            selected.setDepartment(dept_field.getText());
            selected.setDesignation(degis_fiekd.getText());
            selected.setSalary(Double.parseDouble(salary_field.getText()));
            selected.setGender(gender_field.getText());
            selected.setEducation(edu_qalification.getText());
            selected.setSkill(skill_field.getText());

            listView.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully.");
            clearFields();
        } catch(NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Salary must be a number.");
        }
    }

    @FXML
    private void delete_field(ActionEvent event) {
        Employee selected = listView.getSelectionModel().getSelectedItem();
        if(selected == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select an employee to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to delete this employee?");
        Optional<ButtonType> result = confirm.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            employeeList.remove(selected);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Deleted", "Employee deleted successfully.");
        }
    }

    @FXML
    private void search(ActionEvent event) {
        String keyword = search_field.getText().toLowerCase();

        ObservableList<Employee> filteredList = FXCollections.observableArrayList();
        for(Employee emp : employeeList) {
            if(emp.getId().toLowerCase().contains(keyword) || emp.getFullName().toLowerCase().contains(keyword)) {
                filteredList.add(emp);
            }
        }

        listView.setItems(filteredList);
    }

    @FXML
    private void logout(ActionEvent event) {
        Stage currentStage = (Stage) emid_field.getScene().getWindow();
        currentStage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Employee Management System - Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
private void upload_img(ActionEvent event) {
    
    System.out.println("Upload image button clicked!");
}
    private void clearFields() {
        emid_field.clear();
        fn_field.clear();
        mail_field.clear();
        dept_field.clear();
        degis_fiekd.clear();
        salary_field.clear();
        gender_field.clear();
        edu_qalification.clear();
        skill_field.clear();
        search_field.clear();
    }

    private void showEmployeePopup(Employee emp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String currentDateTime = dtf.format(LocalDateTime.now());

        String info = String.format(
            "Employee ID: %s\nFull Name: %s\nEmail: %s\nDepartment: %s\nDesignation: %s\nSalary: %.2f\nGender: %s\nEducation: %s\nSkills: %s\n\nDate & Time: %s",
            emp.getId(), emp.getFullName(), emp.getEmail(), emp.getDepartment(), emp.getDesignation(), emp.getSalary(),
            emp.getGender(), emp.getEducation(), emp.getSkill(), currentDateTime
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Employee Details");
        alert.setHeaderText("Details of " + emp.getFullName());
        alert.setContentText(info);
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
