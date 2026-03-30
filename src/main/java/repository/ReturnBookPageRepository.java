package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReturnBookPageRepository {

    ResultSet getAllReturnBooks()throws SQLException;

    ResultSet searchRental(String Rental_ID );



}
