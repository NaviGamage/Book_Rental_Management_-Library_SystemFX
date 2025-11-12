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
        PreparedStatement pstm = connection.prepareStatement("SELECT * From books");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public ResultSet getSearchBooks(int id) throws SQLException {
        String SQL = "SELECT * FROM books WHERE id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,id);
        ResultSet resultSet =pstm.executeQuery();
        return resultSet;
    }

    @Override
    public void addBooks(int id, String isbn,String name, String author, String category, int quantity) {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO books( id, isbn, name, author, category,  quantity) VALUES(?,?,?,?,?,?)");
            pstm.setObject(1,id);
            pstm.setObject(2,isbn);
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
    public void deleteBooks(int id) {
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm= connection.prepareStatement("DELETE FROM books WHERE id=?");
            pstm.setObject(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateBooks(int id, String isbn, String name, String author, String category, int quantity) {

        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE books SET id=?,isbn=?, name=?, author=?, category=?, quantity=? WHERE id=?");
            pstm.setObject(1,id);
            pstm.setObject(2,isbn);
            pstm.setObject(3,name);
            pstm.setObject(4,author);
            pstm.setObject(5,category);
            pstm.setObject(6,quantity);
            pstm.setObject(7,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateBooksqty(int quantity, int id) {

        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE books SET  quantity= quantity - ? WHERE id = ?");
            pstm.setObject(1,quantity);
            pstm.setObject(2,id);
            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}





