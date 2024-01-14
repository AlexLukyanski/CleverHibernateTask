package ru.clevertec.house.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.house.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionHandle {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public String handleUnexpectedException(NotFoundException e) {
        Logger log = LogManager.getRootLogger();
        log.log(Level.ERROR, "Not found", e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleUnexpectedException(Exception e) {
        Logger log = LogManager.getRootLogger();
        log.log(Level.ERROR, "Something's wrong", e);
        return e.getMessage();
    }
}
