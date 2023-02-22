package net.chrisbay.myplasticcirclesprovider.controller;

import net.chrisbay.myplasticcirclesprovider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcirclesprovider.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandlers {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error validationException (ValidationException e) {
        return new Error("Validation failed when creating or updating the resource.\n\nDetails: " + e.getMessage());
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error resourceDoesNotExistException (ResourceDoesNotExistException e) {
        return new Error("Invalid entry for type [" + e.getType() + "]. The given object (id=" + e.getId() + ") does not exist in the system");
    }

}
