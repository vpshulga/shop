import com.gmail.vpshulgaa.service.DiscountService;
import com.gmail.vpshulgaa.service.ItemService;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import com.gmail.vpshulgaa.service.dto.ItemDto;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.impl.DiscountServiceImpl;
import com.gmail.vpshulgaa.service.impl.ItemServiceImpl;
import com.gmail.vpshulgaa.service.impl.OrderServiceImpl;
import com.gmail.vpshulgaa.service.impl.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Task {
    public static void main(String[] args) {
        createDiscounts();
        createItems();
        setDiscountsToItems();
        createUser();
        setDiscountToUser();
        createOrders();
        showInfoAboutOrders();
        findItemsByDiscount(BigDecimal.valueOf(10));
    }

    private static void createItems() {
        ItemService itemService = new ItemServiceImpl();
        Random random = new Random();
        for (int i = 1; i <= 30; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setName("item" + i);
            itemDto.setDescription("description" + i);
            itemDto.setUniqueNumber("unique" + i);
            itemDto.setPrice(BigDecimal.valueOf(random.nextInt(400) + 100));
            itemService.create(itemDto);
        }
    }

    private static void createDiscounts() {
        Random random = new Random();
        int year = 2019;
        DiscountService discountService = new DiscountServiceImpl();
        DiscountDto discount1 = new DiscountDto();
        discount1.setName("ten");
        discount1.setPercent(BigDecimal.valueOf(10));
        discount1.setExpireDate(LocalDate.of(year, random.nextInt(12) + 1, random.nextInt(27) + 1));

        DiscountDto discount2 = new DiscountDto();
        discount2.setName("twenty");
        discount2.setPercent(BigDecimal.valueOf(20));
        discount2.setExpireDate(LocalDate.of(year, random.nextInt(12) + 1, random.nextInt(27) + 1));

        DiscountDto discount3 = new DiscountDto();
        discount3.setName("thirty");
        discount3.setPercent(BigDecimal.valueOf(30));
        discount3.setExpireDate(LocalDate.of(year, random.nextInt(12) + 1, random.nextInt(27) + 1));

        discountService.create(discount1);
        discountService.create(discount2);
        discountService.create(discount3);
    }

    private static void setDiscountsToItems() {
        ItemService itemService = new ItemServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();
        for (ItemDto itemDto : itemService.findItemsInPriceDiapason(BigDecimal.valueOf(200), BigDecimal.valueOf(299))) {
            DiscountDto discount = discountService.findOne(1L);
            itemDto.getDiscounts().add(discount);
            itemService.update(itemDto);
        }

        for (ItemDto itemDto : itemService.findItemsInPriceDiapason(BigDecimal.valueOf(300), BigDecimal.valueOf(399))) {
            DiscountDto discount = discountService.findOne(2L);
            itemDto.getDiscounts().add(discount);
            itemService.update(itemDto);
        }

        for (ItemDto itemDto : itemService.findItemsInPriceDiapason(BigDecimal.valueOf(400), BigDecimal.valueOf(500))) {
            DiscountDto discount = discountService.findOne(3L);
            itemDto.getDiscounts().add(discount);
            itemService.update(itemDto);
        }
    }

    private static void createUser() {
        UserService userService = new UserServiceImpl();
        UserDto userDto = new UserDto();
        userDto.setName("Valera");
        userDto.setEmail("vvv@vv.ru");
        userDto.setSurname("Petrov");
        userDto.setPassword("vvv");
        userService.create(userDto);
    }

    private static void setDiscountToUser() {
        UserService userService = new UserServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();
        UserDto user = userService.findOne(1L);
        user.setDiscount(discountService.findOne(2L));
        userService.update(user);
    }

    private static void createOrders() {
        Random random = new Random();
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        ItemService itemService = new ItemServiceImpl();
        for (int i = 1; i <= 4; i++) {
            OrderDto orderDto = new OrderDto();
            orderDto.setCreated(LocalDateTime.now());
            List<ItemDto> items = itemService.findItemsInPriceDiapason(BigDecimal.valueOf(250), BigDecimal.valueOf(450));
            ItemDto item = items.get(random.nextInt((items.size() - 1)));
            orderDto.setItem(item);
            Long qantity = itemService.countItemsInDiapason(BigDecimal.valueOf(250), BigDecimal.valueOf(450));
            orderDto.setQuantity(Math.toIntExact(qantity));
            UserDto user = userService.findOne(1L);
            orderDto.setUser(user);
            orderService.create(orderDto);
        }
    }

    private static void showInfoAboutOrders() {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        StringBuilder sb = new StringBuilder();
        UserDto user = userService.findOne(1L);
        for (OrderDto order : orderService.findOrdersByUserId(1L)) {
            BigDecimal price = order.getItem().getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            BigDecimal discountsForItem = BigDecimal.valueOf(0);
            for (DiscountDto discountDto : order.getItem().getDiscounts()) {
                discountsForItem = discountsForItem.add(discountDto.getPercent());
            }
            BigDecimal totalDiscount = user.getDiscount().getPercent().add(discountsForItem).divide(BigDecimal.valueOf(100));
            BigDecimal priceWithDiscount = price.subtract(price.multiply(totalDiscount));
            sb.append(user.getName())
                    .append(" | ")
                    .append(order.getItem().getName())
                    .append(" | ")
                    .append(order.getQuantity())
                    .append(" | ")
                    .append(price)
                    .append(" | ")
                    .append(priceWithDiscount).append("\n");
        }
        System.out.println(sb);
    }

    private static void findItemsByDiscount(BigDecimal percent) {
        ItemService itemService = new ItemServiceImpl();
        List<ItemDto> items = itemService.findItemsByDiscount(percent);
        for (ItemDto item : items) {
            System.out.println(item.getName());
        }
    }

}
