package service;

import javafx.collections.ObservableList;
import model.dto.Books;

import java.sql.SQLException;

public interface BookPageService {

    ObservableList<Books>getAllBooks()throws SQLException;

    ObservableList<Books>getSearchBooks(int id)throws SQLException;

    void addBooks(int id,String isbm,String name,String author,String category,int quantity );

    void deleteBooks(int id);

    boolean updateBooks(int id, String isbm, String name, String author, String category, int quantity);

    boolean updateBooksqty(int quantity, int id);
}

