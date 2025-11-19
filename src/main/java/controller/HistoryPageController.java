package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.History;
import service.HistoryPageService;
import service.Impl.HistoryPageServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HistoryPageController implements Initializable {

    ObservableList<History> history = FXCollections.observableArrayList();
    HistoryPageService historyPageService =new HistoryPageServiceImpl();

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
    private Button btnHistory;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colRentalDate;

    @FXML
    private TableColumn<?, ?> colRentalID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colfine;

    @FXML
    private TableView<History> tblHistory;

    Stage BookPageStage = new Stage();
    @FXML
    void btnBookOnAction(ActionEvent event) {
        try {
            BookPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/BooksPage.fxml"))));
            BookPageStage.setResizable(false);
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

    Stage DashboardPage = new Stage();
    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            DashboardPage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardPage.fxml"))));
            DashboardPage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DashboardPage.show();
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
    @FXML
    void btnHistoryOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colRentalID.setCellValueFactory(new PropertyValueFactory<>("Rental_ID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colRentalDate.setCellValueFactory(new PropertyValueFactory<>("Rental_Date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("Return_Date"));
        colfine.setCellValueFactory( new PropertyValueFactory<>("fine"));

        try {
            getAllHistory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getAllHistory() throws SQLException {
        history=historyPageService.getAllHistory();
        tblHistory.setItems(history);
    }
}
