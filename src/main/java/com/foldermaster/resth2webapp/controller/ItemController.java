package com.foldermaster.resth2webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.foldermaster.resth2webapp.model.*;
import com.foldermaster.resth2webapp.component.ItemModelAssembler;
import com.foldermaster.resth2webapp.repository.*;
import com.foldermaster.resth2webapp.exception.ItemNotFoundException;

/**
 * Rest-контроллер товаров с основной ссылкой, репозиториями и сборщиком контейнера модели товара со ссылками.
 */
@RestController
public class ItemController {
    /**
     * Основная ссылка.
     */
    private final String mainLink = "/items";

    /**
     * Репозиторий базы данных товаров.
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * Репозиторий базы данных жёстких дисков.
     */
    @Autowired
    private HardDriveRepository hardDriveRepository;

    /**
     * Репозиторий базы данных ноутбуков.
     */
    @Autowired
    private LaptopRepository laptopRepository;

    /**
     * Репозиторий базы данных мониторов.
     */
    @Autowired
    private MonitorRepository monitorRepository;

    /**
     * Репозиторий базы данных настольных компьютеров.
     */
    @Autowired
    private ComputerRepository computerRepository;

    /**
     * Сборщик контейнера модели товара со ссылками.
     */
    private final ItemModelAssembler assembler;


    /**
     * Создаёт экземпляр класса.
     * @param assembler Сборщик контейнера модели товара со ссылками.
     */
    public ItemController(ItemModelAssembler assembler) {
        assembler.setMainLink(mainLink);
        this.assembler = assembler;
    }

