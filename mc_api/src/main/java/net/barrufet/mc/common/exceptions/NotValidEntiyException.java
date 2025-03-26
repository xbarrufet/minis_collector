package net.barrufet.mc.common.exceptions;

import net.barrufet.mc.master.exceptions.FactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotValidEntiyException {
    @ExceptionHandler(NotValidEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String notValidEnityHandler(NotValidEntityException ex) {
        return ex.getMessage();
    }
}
