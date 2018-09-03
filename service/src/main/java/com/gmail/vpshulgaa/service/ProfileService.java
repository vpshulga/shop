package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Profile;
import java.util.List;

public interface ProfileService {
    Profile findOne(final Long id);

    List<Profile> findAll();

    void create(final Profile profile);

    void update(final Profile profile);

    void delete(final Profile profile);

    void deleteById(final Long id);
}
