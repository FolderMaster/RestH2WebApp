package com.foldermaster.resth2webapp.repository;

import org.springframework.stereotype.Repository;

import com.foldermaster.resth2webapp.model.Monitor;

/**
 * Интерфейс репозитория базы данных мониторов.
 */
@Repository
public interface MonitorRepository extends AbstractItemRepository<Monitor> {}