package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.dao.enums.RolesEnum;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao;
    private final Converter<UserProfileDto, User> userProfileConverter;
    private final DtoConverter<UserProfileDto, User> userProfileDtoConverter;
    private final Converter<RoleDto, Role> roleConverter;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ProfileService profileService;
    private final Converter<ProfileDto, Profile> profileConverter;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            @Qualifier("userProfileConverter") Converter<UserProfileDto, User> userProfileConverter,
            @Qualifier("userProfileDtoConverter") DtoConverter<UserProfileDto, User> userProfileDtoConverter,
            @Qualifier("roleConverter") Converter<RoleDto, Role> roleConverter,
            RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            ProfileService profileService,
            @Qualifier("profileConverter") Converter<ProfileDto, Profile> profileConverter) {
        this.userDao = userDao;
        this.userProfileConverter = userProfileConverter;
        this.userProfileDtoConverter = userProfileDtoConverter;
        this.roleConverter = roleConverter;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.profileService = profileService;
        this.profileConverter = profileConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileDto findOne(Long id) {
        User user = userDao.findOne(id);
        if (user != null) {
            return userProfileDtoConverter.toDto(user);
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    @Transactional
    public UserProfileDto create(UserProfileDto userDto) {
        try {
            User user = userProfileConverter.toEntity(userDto);
            Role role = roleConverter.toEntity(roleService.findByName(RolesEnum.CUSTOMER_USER));
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRole(role);
            user.setDisabled(Boolean.FALSE);
            user.setDeleted(Boolean.FALSE);
            Profile profile = new Profile();
            profile.setAddress(userDto.getAddress());
            profile.setTelephone(userDto.getTelephone());
            user.setProfile(profile);
            profile.setUser(user);
            userDao.create(user);
            userDto = userProfileDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to save user", e);
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserProfileDto update(UserProfileDto userDto) {
        try {
            User user = userProfileConverter.toEntity(userDto);
            ProfileDto profileDto = profileService.findOne(user.getId());
            profileDto.setAddress(userDto.getAddress());
            profileDto.setTelephone(userDto.getTelephone());
            Profile profile = profileConverter.toEntity(profileDto);
            user.setProfile(profile);
            profile.setUser(user);
            userDao.update(user);
            userDto = userProfileDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to update user", e);
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserProfileDto delete(UserProfileDto userDto) {
        try {
            User user = userProfileConverter.toEntity(userDto);
            ProfileDto profileDto = profileService.findOne(user.getId());
            profileDto.setAddress(userDto.getAddress());
            profileDto.setTelephone(userDto.getTelephone());
            Profile profile = profileConverter.toEntity(profileDto);
            user.setProfile(profile);
            profile.setUser(user);
            userDao.delete(user);
            userDto = userProfileDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to delete user", e);
        }
        return userDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (userDao.findOne(id) != null) {
            userDao.deleteById(id);
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    @Transactional
    public UserProfileDto changePassword(ChangePasswordDto changePassword, Long userId) {
        User user = userDao.findOne(userId);
        if (user != null) {
            if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
                changePassword.setNewPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));

                user.setPassword(changePassword.getNewPassword());
                userDao.update(user);
            }
            return userProfileDtoConverter.toDto(user);
        } else {
            throw new EntityNotFoundException(User.class, userId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long countOfUsers() {
        Long count = 0L;
        try {
            count = userDao.countOfUsers();
        } catch (Exception e) {
            logger.error("Failed to find users", e);
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfileDto> findUsersByPage(Long page, int maxResults) {
        List<UserProfileDto> usersDto = new ArrayList<>();
        List<User> users;
        try {
            users = userDao.findUsersByPage(page, maxResults);
            for (User user : users) {
                usersDto.add(userProfileDtoConverter.toDto(user));
            }
        } catch (Exception e) {
            logger.error("Failed to find users", e);
        }
        return usersDto;
    }

    @Override
    @Transactional
    public boolean isExistsEmail(String email) {
        boolean isExists = false;
        for (String string : userDao.findAllEmails()) {
            if (string.equals(email)) {
                isExists = true;
            }
        }
        return isExists;
    }
}
