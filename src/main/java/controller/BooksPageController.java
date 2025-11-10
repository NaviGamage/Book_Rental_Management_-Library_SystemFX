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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.Books;
import service.BookPageService;
import service.Impl.BookPageServiceImpl;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BooksPageController implements Initializable {

    BookPageService bookPageService =new BookPageServiceImpl();
    ObservableList<Books>books= FXCollections.observableArrayList();



    @FXML
    private JFXButton btnAddBook;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnClearBook;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDashboard;

    @FXML
    private JFXButton btnDeleteBook;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    @FXML
    private Button btnSearchBook;

    @FXML
    private JFXButton btnUpdateBook;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colISBM;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuntity;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtBookID;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXTextField txtISBM;

    @FXML
    private JFXTextField txtQuntity;

    @FXML
    private TableView<Books> tblBook;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddBookOnAction(ActionEvent event) throws SQLException {

        int id = Integer.parseInt(txtBookID.getText());
        String isbm=txtISBM.getText();
        String name=txtBookName.getText();
        String author=txtAuthor.getText();
        String category =txtCategory.getText();
        int quantity =Integer.parseInt(txtQuntity.getText());
        bookPageService.addBooks(id,isbm,name,author,category,quantity);

        getAllBooks();
        clearBook();


    }



    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearBookOnAction(ActionEvent event) {

        clearBook();

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

    Stage DashboardPageStage = new Stage();
    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            DashboardPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboardPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DashboardPageStage.show();
    }

    @FXML
    void btnDeleteBookOnAction(ActionEvent event) throws SQLException {

        int id = Integer.parseInt(txtBookID.getText());
        bookPageService.deleteBooks(id);

        getAllBooks();
        clearBook();


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
            ReturnBooksStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReturnBooksPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ReturnBooksStage.show();
    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws SQLException {

        int id =Integer.parseInt(txtSearch.getText());
        books.clear();
        books = bookPageService.getSearchBooks(id);
        tblBook.setItems(books);

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) throws SQLException {

        int id = Integer.parseInt(txtBookID.getText());
        String isbn=txtISBM.getText();
        String name=txtBookName.getText();
        String author=txtAuthor.getText();
        String category =txtCategory.getText();
        int quantity =Integer.parseInt(txtQuntity.getText());
        bookPageService.updateBooks(id,isbn,name,author,category,quantity);

        getAllBooks();
        clearBook();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colISBM.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuntity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try {
            getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblBook.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void setSelectedValue(Books selectedValue) {

        txtBookID.setText(String.valueOf(selectedValue.getId()));
        txtISBM.setText(selectedValue.getIsbn());
        txtBookName.setText(selectedValue.getName());
        txtAuthor.setText(selectedValue.getAuthor());
        txtCategory.setText(selectedValue.getCategory());
        txtQuntity.setText(String.valueOf(selectedValue.getQuantity()));
    }



    private void getAllBooks() throws SQLException {
        books.clear();
        books=bookPageService.getAllBooks();
        tblBook.setItems(books);
    }
    private void clearBook() {

        txtBookID.setText(null);
        txtISBM.setText(null);
        txtBookName.setText(null);
        txtAuthor.setText(null);
        txtCategory.setText(null);
        txtQuntity.setText(null);
    }
}

