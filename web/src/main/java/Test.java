import com.gmail.vpshulgaa.service.*;
import com.gmail.vpshulgaa.service.dto.*;
import com.gmail.vpshulgaa.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        NewsService newsService = new NewsServiceImpl();

        UserDto userDto = new UserDto();
        userDto.setName("v");
        userDto.setSurname("v");
        userDto.setEmail("v");
        userDto.setPassword("v");

        ProfileDto profileDto = new ProfileDto();

        profileDto.setAddress("addressMO");
        profileDto.setTelephone("telephoneMO");

        userDto.setProfileDto(profileDto);


        NewsDto newsDto1 = new NewsDto();
        newsDto1.setTitle("s");
        newsDto1.setContent("s");
        newsDto1.setCreated(LocalDateTime.now());

        NewsDto newsDto2 = new NewsDto();
        newsDto2.setTitle("s1");
        newsDto2.setContent("s2");
        newsDto2.setCreated(LocalDateTime.now());

        userDto.getNews().add(newsDto1);
        userDto.getNews().add(newsDto2);

        userService.create(userDto);

        CommentDto commentDto = new CommentDto();
        commentDto.setContent("c1");
        commentDto.setCreated(LocalDateTime.now());

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setContent("c2");
        commentDto1.setCreated(LocalDateTime.now());

        CommentDto commentDto2 = new CommentDto();
        commentDto2.setContent("c3");
        commentDto2.setCreated(LocalDateTime.now());

        UserDto userDto1 = userService.findOne(1L);
        NewsDto news1 = newsService.findOne(1L);
        NewsDto news2 = newsService.findOne(2L);

        commentDto.setNews(news1);
        commentDto1.setNews(news1);
        commentDto2.setNews(news2);

        commentDto.setUser(userDto1);
        commentDto1.setUser(userDto1);
        commentDto2.setUser(userDto1);


        commentService.create(commentDto);
        commentService.create(commentDto1);
        commentService.create(commentDto2);

        commentService.delete(commentService.findOne(2L));

        AuditDto a1 = new AuditDto();
        a1.setEventType("e1");
        a1.setCreated(LocalDateTime.now());

        AuditDto a2 = new AuditDto();
        a2.setEventType("e2");
        a2.setCreated(LocalDateTime.now());



        UserDto u = userService.findOne(1L);
        u.getAudits().add(a1);
        u.getAudits().add(a2);

        userService.update(u);

        ItemService itemService = new ItemServiceImpl();

        ItemDto item1 = new ItemDto();
        ItemDto item2 = new ItemDto();
        ItemDto item3 = new ItemDto();

        item1.setName("i1");
        item1.setDescription("d1");
        item1.setPrice(BigDecimal.valueOf(10.5));
        item1.setUniqueNumber("sb1");

        item2.setName("i2");
        item2.setDescription("d2");
        item2.setPrice(BigDecimal.valueOf(11.5));
        item2.setUniqueNumber("sb2");

        item3.setName("i3");
        item3.setDescription("d3");
        item3.setPrice(BigDecimal.valueOf(12.5));
        item3.setUniqueNumber("sb3");

        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);

        u = userService.findOne(1L);
        OrderService orderService = new OrderServiceImpl();
        OrderDto o1 = new OrderDto();
        o1.setItem(itemService.findOne(1L));
        o1.setQuantity(10);
        o1.setCreated(LocalDateTime.now());

        OrderDto o2 = new OrderDto();
        o2.setItem(itemService.findOne(3L));
        o2.setQuantity(4);
        o2.setCreated(LocalDateTime.now());

        u.getOrders().add(o1);
        u.getOrders().add(o2);
        userService.update(u);

        RoleDto r = new RoleDto();
        r.setName("r");

        PermissionDto p1 = new PermissionDto();
        p1.setName("p1");

        PermissionDto p2 = new PermissionDto();
        p2.setName("p2");

        r.getPermissions().add(p1);
        r.getPermissions().add(p2);

        u = userService.findOne(1L);
        RoleService roleService = new RoleServiceImpl();
        roleService.create(r);
        u.setRole(roleService.findOne(1L));

        userService.update(u);


    }
}
