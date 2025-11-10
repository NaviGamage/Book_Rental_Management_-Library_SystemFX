package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Books;
import repository.BookPageRepository;
import repository.CustomerPageRepository;
import repository.Impl.BookPageRepositoryImpl;
import repository.Impl.CustomerPageRepositoryImpl;
import service.BookPageService;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPageServiceImpl implements BookPageService {

    ObservableList<Books> books = FXCollections.observableArrayList();
    BookPageRepositoryImpl bookPageRepository = new BookPageRepositoryImpl();

    @Override
    public ObservableList<Books> getAllBooks() throws SQLException {
        ResultSet resultSet = bookPageRepository.getAllbooks();
        while (resultSet.next()){
            books.add(new Books(
                    resultSet.getInt("id"),
                    resultSet.getString("isbm"),
                    resultSet.getString("name"),
                    resultSet.getString("author"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantity")
            ));
        }
        return books;
    }

    @Override
    public ObservableList<Books> getSearchBooks(int id) throws SQLException {
        ResultSet resultSet = bookPageRepository.getSearchBooks(id);
        while (resultSet.next()){
            books.add(new Books(
                    resultSet.getInt("id"),
                    resultSet.getString("isbm"),
                    resultSet.getString("name"),
                    resultSet.getString("author"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantity")
            ));
        }



        return books;
    }

    @Override
    public void addBook(int id, String isbm, String name, String author, String category, int quantity) {
        bookPageRepository.addBook(id, isbm, name, author, category, quantity);
    }

    @Override
    public void deleteBook(int id) {
        bookPageRepository.deleteBook(id);

    }

    @Override
    public void updateBook(int id, String isbm, String name, String author, String category, int quantity) {
        bookPageRepository.updateBook(id, isbm, name, author, category, quantity);

    }
}
