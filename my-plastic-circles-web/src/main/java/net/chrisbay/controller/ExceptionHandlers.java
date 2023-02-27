package net.chrisbay.controller;

import net.chrisbay.service.exception.RequestException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(RequestException.class)
    public String handleClientError (RequestException e, Model model) {
        model.addAttribute("errorMsg", e.getResponseCode() + " Error: " + e.getMessage());
        return "error";
    }
}
