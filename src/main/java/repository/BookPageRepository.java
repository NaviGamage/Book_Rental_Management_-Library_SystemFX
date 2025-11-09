package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookPageRepository {

    ResultSet getAllbooks() throws SQLException;

    ResultSet getSearchedCustomers(int id) throws SQLException;

    void addBook(int id, String isbm,String name,String author,String category,int quantity);

    void deleteBook(int id);

    void updateBook(int id,String isbm, String name,String author,String category,int quantity);

}
