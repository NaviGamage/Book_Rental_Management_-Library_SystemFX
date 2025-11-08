package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerPageRepository {

    ResultSet getAllCustomers()throws SQLException;

    void addCustomer(String custID,String name,String phone,String email,String address);

    void deleteCustomer(String custID);

    void updateCustomer(String custID,String name,String phone,String email,String address);
}
