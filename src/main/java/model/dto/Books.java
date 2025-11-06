package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    private int id;
    private String isbn;
    private String name;
    private String author;
    private String category;
    private int quantity;
}
