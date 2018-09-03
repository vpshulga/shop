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
        userService.create(user);
        ProfileService profileService = new ProfileServiceImpl();
        Profile profile = new Profile();
        User gotUser = userService.findOne((long) 1);
        profile.setUserId(gotUser.getId());
        profile.setAddress("some addr");
        profile.setTelephone("+375256682777");
        profileService.create(profile);

        NewsService newsService = new NewsServiceImpl();
        News news1 = new News();
        News news2 = new News();

        news1.setTitle("title1");
        news1.setContent("content1");
        news1.setCreated(LocalDateTime.now());
        news1.setUserId(gotUser.getId());

        news2.setTitle("title2");
        news2.setContent("content2");
        news2.setCreated(LocalDateTime.now());
        news2.setUserId(gotUser.getId());

        newsService.create(news1);
        newsService.create(news2);

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


//        Profile profile1 = new Profile();
//        profile1.setAddress("some addr123");
//        profile1.setTelephone("+777");
//        profile1.setUserId(gotUser.getId());
//        profileService.create(profile1);


//        ItemService itemService = new ItemServiceImpl();
//        Item item = new Item();
//        item.setName("TestItem");
//        item.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        item.setPrice(BigDecimal.valueOf(155.366));
//        item.setUniqueNumber("123as2");
//        itemService.create(item);
//
//
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        Order order = new Order();
//        order.setUserId((long) 1);
//        order.setItemId((long) 1);
//        order.setQuantity(5);
//        order.setCreated(LocalDateTime.now());
//        orderService.create(order);
//        Order order1 = orderService.findOne((long) 1);
//        System.out.println(order1.getQuantity());
    }
}
