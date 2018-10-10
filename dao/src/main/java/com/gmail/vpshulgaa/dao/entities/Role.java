package com.gmail.vpshulgaa.dao.entities;

import com.gmail.vpshulgaa.dao.enums.Roles;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "T_ROLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
