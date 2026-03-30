package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.RentalBook;
import model.dto.ReturnBook;
import service.BookPageService;
import service.Impl.BookPageServiceImpl;
import service.Impl.RentalBookPageServiceImpl;
import service.Impl.ReturnBookPageServiceImpl;
import service.RentalBookPageService;
import service.ReturnBookPageService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReturnBooksController implements Initializable {

    ReturnBookPageService returnBookPageService = new ReturnBookPageServiceImpl();
    BookPageService bookPageService = new BookPageServiceImpl();
    ObservableList<RentalBook> rentalBooks = FXCollections.observableArrayList();



    @FXML
    private JFXButton btnAddRentalBook;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnClearRentalBook;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    @FXML
    private Button btnSearchBook;

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
    private TableView<RentalBook> tblReturnBook;

    @FXML
    private JFXTextField txtBookIID;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtDueDate;


    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtRentalDate;

    @FXML
    private JFXTextField txtRentalID;

    @FXML
    private JFXTextField txtReturDate;

    @FXML
    private TextField txtSearch;


    @FXML
    void btnAddReturnBookOnAction(ActionEvent event) throws SQLException {


        int Rental_ID = Integer.parseInt(txtRentalID.getText());
        int id = Integer.parseInt(txtBookIID.getText());
        String Cust_ID = txtCustID.getText();
        String Rental_Date = txtRentalDate.getText();
        String Due_Date = txtDueDate.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        String Return_Date = txtReturDate.getText();


        double fine = calculateFine(Due_Date, Return_Date);
        if (!showConfirmation("Confirm Return", "Are you sure you want to process this return?\nFine Amount: $" + fine)) {
            return;
        }

        returnBookPageService.setReturn(Rental_ID, id, Cust_ID, Rental_Date, Due_Date, quantity,Return_Date,fine);

        bookPageService.addedBookQty(quantity, id);

        clearRentalBooks();
        getAllReturnBooks();
    }

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

    @FXML
    void btnClearRentalBookOnAction(ActionEvent event) {

        clearRentalBooks();
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

    Stage HistoryPageStage = new Stage();
    @FXML
    void btnHistoryOnAction(ActionEvent event) {

        try {
            HistoryPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/HistoryPage.fxml"))));
            HistoryPageStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HistoryPageStage.show();

    }

    @FXML
    void btnRetunrBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws SQLException {
        int Rental_ID = Integer.parseInt(txtSearch.getText());
        rentalBooks.clear();
        rentalBooks = returnBookPageService.searchRental(Rental_ID);
        tblReturnBook.setItems(rentalBooks);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colRentalID.setCellValueFactory(new PropertyValueFactory<>("Rental_ID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colRentalDate.setCellValueFactory(new PropertyValueFactory<>("Rental_Date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        try {
            getAllReturnBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblReturnBook.getSelectionModel().selectedItemProperty().addListener((observableValue, returnBook, newValue) -> {

            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });

    }

    private void setSelectedValue(ReturnBook selectedValue) {

        txtRentalID.setText(String.valueOf(selectedValue.getRental_ID()));
        txtBookIID.setText(String.valueOf(selectedValue.getId()));
        txtCustID.setText(selectedValue.getCust_ID());

        txtRentalDate.setText(selectedValue.getRental_Date().toString());
        txtDueDate.setText(selectedValue.getDue_Date().toString());

        txtQuantity.setText(String.valueOf(selectedValue.getQuantity()));
    }


    private void getAllReturnBooks() throws SQLException {
        rentalBooks.clear();
        rentalBooks= returnBookPageService.getAllReturnBooks();
        tblReturnBook.setItems(rentalBooks);
    }


    private void clearRentalBooks() {
        txtRentalID.setText(null);
        txtBookIID.setText(null);
        txtCustID.setText(null);
        txtRentalDate.setText(null);
        txtQuantity.setText(null);
        txtReturDate.setText(null);
        txtDueDate.setText(null);
    }

    public double calculateFine(String dueDate, String returnDate) {

        LocalDate due = LocalDate.parse(dueDate);
        LocalDate ret = LocalDate.parse(returnDate);

        long overdueDays = DAYS.between(due, ret);

        if (overdueDays <= 0) return 0;

        return overdueDays * 50;
    }



    private boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}




