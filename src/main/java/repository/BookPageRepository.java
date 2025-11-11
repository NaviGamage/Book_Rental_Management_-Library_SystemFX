package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookPageRepository {

    ResultSet getAllbooks() throws SQLException;

    ResultSet getSearchBooks(int id) throws SQLException;

    void addBooks(int id, String isbn,String name,String author,String category,int quantity);

    void deleteBooks(int id);

    void updateBooks(int id, String isbn, String name, String author, String category, int quantity);

    boolean updateBooksqty(int quantity, int id);
}
