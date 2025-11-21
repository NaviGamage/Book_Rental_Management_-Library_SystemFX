package service.Impl;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentalBook;
import repository.Impl.ReturnBookPageRepositoryImpl;
import repository.ReturnBookPageRepository;
import service.BookPageService;
import service.HistoryPageService;
import service.RentalBookPageService;
import service.ReturnBookPageService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookPageServiceImpl implements ReturnBookPageService {

    ReturnBookPageRepository returnBookPageRepository=new ReturnBookPageRepositoryImpl();
    BookPageService bookPageService = new BookPageServiceImpl();
    ObservableList<RentalBook> rentalBooks = FXCollections.observableArrayList();
    RentalBookPageService rentalBookPageService= new RentalBookPageServiceImpl();
    HistoryPageService historyPageService = new HistoryPageServiceImpl();




    @Override
    public ObservableList<RentalBook> getAllReturnBooks() throws SQLException {
        ResultSet resultSet = returnBookPageRepository.getAllReturnBooks();
        while (resultSet.next()){
            rentalBooks.add(new RentalBook(
                    resultSet.getInt("Rental_ID"),
                    resultSet.getInt("id"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("quantity")
            ));
        }
        return rentalBooks;
    }

    @Override
    public ObservableList<RentalBook> searchRental(int Rental_ID) throws SQLException {
        ResultSet resultSet = returnBookPageRepository.searchRental(String.valueOf(Integer.parseInt(String.valueOf(Rental_ID))));
        while (resultSet.next()) {
            rentalBooks.add(new RentalBook(
                    resultSet.getInt("Rental_ID"),
                    resultSet.getInt("id"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("quantity")
            ));
        }return rentalBooks;
    }


    @Override
    public void setReturn(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity, String Return_Date, double fine) throws SQLException {

              Connection connection = DBConnection.getInstance().getConnection();
       try {
           connection.setAutoCommit(false);


        boolean isDeleted = rentalBookPageService.returnRental(Integer.parseInt(String.valueOf(Rental_ID)));
        System.out.println("Return OK: " + isDeleted);

        if (isDeleted) {

            boolean isAddedQty = bookPageService.addedBookQty(quantity, id);
            System.out.println("Added Book Qty: " + isAddedQty);

            if (isAddedQty) {
                boolean isaddHistory = historyPageService.addHistoy(Rental_ID, id, Cust_ID, Rental_Date, Due_Date, quantity, Return_Date, fine);
                System.out.println("added Table History:" + isaddHistory);
            }
        }

                 if(isDeleted){
                     connection.commit();
                  }

        } catch (SQLException e) {
            connection.rollback();
           throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
       }
    }
}
