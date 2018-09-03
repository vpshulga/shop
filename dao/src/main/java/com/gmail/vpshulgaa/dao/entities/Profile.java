package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_PROFILE")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_USER_ID", unique = true, nullable = false)
    private Long userId;
    @Column(name = "F_ADDRESS", length = 200)
    private String address;
    @Column(name = "F_TELEPHONE", length = 20)
    private String telephone;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_ID")
    private User user;
}
