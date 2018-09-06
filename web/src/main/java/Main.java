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


        UserDto userDto = new UserDto();
        ProfileDto profileDto = new ProfileDto();

        userDto.setEmail("emailOO");
        userDto.setName("nameOO");
        userDto.setSurname("surnameOO");
        userDto.setPassword("passwordOO");

        profileDto.setAddress("addressOO");
        profileDto.setTelephone("telephoneOO");


        userDto.setProfileDto(profileDto);

        userService.create(userDto);
        userService.create(userDto);

        UserDto userDto1 = userService.findOne((long) 2);

        userDto1.setEmail("new");
        userDto1.getProfileDto().setAddress("new");



        userService.update(userDto1);
//        userService.delete(userDto1);
    }

    private static void testManyToOne(){
        UserService userService = new UserServiceImpl();

        UserDto userDto = new UserDto();

        userDto.setEmail("emailMO");
        userDto.setName("nameMO");
        userDto.setSurname("surnameMO");
        userDto.setPassword("passwordMO");

        ProfileDto profileDto = new ProfileDto();

        profileDto.setAddress("addressMO");
        profileDto.setTelephone("telephoneMO");

        userDto.setProfileDto(profileDto);

        NewsDto newsDto1 = new NewsDto();
        newsDto1.setTitle("1");
        newsDto1.setContent("2");
        newsDto1.setCreated(LocalDateTime.now());

        NewsDto newsDto2 = new NewsDto();
        newsDto2.setTitle("2");
        newsDto2.setContent("3");
        newsDto2.setCreated(LocalDateTime.now());

        userDto.getNewsDtoSet().add(newsDto1);
        userDto.getNewsDtoSet().add(newsDto2);

        CommentDto commentDto = new CommentDto();
        commentDto.setContent("c1");
        commentDto.setCreated(LocalDateTime.now());

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setContent("c2");
        commentDto1.setCreated(LocalDateTime.now());

        CommentDto commentDto2 = new CommentDto();
        commentDto2.setContent("c3");
        commentDto2.setCreated(LocalDateTime.now());


        userDto.getCommentDtoSet().add(commentDto);
        userDto.getCommentDtoSet().add(commentDto1);
        userDto.getCommentDtoSet().add(commentDto2);

        userService.create(userDto);


    }

    private static void testManyToMany() {
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

        roleDto.getPermissionDtoSet().add(permissionDto);
        roleDto.getPermissionDtoSet().add(permissionDto2);

        roleDto1.getPermissionDtoSet().add(permissionDto);
        roleDto1.getPermissionDtoSet().add(permissionDto1);
        roleDto1.getPermissionDtoSet().add(permissionDto2);
        roleDto1.getPermissionDtoSet().add(permissionDto3);


        roleService.create(roleDto);
        roleService.create(roleDto1);


    }
}
