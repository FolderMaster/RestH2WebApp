package com.foldermaster.resth2webapp.repository;

import org.springframework.stereotype.Repository;

import com.foldermaster.resth2webapp.model.Computer;

/**
 * Интерфейс репозитория базы данных настольных компьютеров.
 */
@Repository
public interface ComputerRepository extends AbstractItemRepository<Computer> {}