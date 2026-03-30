package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    private String Cust_ID;
    private String Name;
    private String Phone;
    private String Email;
    private String Address;
}
