import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.impl.toentity.*;
import com.gmail.vpshulgaa.service.dto.*;
import com.gmail.vpshulgaa.service.impl.RoleServiceImpl;
import com.gmail.vpshulgaa.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        testOneToOne();
        testManyToOne();
        testManyToMany();
    }


    private static void testOneToOne(){
        UserService userService = new UserServiceImpl();

        UserConverter userConverter = new UserConverter();
        ProfileConverter profileConverter = new ProfileConverter();

        UserDto userDto = new UserDto();
        ProfileDto profileDto = new ProfileDto();

        userDto.setEmail("emailOO");
        userDto.setName("nameOO");
        userDto.setSurname("surnameOO");
        userDto.setPassword("passwordOO");
        userDto.setRoleId(1);

        profileDto.setAddress("addressOO");
        profileDto.setTelephone("telephoneOO");

        profileDto.setUser(userConverter.toEntity(userDto));
        userDto.setProfile(profileConverter.toEntity(profileDto));

        userService.create(userDto);
    }

    private static void testManyToOne(){
        UserService userService = new UserServiceImpl();
        NewsConverter newsConverter = new NewsConverter();

        UserDto userDto = new UserDto();

        userDto.setEmail("emailMO");
        userDto.setName("nameMO");
        userDto.setSurname("surnameMO");
        userDto.setPassword("passwordMO");
        userDto.setRoleId(1);


        NewsDto newsDto1 = new NewsDto();
        newsDto1.setTitle("1");
        newsDto1.setContent("2");
        newsDto1.setCreated(LocalDateTime.now());

        NewsDto newsDto2 = new NewsDto();
        newsDto2.setTitle("2");
        newsDto2.setContent("3");
        newsDto2.setCreated(LocalDateTime.now());

        userDto.getNews().add(newsConverter.toEntity(newsDto1));
        userDto.getNews().add(newsConverter.toEntity(newsDto2));

        userService.create(userDto);


    }

    private static void testManyToMany() {
        PermissionConverter permissionConverter = new PermissionConverter();
        RoleConverter roleConverter = new RoleConverter();
        RoleService roleService = new RoleServiceImpl();

        RoleDto roleDto = new RoleDto();
        roleDto.setName("ADMIN");
        RoleDto roleDto1 = new RoleDto();
        roleDto1.setName("USER");

        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setName("p");
        PermissionDto permissionDto1 = new PermissionDto();
        permissionDto1.setName("p1");
        PermissionDto permissionDto2 = new PermissionDto();
        permissionDto2.setName("p2");
        PermissionDto permissionDto3 = new PermissionDto();
        permissionDto3.setName("p3");

        roleDto.getPermissions().add(permissionConverter.toEntity(permissionDto));
        roleDto.getPermissions().add(permissionConverter.toEntity(permissionDto2));

        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto1));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto2));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto3));

        permissionDto.getRoles().add(roleConverter.toEntity(roleDto));
        permissionDto.getRoles().add(roleConverter.toEntity(roleDto1));

        permissionDto1.getRoles().add(roleConverter.toEntity(roleDto1));

        permissionDto2.getRoles().add(roleConverter.toEntity(roleDto));
        permissionDto2.getRoles().add(roleConverter.toEntity(roleDto1));

        permissionDto3.getRoles().add(roleConverter.toEntity(roleDto1));

        roleService.create(roleDto);
        roleService.create(roleDto1);


    }
}
