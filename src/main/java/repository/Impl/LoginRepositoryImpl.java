package repository.Impl;

import db.DBConnection;
import repository.LoginRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl implements LoginRepository {
    @Override
    public ResultSet getUsers(String email) throws SQLException {
        String SQL = "SELECT * FROM users WHERE email = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setString(1, email);
        return pstm.executeQuery();
    }
}

