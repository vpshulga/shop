import com.gmail.vpshulgaa.dao.entities.Item;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.impl.ItemServiceImpl;
import com.gmail.vpshulgaa.service.impl.OrderServiceImpl;
import com.gmail.vpshulgaa.service.impl.ProfileServiceImpl;
import com.gmail.vpshulgaa.service.impl.UserServiceImpl;
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
