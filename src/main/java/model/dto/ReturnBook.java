package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReturnBook  {

    private int Rental_ID;
    private int id;
    private String Cust_ID;
    private Date Rental_Date;
    private Date Due_Date;
    private  int quantity;
    private Date Return_Date;
    private double fine;


}
