package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_USER")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_EMAIL", length = 50)
    private String email;
    @Column(name = "F_NAME", length = 50)
    private String name;
    @Column(name = "F_SURNAME", length = 50)
    private String surname;
    @Column(name = "F_PASSWORD", length = 50)
    private String password;
    @Column(name = "F_ROLE_ID", length = 50)
    private long roleId;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;
}
