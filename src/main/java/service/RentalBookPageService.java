package service;

import javafx.collections.ObservableList;
import model.dto.RentalBook;

import java.sql.SQLException;

public interface RentalBookPageService {

    ObservableList<RentalBook> getAllRentalBooks()throws  SQLException;

    ObservableList<RentalBook> searchRental(int Rental_ID) throws  SQLException;

    void addRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity ) throws SQLException;

    void deleteRental (int Rental_ID);

    void updateRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity );

    boolean returnRental(int Rental_ID);
}
