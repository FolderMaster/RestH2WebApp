package com.foldermaster.resth2webapp.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foldermaster.resth2webapp.model.Item;

/**
 * Интерфейс репозитория базы данных товаров.
 */
@Primary
@Repository
public interface ItemRepository extends AbstractItemRepository<Item> {}