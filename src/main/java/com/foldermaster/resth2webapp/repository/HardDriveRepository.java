package com.foldermaster.resth2webapp.repository;

import org.springframework.stereotype.Repository;

import com.foldermaster.resth2webapp.model.HardDrive;

/**
 * Интерфейс репозитория базы данных жёстких дисков.
 */
@Repository
public interface HardDriveRepository extends AbstractItemRepository<HardDrive> {}