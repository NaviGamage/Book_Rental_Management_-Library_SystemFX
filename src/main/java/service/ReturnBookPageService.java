package service;

import javafx.collections.ObservableList;
import model.dto.RentalBook;
import model.dto.ReturnBook;

import java.sql.SQLException;

public interface ReturnBookPageService {

    ObservableList<RentalBook>getAllReturnBooks()throws SQLException;

    ObservableList<RentalBook>searchRental(int Rental_ID ) throws  SQLException;

    void setReturn(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity , String Return_Date, double fine) throws SQLException;


}
