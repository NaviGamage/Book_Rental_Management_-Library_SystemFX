package service.Impl;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentalBook;
import repository.Impl.RentalBookPageRepositoryImpl;
import repository.RentalBookPageRepository;
import service.BookPageService;
import service.RentalBookPageService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalBookPageServiceImpl implements RentalBookPageService {

    ObservableList<RentalBook>rentalBooks= FXCollections.observableArrayList();
    RentalBookPageRepository rentalBookPageRepository = new RentalBookPageRepositoryImpl();
    BookPageService bookPageService = new BookPageServiceImpl();



    @Override
    public ObservableList<RentalBook> getAllRentalBooks() throws SQLException {
        ResultSet resultSet = rentalBookPageRepository.getAllRentalBooks();

        while(resultSet.next()){
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
        ResultSet resultSet = rentalBookPageRepository.searchRental(Rental_ID);
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
    public void addRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isAdded = rentalBookPageRepository.addRentalBooks(Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity);
            System.out.println(("Add Rental Book:"+isAdded));

            if(isAdded){
                boolean isUpdated = bookPageService.updateBooksqty(quantity,id);
                System.out.println("Update Book quantity:"+isUpdated);

                if (isUpdated) {
                    connection.commit();
                }

                if (isUpdated) {
                }

            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public void deleteRental(int Rental_ID) {
        rentalBookPageRepository.deleteRental(Rental_ID);
    }

    @Override
    public void updateRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity) {
        rentalBookPageRepository.updateRentalBooks(Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity);

    }

    @Override
    public boolean returnRental(int Rental_ID) {
            boolean isDeleted = rentalBookPageRepository.returnRental(Integer.parseInt(String.valueOf(Rental_ID)));
            System.out.println("Return OK: " + isDeleted);
            return isDeleted;
        }
}

