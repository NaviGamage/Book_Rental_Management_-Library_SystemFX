package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardPageController {

    @FXML
    private Button btnBook;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    @FXML
    private Label lblToatalBooks;

    @FXML
    private Label lblToatalCustomer;

    Stage BookPageStage = new Stage();
    @FXML
    void btnBookOnAction(ActionEvent event) {

        try {
            BookPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/BooksPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BookPageStage.show();

    }
    Stage CustomerPageStage = new Stage();
    @FXML
    void btnCustomerOnAction(ActionEvent event) {

        try {
            CustomerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CustomerPageStage.show();
    }


    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    Stage RentalBooksStage = new Stage();
    @FXML
    void btnRentalBooksOnAction(ActionEvent event) {
        try {
            RentalBooksStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RentalBooksPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RentalBooksStage.show();
    }

    Stage ReturnBooksStage = new Stage();
    @FXML
    void btnRetunrBooksOnAction(ActionEvent event) {

        try {
            RentalBooksStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBooksPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RentalBooksStage.show();

    }

}
