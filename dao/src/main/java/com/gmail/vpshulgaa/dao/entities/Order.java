package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_ORDER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
    @Column(name = "F_QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "F_ITEM_ID")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(created, order.created) &&
                Objects.equals(quantity, order.quantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity);
    }
}
