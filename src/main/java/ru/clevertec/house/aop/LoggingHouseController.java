package ru.clevertec.house.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingHouseController {

    @AfterThrowing(pointcut = "execution" +
            "(public HouseResponse ru.clevertec.house.controller.HouseController.findHouseById(..))", throwing = "e")
    public void loggingForFindHouseById(Throwable e) {

        log.error("Something's wrong with request findHouseById", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<HouseResponse> ru.clevertec.house.controller.HouseController.findAll(..))", throwing = "e")
    public void loggingForFindAll(Throwable e) {

        log.error("Something's wrong with request findAll", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public UUID ru.clevertec.house.controller.HouseController.create(..))", throwing = "e")
    public void loggingForCreate(Throwable e) {

        log.error("Something's wrong with request create", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public UUID ru.clevertec.house.controller.HouseController.update(..))", throwing = "e")
    public void loggingForUpdate(Throwable e) {

        log.error("Something's wrong with request update", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public void ru.clevertec.house.controller.HouseController.delete(..))", throwing = "e")
    public void loggingForDelete(Throwable e) {

        log.error("Something's wrong with request delete", e);
    }

    @AfterThrowing(pointcut = "execution" +
            "(public List<PersonResponse> ru.clevertec.house.controller.HouseController.findAllResidents(..))", throwing = "e")
    public void loggingForFindAllResidents(Throwable e) {

        log.error("Something's wrong with request findAllResidents", e);
    }
}