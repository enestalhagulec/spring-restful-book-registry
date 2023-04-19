package net.etg.bookregistry.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @NotEmpty(message = "Book must have a name")
    private String name;

    @NotEmpty(message = "Book must have an author")
    private String author;

    @NotNull(message = "Book must have a price")
    private BigDecimal price;

}
