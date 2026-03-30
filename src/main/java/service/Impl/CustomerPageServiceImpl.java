package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import repository.CustomerPageRepository;
import repository.Impl.CustomerPageRepositoryImpl;
import service.CustomerPageService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerPageServiceImpl implements CustomerPageService {

    ObservableList<Customer> customer = FXCollections.observableArrayList();
    CustomerPageRepository customerPageRepository = new CustomerPageRepositoryImpl();


    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        ResultSet resultSet = customerPageRepository.getAllCustomers();
        while (resultSet.next()){
            customer.add(new Customer(
                    resultSet.getString("Cust_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
            ));
        }
        return customer;
    }



    @Override
    public ObservableList<Customer> getSearchedCustomers(String Cust_ID) throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ResultSet resultSet = customerPageRepository.getSearchedCustomers(Cust_ID);

        while (resultSet.next()) {
            customers.add(new Customer(
                    resultSet.getString("Cust_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Email"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Address")
            ));
        }

        return customers;

    }


    @Override
    public void addCustomer(String Cust_ID, String Name, String Phone, String Email, String Address) {
        customerPageRepository.addCustomer(Cust_ID, Name, Phone, Email, Address);
    }

    @Override
    public void deleteCustomer(String Cust_ID) {
        customerPageRepository.deleteCustomer(Cust_ID);
    }

    @Override
    public void updateCustomer(String Cust_ID, String Name, String Phone, String Email, String Address) {
        customerPageRepository.updateCustomer(Cust_ID, Name, Phone, Email, Address);
    }
}
