package repository.Impl;

import db.DBConnection;
import repository.ReturnBookPageRepository;
import java.sql.*;

public class ReturnBookPageRepositoryImpl implements ReturnBookPageRepository {

    @Override
    public ResultSet getAllReturnBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbooks ");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }


    @Override
    public ResultSet searchRental(String Rental_ID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbooks WHERE Rental_ID=?");
            pstm.setString(1, Rental_ID);
            return pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




