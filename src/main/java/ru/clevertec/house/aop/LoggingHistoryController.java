package ru.clevertec.house.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingHistoryController {

    @AfterThrowing(pointcut = "execution" +
            "(public List<PersonResponse> ru.clevertec.house.controller.HistoryController.getAllPersonsLivedInHouse(..))", throwing = "e")
    public void loggingForGetAllPersonsLivedInHouse(Throwable e) {

        log.error("Something's wrong with request get all Persons lived In House", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<PersonResponse> ru.clevertec.house.controller.HistoryController.getAllPersonsOwnedHouse(..))", throwing = "e")
    public void loggingForGetAllPersonsOwnedHouse(Throwable e) {

        log.error("Something's wrong with request get all Persons Owned House", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<HouseResponse> ru.clevertec.house.controller.HistoryController.getAllHousesWherePersonLived(..))", throwing = "e")
    public void loggingForGetAllHousesWherePersonLived(Throwable e) {

        log.error("Something's wrong with request get all Houses where Person lived", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<HouseResponse> ru.clevertec.house.controller.HistoryController.getAllHousesWhichPersonOwned(..))", throwing = "e")
    public void loggingForGetAllHousesWhichPersonOwned(Throwable e) {

        log.error("Something's wrong with request get all Houses which Person owned", e);
    }
}
