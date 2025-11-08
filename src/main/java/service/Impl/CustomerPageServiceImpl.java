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

    ObservableList<Customer>customers = FXCollections.observableArrayList();
    CustomerPageRepository customerPageRepository = new CustomerPageRepositoryImpl();


    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        ResultSet resultSet = customerPageRepository.getAllCustomers();
        while (resultSet.next()){
            customers.add(new Customer(
               resultSet.getString("CustID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
            ));
        }
        return customers;
    }

    @Override
    public void addCustomer(String custID, String name, String phone, String email, String address) {
        customerPageRepository.addCustomer(custID, name, phone, email, address);
    }

    @Override
    public void deleteCustomer(String custID) {
        customerPageRepository.deleteCustomer(custID);
    }

    @Override
    public void updateCustomer(String custID, String name, String phone, String email, String address) {
        customerPageRepository.updateCustomer(custID, name, phone, email, address);
    }
}
