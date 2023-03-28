package com.foldermaster.resth2webapp.exception;

/**
 * Класс ошибки ненахождения товара.
 */
public class ItemNotFoundException extends RuntimeException {
    /**
     * Создаёт экземпляр класса по умолчанию.
     * @param id Уникальный идентификатор.
     */
    public ItemNotFoundException(long id) {
        super("Not found item by id " + id);
    }
}