package com.gmail.vpshulgaa.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class DiscountDto {
    private Long id;
    private String name;
    private BigDecimal percent;
    private LocalDate expireDate;

    public DiscountDto() {
    }

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

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountDto that = (DiscountDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(percent, that.percent) &&
                Objects.equals(expireDate, that.expireDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, percent, expireDate);
    }
}
