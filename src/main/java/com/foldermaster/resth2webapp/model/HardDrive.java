package com.foldermaster.resth2webapp.model;

import jakarta.persistence.*;

/**
 * Класс жёсткого диска с уникальным идентификатором, номером серии, производителем, ценой, количеством и объёмом.
 */
@Entity
@Table(name = "HARD_DRIVE")
public class HardDrive extends Item {
    /**
     * Объём.
     */
    @Column(name = "capacity")
    private int capacity;

    /**
     * Задаёт объём.
     * @param capacity Объём.
     */
    public void setCapacity(int capacity) {
        if(capacity < 0)
        {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.capacity = capacity;
    }

    /**
     * Возращает объём.
     * @return Объём.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Создаёт экземпляр класса по умолчанию.
     */
    public HardDrive() {}

    /**
     * Создаёт экземпляр класса.
     * @param batchNumber Номер серии.
     * @param producer Производитель.
     * @param cost Цена.
     * @param count Количество.
     * @param capacity Объём.
     */
    public HardDrive(String batchNumber, String producer, int cost, int count, int capacity) {
        super(batchNumber, producer, cost, count);
        setCapacity(capacity);
    }
}