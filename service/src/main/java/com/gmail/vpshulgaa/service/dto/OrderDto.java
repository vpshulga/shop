package com.gmail.vpshulgaa.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {
    private long id;
    private LocalDateTime created;
    private Integer quantity;
    private ItemDto item;
    private UserDto user;

    public OrderDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id &&
                Objects.equals(created, orderDto.created) &&
                Objects.equals(quantity, orderDto.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, quantity);
    }
}
