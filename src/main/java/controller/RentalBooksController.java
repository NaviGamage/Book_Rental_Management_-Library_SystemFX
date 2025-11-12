package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RentalBook;
import service.Impl.RentalBookPageServiceImpl;
import service.RentalBookPageService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class RentalBooksController implements Initializable {

    ObservableList<RentalBook> rentalBooks = FXCollections.observableArrayList();
    RentalBookPageService rentalBookPageService = new RentalBookPageServiceImpl();

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colRentalDate;

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
    private JFXButton btnDeleteRentalBook;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    @FXML
    private Button btnSearchBook;

    @FXML
    private JFXButton btnUpdateRentalBook;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colRentalID;

    @FXML
    private TableView<RentalBook> tblRentalBook;

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
    private TextField txtSearch;


    @FXML
    void btnAddRentalBookOnAction(ActionEvent event) throws SQLException {

        int Rental_ID = Integer.parseInt(txtRentalID.getText());
        int id = Integer.parseInt(txtBookIID.getText());
        String Cust_ID =txtCustID.getText();
        String Rental_Date = txtRentalDate.getText();
        String Due_Date = txtDueDate.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());

        rentalBookPageService.addRentalBooks(Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity);
        rentalBooks.clear();

        clearRentalBooks();
        getAllRentalBooks();
    }



    @FXML
    void btnBookOnAction(ActionEvent event) {



    }

    @FXML
    void btnClearRentalBookOnAction(ActionEvent event) {

        clearRentalBooks();

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteRentalBookOnAction(ActionEvent event) throws SQLException {

        int Rental_ID = Integer.parseInt(txtRentalID.getText());
        rentalBookPageService.deleteRental(Integer.parseInt(String.valueOf(Rental_ID)));
        clearRentalBooks();
        getAllRentalBooks();

    }

    @FXML
    void btnRentalBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnRetunrBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws SQLException {
        int Rental_ID = Integer.parseInt(txtSearch.getText());
        rentalBooks.clear();
        rentalBooks = rentalBookPageService.searchRental(Rental_ID);
        tblRentalBook.setItems(rentalBooks);
    }

    @FXML
    void btnUpdateRentalBookOnAction(ActionEvent event) throws SQLException {

        int Rental_ID = Integer.parseInt(txtRentalID.getText());
        int id = Integer.parseInt(txtBookIID.getText());
        String Cust_ID =txtCustID.getText();
        String Rental_Date = txtRentalDate.getText();
        String Due_Date = txtDueDate.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());

        rentalBookPageService.updateRentalBooks(Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity);
        clearRentalBooks();
        getAllRentalBooks();



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
            getAllRentalBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblRentalBook.getSelectionModel().selectedItemProperty().addListener(((observableValue, rentalBook, newValue) -> {

           if (newValue!=null){

               System.out.println(newValue);

               setSelectedValue(newValue);

           }

       }));


    }

    private void setSelectedValue(RentalBook selectedValue) {
        txtRentalID.setText(String.valueOf(selectedValue.getRental_ID()));
        txtBookIID.setText(String.valueOf(selectedValue.getId()));
        txtCustID.setText(selectedValue.getCust_ID());
        txtRentalDate.setText(String.valueOf(selectedValue.getRental_Date()));
        txtDueDate.setText(String.valueOf(selectedValue.getDue_Date()));
        txtQuantity.setText(String.valueOf(selectedValue.getQuantity()));
    }

    private void getAllRentalBooks() throws SQLException {
        rentalBooks.clear();
        rentalBooks = rentalBookPageService.getAllRentalBooks();
        tblRentalBook.setItems(rentalBooks);
    }

    private void clearRentalBooks() {

        txtRentalID.setText(null);
        txtBookIID.setText(null);
        txtCustID.setText(null);
        txtRentalDate.setText(null);
        txtDueDate.setText(null);
        txtQuantity.setText(null);
    }
}
