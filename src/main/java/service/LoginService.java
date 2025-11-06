package service;

import java.sql.SQLException;

public interface LoginService {

    String authenticateUser(String email, String password) throws SQLException;
}
