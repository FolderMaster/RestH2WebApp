package com.foldermaster.resth2webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foldermaster.resth2webapp.model.Item;

/**
 * Интерфейс абстрактного репозитория базы данных товаров.
 * @param <T> Тип товара.
 */
public interface AbstractItemRepository<T extends Item> extends JpaRepository<T, Long> {}