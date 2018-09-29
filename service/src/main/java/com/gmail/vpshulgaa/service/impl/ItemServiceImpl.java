package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.impl.ItemDaoImpl;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.ItemDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.ItemConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private final ItemDao itemDao;
    private final Converter<ItemDto, Item> itemConverter;
    private final DtoConverter<ItemDto, Item> itemDtoConverter;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao,
                           @Qualifier("itemConverter") Converter<ItemDto, Item> itemConverter,
                           @Qualifier("itemDtoConverter") DtoConverter<ItemDto, Item> itemDtoConverter) {
        this.itemDao = itemDao;
        this.itemConverter = itemConverter;
        this.itemDtoConverter = itemDtoConverter;
    }

    @Override
    public ItemDto findOne(Long id) {
        ItemDto itemDto = null;
        try {
            Item item = itemDao.findOne(id);
            itemDto = itemDtoConverter.toDto(item);
        } catch (Exception e) {
            logger.error("Failed to get item", e);
        }
        return itemDto;
    }

    @Override
    public List<ItemDto> findAll() {
        return null;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            itemDao.create(item);
            itemDto = itemDtoConverter.toDto(item);
        } catch (Exception e) {
            logger.error("Failed to save item", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            itemDao.update(item);
            itemDto = itemDtoConverter.toDto(item);
        } catch (Exception e) {
            logger.error("Failed to update item", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto delete(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            itemDao.delete(item);
            itemDto = itemDtoConverter.toDto(item);
        } catch (Exception e) {
            logger.error("Failed to delete item", e);
        }
        return itemDto;
    }

    @Override
    public void deleteById(Long id) {
        try {
            itemDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete item", e);
        }
    }

    @Override
    public List<ItemDto> findItemsInPriceDiapason(BigDecimal start, BigDecimal finish) {
        List<ItemDto> itemsDto = new ArrayList<>();
        List<Item> items;
        try {
            items = itemDao.findItemsInPriceDiapason(start, finish);
            for (Item item : items) {
                itemsDto.add(itemDtoConverter.toDto(item));
            }
        } catch (Exception e) {
            logger.error("Failed to find items", e);
        }
        return itemsDto;
    }

    @Override
    public List<ItemDto> findItemsByDiscount(BigDecimal discount) {
        List<ItemDto> itemsDto = new ArrayList<>();
        List<Item> items;
        try {
            items = itemDao.findItemsByDiscount(discount);
            for (Item item : items) {
                itemsDto.add(itemDtoConverter.toDto(item));
            }
        } catch (Exception e) {
            logger.error("Failed to find items", e);
        }
        return itemsDto;
    }

    @Override
    public Long countItemsInDiapason(BigDecimal start, BigDecimal finish) {
        Long count = 0L;
        try {
            count = itemDao.countItemsInDiapason(start, finish);
        } catch (Exception e) {
            logger.error("Failed to find items", e);
        }
        return count;
    }

    @Override
    public Long countOfItems() {
        Long count = 0L;
        try {
            count = itemDao.countOfItems();
        } catch (Exception e) {
            logger.error("Failed to find items", e);
        }
        return count;
    }

    @Override
    public List<ItemDto> findItemsByPage(Long page, int maxResults) {
        List<ItemDto> itemsDto = new ArrayList<>();
        List<Item> items;
        try {
            items = itemDao.findItemsByPage(page, maxResults);
            for (Item item : items) {
                itemsDto.add(itemDtoConverter.toDto(item));
            }
        } catch (Exception e) {
            logger.error("Failed to find items", e);
        }
        return itemsDto;
    }
}
