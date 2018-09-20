package com.gmail.vpshulgaa.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiscountDto {
    private Long id;
    private String name;
    private BigDecimal percent;
    private LocalDate expireDate;

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
