package com.gmail.vpshulgaa.service.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
    private Boolean deleted;

    private List<DiscountDto> discounts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<DiscountDto> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountDto> discounts) {
        this.discounts = discounts;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id) &&
                Objects.equals(name, itemDto.name) &&
                Objects.equals(description, itemDto.description) &&
                Objects.equals(uniqueNumber, itemDto.uniqueNumber) &&
                Objects.equals(price, itemDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, uniqueNumber, price);
    }
}
