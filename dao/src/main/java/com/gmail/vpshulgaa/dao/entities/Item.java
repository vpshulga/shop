package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

}
