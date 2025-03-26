package net.barrufet.mc.master.exceptions.error;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

    // Getter methods
}
