package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

    ItemDto findOne(final Long id);

    ItemDto create(final ItemDto dto);

    ItemDto update(final ItemDto dto);

    ItemDto delete(final ItemDto dto);

    void deleteById(final Long id);

    Long countOfItems();

    List<ItemDto> findItemsByPage(Long page, int maxResults);

    void createFromXml(MultipartFile file);


}
