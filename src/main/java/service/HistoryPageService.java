package service;

import javafx.collections.ObservableList;
import model.dto.History;

import java.sql.SQLException;

public interface HistoryPageService {

    ObservableList<History>getAllHistory()throws SQLException;

    boolean addHistoy(int Rental_ID,int id,String Cust_ID,String Rental_Date,String Due_Date,int quantity,String Return_Date,double fine);
}
