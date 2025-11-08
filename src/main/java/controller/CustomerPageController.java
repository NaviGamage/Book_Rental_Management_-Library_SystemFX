package controller;

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
import model.dto.Customer;
import service.CustomerPageService;
import service.Impl.CustomerPageServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    CustomerPageService customerPageService = new CustomerPageServiceImpl();
    ObservableList<Customer> customers= FXCollections.observableArrayList();

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnRentalBooks;

    @FXML
    private Button btnRetunrBooks;

    @FXML
    private Button btnSearchCustomer;

    @FXML
    private Button btnUpdateCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws SQLException {

        String Cust_ID = txtCustomerID.getText();
        String Name = txtCustomerName.getText();
        String Phone = txtPhoneNumber.getText();
        String Email = txtEmail.getText();
        String Address = txtAddress.getText();
        customerPageService.addCustomer(Cust_ID, Name ,Phone ,Email,Address);

        getAllCustomer();
        clearCustomer();

    }




    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {



    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws SQLException {

        String Cust_ID = txtCustomerID.getText();
        customerPageService.deleteCustomer(Cust_ID);
        getAllCustomer();
        clearCustomer();

    }

    @FXML
    void btnRentalBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnRetunrBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) throws SQLException {
        String Cust_ID = txtSearch.getText();
        customers.clear();
        customers = customerPageService.getSearchedCustomers(Cust_ID);
        tblCustomer.setItems(customers);
    }



    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException {

        String Cust_ID = txtCustomerID.getText();
        String Name    = txtCustomerName.getText();
        String Phone   = txtPhoneNumber.getText();
        String Email   = txtEmail.getText();
        String Address = txtAddress.getText();
        customerPageService.updateCustomer(Cust_ID, Name, Phone, Email, Address);

        getAllCustomer();
        clearCustomer();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));

        try {
            getAllCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void setSelectedValue(Customer selectedValue ) {

        txtCustomerID.setText(selectedValue.getCust_ID());
        txtCustomerName.setText(selectedValue.getName());
        txtPhoneNumber.setText(selectedValue.getPhone());
        txtEmail.setText(selectedValue.getEmail());
        txtAddress.setText(selectedValue.getAddress());
    }

    private void getAllCustomer() throws SQLException {

        customers.clear();
        customers = customerPageService.getAllCustomers();
        tblCustomer.setItems(customers);
    }

    private void clearCustomer() {

        txtCustomerID.setText(null);
        txtCustomerName.setText(null);
        txtPhoneNumber.setText(null);
        txtEmail.setText(null);
        txtAddress.setText(null);
    }
}
