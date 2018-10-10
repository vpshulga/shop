package com.gmail.vpshulgaa.dao.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "T_PROFILE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    public Profile() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return userId == profile.userId &&
                Objects.equals(address, profile.address) &&
                Objects.equals(telephone, profile.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, address, telephone);
    }
}
