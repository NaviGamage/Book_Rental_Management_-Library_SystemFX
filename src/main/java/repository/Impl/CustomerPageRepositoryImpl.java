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
    public void addCustomer(String custID, String name, String phone, String email, String address) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer(String custID, String name, String phone, String email, String address) VALUES(?,?,?,?,?)");
            pstm.setObject(1,custID);
            pstm.setObject(2,name);
            pstm.setObject(3,phone);
            pstm.setObject(4,email);
            pstm.setObject(5,address);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomer(String custID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE Cust_ID = ?");
            pstm.setObject(1,custID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCustomer(String custID, String name, String phone, String email, String address) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement("UPDATE customer SET Cust_ID=?, Name=?, Phone=?, Email=?, Address=? WHERE Cust_ID=?");
            pstm.setObject(1,custID);
            pstm.setObject(2,name);
            pstm.setObject(3,phone);
            pstm.setObject(4,email);
            pstm.setObject(5,address);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
