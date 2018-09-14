package com.gmail.vpshulgaa.service.dto;

import com.gmail.vpshulgaa.dao.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
    private long userId;
    private String address;
    private String telephone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileDto that = (ProfileDto) o;

        return userId == that.userId && (address != null ? address.equals(that.address) : that.address == null)
                && (telephone != null ? telephone.equals(that.telephone) : that.telephone == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }
}
