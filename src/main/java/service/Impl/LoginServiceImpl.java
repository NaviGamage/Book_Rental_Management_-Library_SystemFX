package service.Impl;

import repository.Impl.LoginRepositoryImpl;
import repository.LoginRepository;
import service.LoginService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    LoginRepository loginRepository = new LoginRepositoryImpl();

    @Override
    public String authenticateUser(String email, String password) throws SQLException {
        ResultSet resultSet = loginRepository.getUsers(email);

        if (resultSet.next()) {
            String dbEmail = resultSet.getString("email");
            String dbPassword = resultSet.getString("password_hash");
            String dbRole = resultSet.getString("role");

            if (email.equals(dbEmail) && password.equals(dbPassword)) {
                return dbRole;
            }
        }
        return null;
    }
}