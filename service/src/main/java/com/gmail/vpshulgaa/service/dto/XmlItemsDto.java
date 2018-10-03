package com.gmail.vpshulgaa.service.dto;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlItemsDto {

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemDto> items;

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
