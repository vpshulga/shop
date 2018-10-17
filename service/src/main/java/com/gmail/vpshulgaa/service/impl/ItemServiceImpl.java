package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ItemDao;
import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.XmlItemsDto;
import com.gmail.vpshulgaa.service.exception.CustomUnmarshalException;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import com.gmail.vpshulgaa.service.util.UIDGeneratorUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
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
    public ItemServiceImpl(
            ItemDao itemDao,
            @Qualifier("itemConverter") Converter<ItemDto, Item> itemConverter,
            @Qualifier("itemDtoConverter") DtoConverter<ItemDto, Item> itemDtoConverter) {
        this.itemDao = itemDao;
        this.itemConverter = itemConverter;
        this.itemDtoConverter = itemDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto findOne(Long id) {
        Item item = itemDao.findOne(id);
        if (item != null) {
            return itemDtoConverter.toDto(item);
        } else {
            throw new EntityNotFoundException(Item.class, id);
        }
    }

    @Override
    @Transactional
    public ItemDto create(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            item.setDeleted(Boolean.FALSE);
            item.setUniqueNumber(UIDGeneratorUtils.generateUniqueId());
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
        if (itemDao.findOne(id) != null) {
            itemDao.deleteById(id);
        } else {
            throw new EntityNotFoundException(Item.class, id);
        }
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
        File tmpFile = new File("tmp.xml");
        try {
            file.transferTo(tmpFile);
        } catch (IOException e) {
            logger.error("Failed to find file", e);
        }
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(tmpFile), "UTF-8"))) {
            if (file.getSize() != 0) {
                JAXBContext context = JAXBContext.newInstance(XmlItemsDto.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                XmlItemsDto xmlItemsDto = (XmlItemsDto) unmarshaller.unmarshal(bufferedReader);
                List<ItemDto> itemsDto = xmlItemsDto.getItems();

                for (ItemDto itemDto : itemsDto) {
                    Item item = itemConverter.toEntity(itemDto);
                    item.setDeleted(Boolean.FALSE);
                    item.setUniqueNumber(UIDGeneratorUtils.generateUniqueId());
                    itemDao.create(item);
                }
            }
        } catch (UnmarshalException e) {
            throw new CustomUnmarshalException("Invalid file format, choose xml file with correct schema");
        } catch (Exception e) {
            logger.error("Failed to upload items", e);
        }
    }
}
