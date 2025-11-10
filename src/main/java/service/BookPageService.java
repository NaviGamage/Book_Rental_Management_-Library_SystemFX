package service;

import javafx.collections.ObservableList;
import model.dto.Books;

import java.sql.SQLException;

public interface BookPageService {

    ObservableList<Books>getAllBooks()throws SQLException;

    ObservableList<Books>getSearchBooks(int id)throws SQLException;

    void addBook(int id,String isbm,String name,String author,String category,int quantity );

    void deleteBook(int id);

    void updateBook(int id,String isbm,String name,String author,String category,int quantity);
}

