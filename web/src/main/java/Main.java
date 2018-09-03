import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.dao.impl.PermissionDaoImpl;
import com.gmail.vpshulgaa.service.*;
import com.gmail.vpshulgaa.service.impl.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();

        user.setEmail("vpsh@gmail.com");
        user.setName("Valera");
        user.setSurname("Petrovich");
        user.setPassword("123");
        user.setRoleId(1);

        News news1 = new News();
        News news2 = new News();

        news1.setTitle("title123");
        news1.setContent("content1");
        news1.setCreated(LocalDateTime.now());

        news2.setTitle("title234");
        news2.setContent("content2");
        news2.setCreated(LocalDateTime.now());

        user.getNews().add(news1);
        user.getNews().add(news2);

        Profile profile = new Profile();
        profile.setAddress("some addr");
        profile.setTelephone("+375256682777");
        user.setProfile(profile);
        profile.setUser(user);

        userService.create(user);

        User user1 = userService.findByEmail("vpsh@gmail.com");
        System.out.println(user1.getName());

//        ProfileService profileService = new ProfileServiceImpl();
//        Profile profile = new Profile();
//        User gotUser = userService.findOne((long) 1);
//        profile.setUserId(gotUser.getId());
//        profile.setAddress("some addr");
//        profile.setTelephone("+375256682777");
//        profileService.create(profile);





        PermissionService permissionService = new PermissionServiceImpl();
        RoleService roleService = new RoleServiceImpl();

        Role role = new Role();
        role.setName("ADMIN");

        Permission permission1 = new Permission();
        permission1.setName("perm1");

        Permission permission2 = new Permission();
        permission2.setName("perm2");

        role.getPermissions().add(permission1);
        permission1.getRoles().add(role);
        role.getPermissions().add(permission2);

        roleService.create(role);



    }
}
