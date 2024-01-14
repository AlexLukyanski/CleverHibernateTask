package ru.clevertec.house.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(Class<?> type, Object field) {
        return new NotFoundException(type.getName() +
                " class with field "
                + field.getClass().getName()
                + " = " + field + " not found");
    }
}
