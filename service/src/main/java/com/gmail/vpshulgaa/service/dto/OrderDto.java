package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private long id;
    private Long userId;
    private Long itemId;
    private LocalDateTime created;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        return id == orderDto.id && (userId != null ? userId.equals(orderDto.userId) : orderDto.userId == null)
                && (itemId != null ? itemId.equals(orderDto.itemId) : orderDto.itemId == null)
                && (created != null ? created.equals(orderDto.created) : orderDto.created == null)
                && (quantity != null ? quantity.equals(orderDto.quantity) : orderDto.quantity == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