    /**
     * Добавляет товар.
     * @param item Товар.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PostMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        EntityModel<Item> entityModel = assembler.toModel(savedItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Добавляет жёсткие диски.
     * @param hardDrive Жёсткий диск.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PostMapping(value = "/hard-drives", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addHardDrive(@RequestBody HardDrive hardDrive) {
        HardDrive savedHardDrive = hardDriveRepository.save(hardDrive);
        EntityModel<Item> entityModel = assembler.toModel(savedHardDrive);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Добавляет ноутбук.
     * @param laptop Ноутбук.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PostMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLaptop(@RequestBody Laptop laptop) {
        Laptop savedLaptop = laptopRepository.save(laptop);
        EntityModel<Item> entityModel = assembler.toModel(savedLaptop);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Добавляет монитор.
     * @param monitor Монитор.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PostMapping(value = "/monitors", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMonitor(@RequestBody Monitor monitor) {
        Monitor savedMonitor = monitorRepository.save(monitor);
        EntityModel<Item> entityModel = assembler.toModel(savedMonitor);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Добавляет настольный компьютер.
     * @param computer Настольный компьютер.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PostMapping(value = "/computers", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMonitor(@RequestBody Computer computer) {
        Computer savedComputer = computerRepository.save(computer);
        EntityModel<Item> entityModel = assembler.toModel(savedComputer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Удаляет товар.
     * @param id Уникальный идентификатор.
     * @return Ответ HTTP.
     */
    @DeleteMapping(value = "/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeItem(@PathVariable long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Обновляет товар.
     * @param newItem Новый товар.
     * @param id Уникальный идентификатор старого товара.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PutMapping(value = "/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestBody Item newItem, @PathVariable long id) {
        Item updatedItem = itemRepository.findById(id).map(item -> {
            item.setBatchNumber(newItem.getBatchNumber());
            item.setProducer(newItem.getProducer());
            item.setCost(newItem.getCost());
            item.setCount(newItem.getCount());
            return itemRepository.save(item);
        }).orElseGet(() -> {
            newItem.setId(id);
            return itemRepository.save(newItem);
        });
        EntityModel<Item> entityModel = assembler.toModel(updatedItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Обновляет жёсткий диск.
     * @param newHardDrive Новый жёсткий диск.
     * @param id Уникальный идентификатор старого жёсткого диска.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PutMapping(value = "/hard-drives/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateHardDrive(@RequestBody HardDrive newHardDrive, @PathVariable long id) {
        HardDrive updatedHardDrive = hardDriveRepository.findById(id).map(hardDrive -> {
            hardDrive.setBatchNumber(newHardDrive.getBatchNumber());
            hardDrive.setProducer(newHardDrive.getProducer());
            hardDrive.setCost(newHardDrive.getCost());
            hardDrive.setCount(newHardDrive.getCount());
            hardDrive.setCapacity(newHardDrive.getCapacity());
            return hardDriveRepository.save(hardDrive);
        }).orElseGet(() -> {
            newHardDrive.setId(id);
            return hardDriveRepository.save(newHardDrive);
        });
        EntityModel<Item> entityModel = assembler.toModel(updatedHardDrive);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Обновляет ноутбук.
     * @param newLaptop Новый ноутбук.
     * @param id Уникальный идентификатор старого ноутбука.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PutMapping(value = "/laptops/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLaptop(@RequestBody Laptop newLaptop, @PathVariable long id) {
        Laptop updatedLaptop = laptopRepository.findById(id).map(laptop -> {
            laptop.setBatchNumber(newLaptop.getBatchNumber());
            laptop.setProducer(newLaptop.getProducer());
            laptop.setCost(newLaptop.getCost());
            laptop.setCount(newLaptop.getCount());
            laptop.setSize(newLaptop.getSize());
            return laptopRepository.save(laptop);
        }).orElseGet(() -> {
            newLaptop.setId(id);
            return laptopRepository.save(newLaptop);
        });
        EntityModel<Item> entityModel = assembler.toModel(updatedLaptop);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Обновляет монитор.
     * @param newMonitor Новый монитор.
     * @param id Уникальный идентификатор старого монитора.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PutMapping(value = "/monitors/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMonitor(@RequestBody Monitor newMonitor, @PathVariable long id) {
        Monitor updatedMonitor = monitorRepository.findById(id).map(monitor -> {
            monitor.setBatchNumber(newMonitor.getBatchNumber());
            monitor.setProducer(newMonitor.getProducer());
            monitor.setCost(newMonitor.getCost());
            monitor.setCount(newMonitor.getCount());
            monitor.setDiagonal(newMonitor.getDiagonal());
            return monitorRepository.save(monitor);
        }).orElseGet(() -> {
            newMonitor.setId(id);
            return monitorRepository.save(newMonitor);
        });
        EntityModel<Item> entityModel = assembler.toModel(updatedMonitor);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Обновляет настольный компьютер.
     * @param newComputer Новый настольный компьютер.
     * @param id Уникальный идентификатор старого настольного компьютера.
     * @return Ответ HTTP со ссылками и моделью товара со ссылками.
     */
    @PutMapping(value = "/computers/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePersonalComputer(@RequestBody Computer newComputer, @PathVariable long id) {
        Computer updatedComputer = computerRepository.findById(id).map(computer -> {
            computer.setBatchNumber(newComputer.getBatchNumber());
            computer.setProducer(newComputer.getProducer());
            computer.setCost(newComputer.getCost());
            computer.setCount(newComputer.getCount());
            computer.setFormFactor(newComputer.getFormFactor());
            return computerRepository.save(computer);
        }).orElseGet(() -> {
            newComputer.setId(id);
            return computerRepository.save(newComputer);
        });
        EntityModel<Item> entityModel = assembler.toModel(updatedComputer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Возращает товар.
     * @param id Уникальный идентификатор.
     * @retur Контейнер модели товара со ссылками.
     */
    @GetMapping(value = "/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Item> selectItemById(@PathVariable long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

    /**
     * Возращает товары.
     * @return Контейнер моделей товаров со ссылками.
     */
    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Item>> selectAllItems() {
        List<EntityModel<Item>> items = itemRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(ItemController.class).selectAllItems()).withSelfRel());
    }

    /**
     * Возращает жёсткие диски.
     * @return Контейнер моделей товаров со ссылками.
     */
    @GetMapping(value = "/hard-drives", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Item>> selectHardDrives() {
        List<EntityModel<Item>> harddrives = hardDriveRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(harddrives, linkTo(methodOn(ItemController.class).selectHardDrives()).
                        withSelfRel(), linkTo(methodOn(ItemController.class).selectAllItems()).withRel(mainLink));
    }

    /**
     * Возращает ноутбуки.
     * @return Контейнер моделей товаров со ссылками.
     */
    @GetMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Item>> selectLaptops() {
        List<EntityModel<Item>> laptops = laptopRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(laptops, linkTo(methodOn(ItemController.class).selectLaptops()).withSelfRel(),
                linkTo(methodOn(ItemController.class).selectAllItems()).withRel(mainLink));
    }

    /**
     * Возращает мониторы.
     * @return Контейнер моделей товаров со ссылками.
     */
    @GetMapping(value = "/monitors", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Item>> selectMonitors() {
        List<EntityModel<Item>> monitors = monitorRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(monitors, linkTo(methodOn(ItemController.class).selectMonitors()).withSelfRel(),
                linkTo(methodOn(ItemController.class).selectAllItems()).withRel(mainLink));
    }

    /**
     * Возращает настольные компьютеры.
     * @return Контейнер моделей товаров со ссылками.
     */
    @GetMapping(value = "/computers", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Item>> selectComputers() {
        List<EntityModel<Item>> computers = computerRepository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(computers, linkTo(methodOn(ItemController.class).selectComputers()).
                        withSelfRel(), linkTo(methodOn(ItemController.class).selectAllItems()).withRel(mainLink));
    }
}