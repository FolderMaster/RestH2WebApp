package com.foldermaster.resth2webapp.model;

import jakarta.persistence.*;

import java.util.regex.*;

/**
 * Класс монитора с уникальным идентификатором, номером серии, производителем, ценой, количеством и диагональю.
 */
@Entity
@Table(name = "MONITOR")
public class Monitor extends Item {
    /**
     * Диагональ.
     */
    @Column(name = "diagonal")
    private String diagonal;

    /**
     * Задаёт диагональ.
     * @param diagonal Диагональ.
     */
    public void setDiagonal(String diagonal) {
        Pattern pattern = Pattern.compile("^\\d+\\D\\d+$");
        Matcher matcher = pattern.matcher(diagonal);
        if(!matcher.matches())
        {
            throw new IllegalArgumentException("Diagonal must look like \"1920x1080\"!");
        }
        this.diagonal = diagonal;
    }

    /**
     * Возращает диагональ.
     * @return Диагональ.
     */
    public String getDiagonal() {
        return this.diagonal;
    }

    /**
     * Создаёт экземпляр класса по умолчанию.
     */
    public Monitor() {}

    /**
     * Создаёт экземпляр класса.
     * @param batchNumber Номер серии.
     * @param producer Производитель.
     * @param cost Цена.
     * @param count Количество.
     * @param diagonal Диагональ.
     */
    public Monitor(String batchNumber, String producer, int cost, int count, String diagonal) {
        super(batchNumber, producer, cost, count);
        setDiagonal(diagonal);
    }
}