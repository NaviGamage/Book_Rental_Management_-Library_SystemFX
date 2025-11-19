package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HistoryPageRepository {

    ResultSet getAllHistory() throws SQLException;

    boolean addHistory(int Rental_ID,int id,String Cust_ID,String Rental_Date,String Due_Date,int quantity,String Return_Date,double fine);

}
