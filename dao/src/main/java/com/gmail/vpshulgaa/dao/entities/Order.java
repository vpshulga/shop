package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "T_ORDER")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_USER_ID")
    private Long userId;
    @Column(name = "F_ITEM_ID")
    private Long itemId;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
    @Column(name = "F_QUANTITY")
    private Integer quantity;
}
