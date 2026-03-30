package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffDashboardPageController {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    Stage DashboardPageStage = new Stage();
    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            DashboardPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardPage.fxml.fxml"))));
            DashboardPageStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    Stage RentalBooksStage = new Stage();
    @FXML
    void btnRentalBooksOnAction(ActionEvent event) {
        try {
            RentalBooksStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RentalBooksPage.fxml"))));
            RentalBooksStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RentalBooksStage.show();
    }

    Stage ReturnBooksStage = new Stage();
    @FXML
    void btnRetunrBooksOnAction(ActionEvent event) {

        try {
            ReturnBooksStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBooksPage.fxml"))));
            ReturnBooksStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ReturnBooksStage.show();

    }

}
