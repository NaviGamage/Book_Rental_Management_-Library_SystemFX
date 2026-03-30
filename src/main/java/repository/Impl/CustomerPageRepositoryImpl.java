package repository.Impl;

import db.DBConnection;
import repository.CustomerPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerPageRepositoryImpl implements CustomerPageRepository {

    @Override
    public ResultSet getAllCustomers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public ResultSet getSearchedCustomers(String Cust_ID) throws SQLException {
        String SQL = "SELECT * FROM customer WHERE Cust_ID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,Cust_ID);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;

    }

    @Override
    public void addCustomer(String Cust_ID, String Name, String Phone, String Email, String Address) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer(Cust_ID,  Name,  Phone,  Email,  Address) VALUES(?,?,?,?,?)");
            pstm.setObject(1,Cust_ID);
            pstm.setObject(2,Name);
            pstm.setObject(3,Phone);
            pstm.setObject(4,Email);
            pstm.setObject(5,Address);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void deleteCustomer(String Cust_ID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE Cust_ID = ?");
            pstm.setObject(1,Cust_ID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCustomer(String Cust_ID, String Name, String Phone, String Email, String Address) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement("UPDATE customer SET Cust_ID=?, Name=?, Phone=?, Email=?, Address=? WHERE Cust_ID=?");
            pstm.setObject(1, Cust_ID);
            pstm.setObject(2, Name);
            pstm.setObject(3, Phone);
            pstm.setObject(4, Email);
            pstm.setObject(5, Address);
            pstm.setObject(6,Cust_ID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
