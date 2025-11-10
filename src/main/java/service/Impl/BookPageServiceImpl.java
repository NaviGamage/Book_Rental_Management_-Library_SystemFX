package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Books;
import repository.Impl.BookPageRepositoryImpl;
import service.BookPageService;

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
                    resultSet.getString("isbn"),
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
                    resultSet.getString("isbn"),
                    resultSet.getString("name"),
                    resultSet.getString("author"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantity")
            ));
        }



        return books;
    }

    @Override
    public void addBooks(int id, String isbn, String name, String author, String category, int quantity) {
        bookPageRepository.addBooks(id, isbn, name, author, category, quantity);
    }

    @Override
    public void deleteBooks(int id) {
        bookPageRepository.deleteBooks(id);

    }

    @Override
    public void updateBooks(int id, String isbn, String name, String author, String category, int quantity) {
        bookPageRepository.updateBooks(id, isbn, name, author, category, quantity);

    }
}
