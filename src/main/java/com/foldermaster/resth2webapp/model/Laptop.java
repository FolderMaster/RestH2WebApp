package com.foldermaster.resth2webapp.model;

import jakarta.persistence.*;

/**
 * Класс ноутбука с уникальным идентификатором, номером серии, производителем, ценой, количеством и размером.
 */
@Entity
@Table(name = "LAPTOP")
public class Laptop extends Item {
    /**
     * Размер.
     */
    @Column(name = "size")
    private double size;

    /**
     * Задаёт размер.
     * @param size Размер.
     */
    public void setSize(double size) {
        if(size < 0)
        {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.size = size;
    }

    /**
     * Возращает размер.
     * @return Размер.
     */
    public double getSize() {
        return this.size;
    }

    /**
     * Создаёт экземпляр класса по умолчанию.
     */
    public Laptop() {}

    /**
     * Создаёт экземпляр класса.
     * @param batchNumber Номер серии.
     * @param producer Производитель.
     * @param cost Цена.
     * @param count Количество.
     * @param size Размер.
     */
    public Laptop(String batchNumber, String producer, int cost, int count, double size) {
        super(batchNumber, producer, cost, count);
        setSize(size);
    }
}