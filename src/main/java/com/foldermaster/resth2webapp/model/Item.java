package com.foldermaster.resth2webapp.model;

import jakarta.persistence.*;

/**
 * Класс товара с уникальным идентификатором, номером серии, производителем, ценой и количеством.
 */
@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item
{
    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Номер серии.
     */
    @Column(name = "batchNumber")
    private String batchNumber;

    /**
     * Производитель.
     */
    @Column(name = "producer")
    private String producer;

    /**
     * Цена.
     */
    @Column(name = "cost")
    private int cost;

    /**
     * Количество.
     */
    @Column(name = "count")
    private int count;

    /**
     * Возращает уникальный идентификатор.
     * @return Уникальный идентификатор.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Задаёт уникальный идентификатор.
     * @param id Уникальный идентификатор.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Задаёт номер серии.
     * @param batchNumber Номер серии.
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * Возращает номер серии.
     * @return Номер серии.
     */
    public String getBatchNumber() {
        return this.batchNumber;
    }

    /**
     * Задаёт производителя.
     * @param producer Производитель.
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Возращает производителя.
     * @return Производитель.
     */
    public String getProducer() {
        return this.producer;
    }

    /**
     * Задаёт цену.
     * @param cost Цена.
     */
    public void setCost(int cost) {
        if(cost < 0)
        {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.cost = cost;
    }

    /**
     * Возращает цену.
     * @return Цена.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Задаёт количество.
     * @param cont Количество.
     */
    public void setCount(int cont) {
        if(count < 0)
        {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.count = cont;
    }

    /**
     * Возращает количество.
     * @return Количество.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Создаёт экземпляр класса по умолчанию.
     */
    public Item() {}

    /**
     * Создаёт экземпляр класса.
     * @param batchNumber Номер серии.
     * @param producer Производитель.
     * @param cost Цена.
     * @param count Количество.
     */
    public Item(String batchNumber, String producer, int cost, int count) {
        setBatchNumber(batchNumber);
        setProducer(producer);
        setCost(cost);
        setCount(count);
    }
}