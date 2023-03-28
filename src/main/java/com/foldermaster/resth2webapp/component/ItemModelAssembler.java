package com.foldermaster.resth2webapp.component;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.foldermaster.resth2webapp.controller.ItemController;
import com.foldermaster.resth2webapp.model.Item;

/**
 * Класс сборщика с основной ссылкой контейнера модели товара со ссылками.
 */
@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {
    /**
     * Основная ссылка.
     */
    private String mainLink;

    /**
     * Создаёт экземпляр класса по умолчанию.
     */
    public ItemModelAssembler() {
    }

    /**
     * Возращает основную ссылку.
     * @return Основная ссылка.
     */
    public String getMainLink() {
        return this.mainLink;
    }

    /**
     * Задаёт основную ссылку.
     * @param mainLink Основная ссылка.
     */
    public void setMainLink(String mainLink) {
        this.mainLink = mainLink;
    }

    /**
     * Осуществляет сборку контейнера модели товара со ссылками.
     * @param item Товар.
     * @return Контейнер модели товара со ссылками.
     */
    @Override
    public EntityModel<Item> toModel(Item item) {
        return EntityModel.of(item, linkTo(methodOn(ItemController.class).selectItemById(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).selectAllItems()).withRel(mainLink));
    }
}