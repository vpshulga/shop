package com.gmail.vpshulgaa.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_PROFILE")
public class Profile implements Serializable {
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "F_USER_ID", unique = true, nullable = false)
    private long userId;
    @Column(name = "F_ADDRESS", length = 200)
    private String address;
    @Column(name = "F_TELEPHONE", length = 20)
    private String telephone;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;
}
