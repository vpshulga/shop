package com.gmail.vpshulgaa.service.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDto itemDto = (ItemDto) o;

        return id == itemDto.id && (name != null ? name.equals(itemDto.name) : itemDto.name == null)
                && (description != null ? description.equals(itemDto.description) : itemDto.description == null)
                && (uniqueNumber != null ? uniqueNumber.equals(itemDto.uniqueNumber) : itemDto.uniqueNumber == null)
                && (price != null ? price.equals(itemDto.price) : itemDto.price == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (uniqueNumber != null ? uniqueNumber.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
