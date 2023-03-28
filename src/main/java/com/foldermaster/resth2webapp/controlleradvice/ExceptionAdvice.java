package com.foldermaster.resth2webapp.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс обработки ошибки.
 */
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * Обрабатывает ошибку.
     * @param ex Ошибка.
     * @return Сообщение ошибки.
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String exceptionHandler(Exception ex) {
        return ex.getMessage();
    }
}