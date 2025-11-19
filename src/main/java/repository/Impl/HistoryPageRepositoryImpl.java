package repository.Impl;

import db.DBConnection;
import repository.HistoryPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryPageRepositoryImpl implements HistoryPageRepository {
    @Override
    public ResultSet getAllHistory() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM history");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public boolean addHistory(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity, String Return_Date, double fine) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO history (Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity,Return_Date,fine) Values (?, ?, ?, ?, ?,?,?,?)");

            pstm.setObject(1, Rental_ID);
            pstm.setObject(2, id);
            pstm.setObject(3, Cust_ID);
            pstm.setObject(4, Rental_Date);
            pstm.setObject(5, Due_Date);
            pstm.setObject(6, quantity);
            pstm.setObject(7, Return_Date);
            pstm.setObject(8, fine);

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
