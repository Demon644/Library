package ua.logos.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookDTO {

    Long id;

    @NotNull(message = "Field 'Name' can't be NULL")
    @Size(min = 2, max = 200, message = "'NAME' should be between 2 and 200 characters")
    private String name;

    private AuthorDTO author;

    @NotNull(message = "Field 'Category' can't be NULL")
    private CategoryDTO category;

    @NotNull(message = "Field 'Image' can't be NULL")
    private String image;

    private String date;

    @NotNull(message = "Field 'Quantity' can't be NULL")
    private int quantity;

    @NotNull(message = "Field 'Price' can't be NULL")
    private double price;

    private String short_info;

    private String language;


}
