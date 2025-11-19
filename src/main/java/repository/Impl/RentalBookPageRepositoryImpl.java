package repository.Impl;

import db.DBConnection;
import repository.RentalBookPageRepository;

import java.sql.*;

public class RentalBookPageRepositoryImpl implements RentalBookPageRepository {

    @Override
    public ResultSet getAllRentalBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbooks");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public boolean addRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO rentalbooks (Rental_ID, id, Cust_ID, Rental_Date, Due_Date, quantity) VALUES (?,?,?,?,?,?)");

            pstm.setInt(1, Rental_ID);
            pstm.setInt(2, id);
            pstm.setString(3, Cust_ID);
            pstm.setString(4, Rental_Date);
            pstm.setString(5, Due_Date);
            pstm.setInt(6, quantity);

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRental(int Rental_ID) {

        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM rentalbooks WHERE Rental_ID =?");
            pstm.setObject(1,Rental_ID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE rentalbooks SET Rental_ID=?,id=?,Cust_ID=?,Rental_Date=?,Due_Date=?,quantity=? WHERE Rental_ID=? ");
            pstm.setObject(1,Rental_ID);
            pstm.setObject(2,id);
            pstm.setObject(3,Cust_ID);
            pstm.setObject(4,Rental_Date);
            pstm.setObject(5,Due_Date);
            pstm.setObject(6,quantity);
            pstm.setObject(7,Rental_ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchRental(int Rental_ID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbooks WHERE Rental_ID=?");
            pstm.setObject(1, Rental_ID);
            ResultSet resultSet = pstm.executeQuery();
            return  resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean returnRental(int Rental_ID) {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM rentalbooks WHERE Rental_ID=?");
            pstm.setObject(1,Rental_ID);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
