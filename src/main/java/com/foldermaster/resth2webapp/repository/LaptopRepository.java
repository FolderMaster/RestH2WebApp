package com.foldermaster.resth2webapp.repository;

import org.springframework.stereotype.Repository;

import com.foldermaster.resth2webapp.model.Laptop;

/**
 * Интерфейс репозитория базы данных ноутбуков.
 */
@Repository
public interface LaptopRepository extends AbstractItemRepository<Laptop> {}