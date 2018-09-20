package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;

    @Column(name = "F_EMAIL", length = 50)
    @NotNull
    @Email
    private String email;
    @Column(name = "F_NAME", length = 50)
    private String name;
    @Column(name = "F_SURNAME", length = 50)
    private String surname;
    @Column(name = "F_PASSWORD", length = 50)
    private String password;
    @Column(name = "F_IS_DISABLED")
    private boolean isDisabled = false;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "F_USER_ID")
    private Set<Audit> audits = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "F_ROLE_ID")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "F_DISCOUNT_ID")
    private Discount discount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, name, surname, password);
    }
}
