package com.foldermaster.resth2webapp.model;

import jakarta.persistence.*;

/**
 * Класс настольного компьютера с уникальным идентификатором, номером серии, производителем, ценой, количеством и
 * форм-фактором.
 */
@Entity
@Table(name = "PERSONAL_COMPUTER")
public class Computer extends Item {
    /**
     * Форм-фактор.
     */
    @Column(name = "formFactor")
    private ComputerFormFactor formFactor;

    /**
     * Задаёт форм-фактор.
     * @param formFactor Форм-фактор.
     */
    public void setFormFactor(ComputerFormFactor formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * Возращает форм-фактор.
     * @return Форм-фактор.
     */
    public ComputerFormFactor getFormFactor() {
        return this.formFactor;
    }

    /**
     * Создаёт экземпляр класса.
     */
    public Computer() {}

    /**
     * Создаёт экземпляр класса по умолчанию.
     * @param batchNumber Номер серии.
     * @param producer Производитель.
     * @param cost Цена.
     * @param count Количество.
     * @param formFactor Форм-фактор.
     */
    public Computer(String batchNumber, String producer, int cost, int count, ComputerFormFactor formFactor) {
        super(batchNumber, producer, cost, count);
        setFormFactor(formFactor);
    }
}