package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentalBookPageRepository {

    ResultSet getAllRentalBooks()throws SQLException;

    boolean addRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity);

    void deleteRental(int Rental_ID);

    void updateRentalBooks(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity);

    ResultSet searchRental(int Rental_ID);

    boolean returnRental(int Rental_ID );
}
