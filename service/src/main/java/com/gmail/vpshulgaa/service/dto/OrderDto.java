package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;

import com.gmail.vpshulgaa.dao.entities.Item;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private long id;
    private LocalDateTime created;
    private Integer quantity;
    private ItemDto item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        return id == orderDto.id && (created != null ? created.equals(orderDto.created) : orderDto.created == null)
                && (quantity != null ? quantity.equals(orderDto.quantity) : orderDto.quantity == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
