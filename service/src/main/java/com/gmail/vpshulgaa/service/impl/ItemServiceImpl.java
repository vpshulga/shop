package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.XmlItemsDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public ItemDto create(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            item.setDeleted(Boolean.FALSE);
            item.setUniqueNumber(ServiceUtils.generateUniqueId());
            itemDao.create(item);
            itemDto = itemDtoConverter.toDto(item);
        } catch (Exception e) {
            logger.error("Failed to save item", e);
        }
        return itemDto;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        try {
            itemDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete item", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional
    public void createFromXml(MultipartFile file) {
        try {
            if (file.getSize() != 0) {
                JAXBContext context = JAXBContext.newInstance(XmlItemsDto.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                File tmpFile = new File("tmp.xml");
                file.transferTo(tmpFile);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(tmpFile), "UTF-8"));
                XmlItemsDto xmlItemsDto = (XmlItemsDto) unmarshaller.unmarshal(bufferedReader);
                List<ItemDto> itemsDto = xmlItemsDto.getItems();

                for (ItemDto itemDto : itemsDto) {
                    Item item = itemConverter.toEntity(itemDto);
                    item.setDeleted(Boolean.FALSE);
                    item.setUniqueNumber(ServiceUtils.generateUniqueId());
                    itemDao.create(item);
                }
                tmpFile.delete();
            }
        } catch (Exception e) {
            logger.error("Failed to create items", e);
        }
    }
}
