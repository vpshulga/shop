package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_ITEM")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_NAME", length = 50)
    private String name;
    @Column(name = "F_DESCRIPTION", length = 1500)
    private String description;
    @Column(name = "F_U_NUMBER", length = 10)
    private String uniqueNumber;
    @Column(name = "F_PRICE")
    private BigDecimal price;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "T_DISCOUNT_ITEM",
            joinColumns = {@JoinColumn(name = "F_ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_DISCOUNT_ID")}
    )
    private List<Discount> discounts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(uniqueNumber, item.uniqueNumber) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, uniqueNumber, price);
    }
}
