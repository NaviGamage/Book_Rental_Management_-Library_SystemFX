package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.History;
import repository.HistoryPageRepository;
import repository.Impl.HistoryPageRepositoryImpl;
import service.HistoryPageService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryPageServiceImpl implements HistoryPageService {

    ObservableList<History> history= FXCollections.observableArrayList();
    HistoryPageRepository historyPageRepository = new HistoryPageRepositoryImpl();

    @Override
    public ObservableList<History> getAllHistory() throws SQLException {
        ResultSet resultSet = historyPageRepository.getAllHistory();
        while (resultSet.next()) {
            history.add(new History(
                    resultSet.getInt("Rental_ID"),
                    resultSet.getInt("id"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("quantity"),
                    resultSet.getDate("Return_Date"),
                    resultSet.getDouble("fine")
            ));
        }

        return history;
    }

    @Override
    public boolean addHistoy(int Rental_ID, int id, String Cust_ID, String Rental_Date, String Due_Date, int quantity, String Return_Date, double fine) {
        boolean addHistoy = historyPageRepository.addHistory(Rental_ID,id,Cust_ID,Rental_Date,Due_Date,quantity,Return_Date,fine);
        return addHistoy;
    }
}
