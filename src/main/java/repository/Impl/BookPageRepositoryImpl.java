package repository.Impl;

import db.DBConnection;
import repository.BookPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPageRepositoryImpl implements BookPageRepository {
    @Override
    public ResultSet getAllbooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * From book");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public ResultSet getSearchedCustomers(int id) throws SQLException {
        String SQL = "SELECT * FROM book WHERE id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,id);
        ResultSet resultSet =pstm.executeQuery();
        return resultSet;
    }

    @Override
    public void addBook(int id, String isbm,String name, String author, String category, int quantity) {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO book( id, isbm, name, author, category,  quantity) VALUES(?,?,?,?,?,?)");
            pstm.setObject(1,id);
            pstm.setObject(2,isbm);
            pstm.setObject(3,name);
            pstm.setObject(4,author);
            pstm.setObject(5,category);
            pstm.setObject(6,quantity);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteBook(int id) {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm= connection.prepareStatement("DELETE FROM book WHERE id=?");
            pstm.setObject(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateBook(int id, String isbm, String name, String author, String category, int quantity) {

        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE book SET id=?,isbm=?, name=?, author=?, category=?, quantity=? WHERE id=?");
            pstm.setObject(1,id);
            pstm.setObject(2,isbm);
            pstm.setObject(3,name);
            pstm.setObject(4,author);
            pstm.setObject(5,category);
            pstm.setObject(6,quantity);
            pstm.setObject(7,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
