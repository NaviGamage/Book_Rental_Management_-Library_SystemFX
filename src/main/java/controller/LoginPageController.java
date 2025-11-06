package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.Impl.LoginServiceImpl;
import service.LoginService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    @FXML private JFXButton btnLogin;

    @FXML private JFXTextField txtEmail;

    @FXML private JFXPasswordField txtPassword;

    private final LoginService loginService = new LoginServiceImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter email and password!");
            return;
        }

        String userRole = loginService.authenticateUser(email, password);

        if (userRole != null) {
            switch (userRole.toUpperCase()) {
                case "ADMIN":
                    loadDashboard("/view/AdminDashboardPage.fxml", "Admin Dashboard");
                    break;
                case "STAFF":
                    loadDashboard("/view/StaffDashboardPage.fxml", "Staff Dashboard");
                    break;
                default:
                    showAlert("Access Denied", "Unknown user role!");
            }
        } else {
            showAlert("Error", "Invalid login credentials!");
        }
    }

    private void loadDashboard(String fxmlPath, String title) {
        try {
            Stage currentStage = (Stage) txtEmail.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
            newStage.setTitle(title);
            newStage.show();


        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load " + title);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}