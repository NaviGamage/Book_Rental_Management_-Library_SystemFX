package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerPageRepository {

    ResultSet getAllCustomers()throws SQLException;

    ResultSet getSearchedCustomers(String Cust_ID) throws SQLException;

    void addCustomer(String Cust_ID,String Name,String Phone,String Email,String Address);

    void deleteCustomer(String Cust_ID);

    void updateCustomer(String Cust_ID, String Name, String Phone, String Email, String Address);


}
