package com.gmail.vpshulgaa.dao.entities;

import com.gmail.vpshulgaa.dao.enums.StatusEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_ORDER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
    @Column(name = "F_QUANTITY")
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS", length = 50)
    private StatusEnum status = StatusEnum.NEW;

    @ManyToOne
    @JoinColumn(name = "F_ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(created, order.created) &&
                Objects.equals(quantity, order.quantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity);
    }
}
