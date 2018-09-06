package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_ITEM")
public class Item implements Serializable{
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "F_ITEM_ID")
    private Set<Order> orders = new HashSet<>();

}
