package ru.clevertec.house.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingPersonController {

    @AfterThrowing(pointcut = "execution" +
            "(public PersonResponse ru.clevertec.house.controller.PersonController.findPersonByID(..))", throwing = "e")
    public void loggingForFindPersonByID(Throwable e) {

        log.error("Something's wrong with request findPersonByID", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<PersonResponse> ru.clevertec.house.controller.PersonController.findAll(..))", throwing = "e")
    public void loggingForFindAll(Throwable e) {

        log.error("Something's wrong with request findAll", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public UUID ru.clevertec.house.controller.PersonController.create(..))", throwing = "e")
    public void loggingForCreate(Throwable e) {

        log.error("Something's wrong with request create", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public UUID ru.clevertec.house.controller.PersonController.update(..))", throwing = "e")
    public void loggingForUpdate(Throwable e) {

        log.error("Something's wrong with request update", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public void ru.clevertec.house.controller.PersonController.delete(..))", throwing = "e")
    public void loggingForDelete(Throwable e) {

        log.error("Something's wrong with request delete", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<HouseResponse> ru.clevertec.house.controller.PersonController.getOwnedHouses(..))", throwing = "e")
    public void loggingForGetOwnedHouses(Throwable e) {

        log.error("Something's wrong with request getOwnedHouses", e);
    }
}