package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_ITEM")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_NAME", length = 50)
    private String name;
    @Column(name = "F_DESCRIPTION", length = 1500)
    private String description;
    @Column(name = "F_U_NUMBER", length = 200, unique = true)
    private String uniqueNumber;
    @Column(name = "F_PRICE")
    private BigDecimal price;
    @Column(name = "F_IS_DELETED")
    private Boolean isDeleted;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "T_DISCOUNT_ITEM",
            joinColumns = {@JoinColumn(name = "F_ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_DISCOUNT_ID")}
    )
    private List<Discount> discounts = new ArrayList<>();

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

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
