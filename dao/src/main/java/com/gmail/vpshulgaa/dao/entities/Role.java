package com.gmail.vpshulgaa.dao.entities;

import com.gmail.vpshulgaa.dao.enums.Roles;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "F_NAME")
    private Roles name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "T_PERMISSION_ROLE",
            joinColumns = {@JoinColumn(name = "F_ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_PERMISSION_ID")}
    )
    private Set<Permission> permissions = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return id == role.id && (name != null ? name.equals(role.name) : role.name == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
