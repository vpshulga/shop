package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.dao.enums.Roles;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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
    public UserServiceImpl(UserDao userDao,
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserProfileDto findOne(Long id) {
        UserProfileDto userProfileDto = new UserProfileDto();
        try {
            User user = userDao.findOne(id);
            userProfileDto = userProfileDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to find user");
        }
        return userProfileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<UserProfileDto> findAll() {
        List<UserProfileDto> users = new ArrayList<>();
        try {
            users = userProfileDtoConverter.toDtoList(userDao.findAll());
        } catch (Exception e) {
            logger.error("Failed to get users", e);
        }
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserProfileDto create(UserProfileDto userDto) {
        try {
            User user = userProfileConverter.toEntity(userDto);
            Role role = roleConverter.toEntity(roleService.findByName(Roles.CUSTOMER_USER));
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete user", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserProfileDto findByEmail(String email) {
        UserProfileDto userDto = null;
        try {
            User user = userDao.findByEmail(email);
            userDto = userProfileDtoConverter.toDto(user);
        } catch (Exception e) {
            logger.error("Failed to find user", e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<UserProfileDto> findNotDeletedUsers() {
        List<UserProfileDto> usersDto = new ArrayList<>();
        List<User> users;
        try {
            users = userDao.findNotDeletedUsers();
            for (User user : users) {
                usersDto.add(userProfileDtoConverter.toDto(user));
            }
        } catch (Exception e) {
            logger.error("Failed to find users");
        }
        return usersDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserProfileDto changePassword(ChangePasswordDto changePassword, UserProfileDto userDto) {
        changePassword.setOldPassword(bCryptPasswordEncoder.encode(changePassword.getOldPassword()));
        changePassword.setNewPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
        changePassword.setConfirmPassword(bCryptPasswordEncoder.encode(changePassword.getConfirmPassword()));
        userDto.setPassword(changePassword.getNewPassword());
        User user = userProfileConverter.toEntity(userDto);
        userDao.update(user);
        userDto = userProfileDtoConverter.toDto(user);
        return userDto;
    }
}
