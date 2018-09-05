import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.dao.impl.PermissionDaoImpl;
import com.gmail.vpshulgaa.service.*;
import com.gmail.vpshulgaa.service.converter.impl.todto.UserDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.*;
import com.gmail.vpshulgaa.service.dto.*;
import com.gmail.vpshulgaa.service.impl.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ProfileConverter profileConverter = new ProfileConverter();
        UserConverter userConverter = new UserConverter();
        NewsConverter newsConverter = new NewsConverter();
        PermissionConverter permissionConverter = new PermissionConverter();
        RoleConverter roleConverter = new RoleConverter();

        UserService userService = new UserServiceImpl();



        UserDto userDto = new UserDto();
        userDto.setEmail("aaa");
        userDto.setName("bbb");
        userDto.setSurname("ccc");
        userDto.setPassword("ddd");
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



        UserDto user1 = userService.findByEmail("aaa");
        System.out.println(user1.getNews());


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
        permissionDto.getRoles().add(roleConverter.toEntity(roleDto));
        permissionDto2.getRoles().add(roleConverter.toEntity(roleDto));


        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto1));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto2));
        roleDto1.getPermissions().add(permissionConverter.toEntity(permissionDto3));

        permissionDto.getRoles().add(roleConverter.toEntity(roleDto1));
        permissionDto1.getRoles().add(roleConverter.toEntity(roleDto1));
        permissionDto2.getRoles().add(roleConverter.toEntity(roleDto1));
        permissionDto3.getRoles().add(roleConverter.toEntity(roleDto1));

        RoleService roleService = new RoleServiceImpl();
        roleService.create(roleDto);
        roleService.create(roleDto1);
        PermissionService permissionService = new PermissionServiceImpl();
        permissionService.create(permissionDto);
        permissionService.create(permissionDto1);
        permissionService.create(permissionDto2);
        permissionService.create(permissionDto3);


//
//        PermissionService permissionService = new PermissionServiceImpl();
//        RoleService roleService = new RoleServiceImpl();
//
//        Role role = new Role();
//        role.setName("ADMIN");
//
//        Permission permission1 = new Permission();
//        permission1.setName("perm1");
//
//        Permission permission2 = new Permission();
//        permission2.setName("perm2");
//
//        role.getPermissions().add(permission1);
//        permission1.getRoles().add(role);
//        role.getPermissions().add(permission2);
//
//        roleService.create(role);



    }
}
