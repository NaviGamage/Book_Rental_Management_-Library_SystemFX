package service;

import javafx.collections.ObservableList;
import model.dto.Customer;

import java.sql.SQLException;

public interface CustomerPageService {

    ObservableList<Customer>getAllCustomers()throws SQLException;

    ObservableList<Customer> getSearchedCustomers(String Cust_ID) throws SQLException;

    void addCustomer(String Cust_ID, String Name, String Phone, String Email, String Address);

    void deleteCustomer(String Cust_ID);

    void updateCustomer(String Cust_ID,String Name,String Phone,String Email,String Address);
}
