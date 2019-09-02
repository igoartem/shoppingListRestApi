package ia.example.shoppinglist.app.repositories;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ia.example.shoppinglist.domain.Category;
import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.Manufacturer;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.domain.Product;
import ia.example.shoppinglist.rest.dto.CategoryDto;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.ManufacturerDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.dto.PlannedOrderDto;
import ia.example.shoppinglist.rest.dto.ProductDto;

public abstract class TestUtils {

    private static Random random = new SecureRandom();

    //Entity

    private static List<EntryOrder> createEntryOrders() {

        List<EntryOrder> entryOrders = new ArrayList<>();
        entryOrders.add(createEntryOrder(EntryOrder.StateEntryOrder.NEW, "description " + random.nextInt(100), "product " + random.nextInt(100)));
        entryOrders.add(createEntryOrder(EntryOrder.StateEntryOrder.NEW, "desc " + random.nextInt(100), "product " + random.nextInt(100)));
        return entryOrders;
    }

    private static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(createCategory("category 1"));
        product.setDescription("description" + name);
        product.setPrice("111");
        product.setManufacturer(createManufacturer("manufacturer 1"));
        return product;
    }

    public static EntryOrder createEntryOrder(EntryOrder.StateEntryOrder stateEntryOrder, String description, String name) {
        EntryOrder entryOrder = new EntryOrder();
        entryOrder.setPrice(random.nextInt(2000));
        entryOrder.setDescription(description);
        entryOrder.setStateOrder(stateEntryOrder);
        entryOrder.setProduct(createProduct(name));
        return entryOrder;
    }

    public static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    public static Manufacturer createManufacturer(String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        return manufacturer;
    }

    public static Order createOrder(String name, String userId, Boolean actual) {
        Order order = new Order();
        order.setActual(actual);
        order.setName(name);
        order.setUserId(userId);
        order.setCreateDate(LocalDateTime.now());
        order.setEntryOrders(TestUtils.createEntryOrders());
        return order;
    }

    public static PlannedOrder createPlannedOrder(String userId) {
        PlannedOrder plannedOrder = new PlannedOrder();
        plannedOrder.setUserId(userId);
        plannedOrder.setOrder(createOrder("test" + random.nextInt(111), userId, false));
        return plannedOrder;
    }

    //EntityDto

    public static CategoryDto createCategoryDto(String name) {
        CategoryDto category = new CategoryDto();
        category.setName(name);
        return category;
    }

    public static ProductDto createProductDto(String name) {
        ProductDto product = new ProductDto();
        product.setName(name);
        product.setCategory(createCategoryDto(("category 2")));
        product.setDescription("description" + name);
        product.setPrice("111");
        product.setManufacturer(createManufacturerDto("manufacturer 1"));
        return product;
    }

    public static ManufacturerDto createManufacturerDto(String name) {
        ManufacturerDto manufacturer = new ManufacturerDto();
        manufacturer.setName(name);
        return manufacturer;
    }

    public static OrderDto createOrderDto(String name, String userId, Boolean actual) {
        OrderDto order = new OrderDto();
        order.setActual(actual);
        order.setName(name);
        order.setUserId(userId);
        order.setCreateDate(LocalDateTime.now());
        order.setEntryOrders(TestUtils.createEntryOrderDtoList());
        return order;
    }

    private static List<EntryOrderDto> createEntryOrderDtoList() {

        List<EntryOrderDto> entryOrders = new ArrayList<>();
        entryOrders.add(createEntryOrderDto(EntryOrder.StateEntryOrder.NEW, "description " + random.nextInt(100), "product " + random.nextInt(100)));
        entryOrders.add(createEntryOrderDto(EntryOrder.StateEntryOrder.NEW, "desc " + random.nextInt(100), "product " + random.nextInt(100)));
        return entryOrders;
    }

    public static EntryOrderDto createEntryOrderDto(EntryOrder.StateEntryOrder stateEntryOrder, String description, String name) {
        EntryOrderDto entryOrder = new EntryOrderDto();
        entryOrder.setPrice(random.nextInt(2000));
        entryOrder.setDescription(description);
        entryOrder.setStateOrder(stateEntryOrder);
        entryOrder.setProduct(createProductDto(name));
        return entryOrder;
    }

    public static PlannedOrderDto createPlannedOrderDto(String userId) {
        PlannedOrderDto plannedOrder = new PlannedOrderDto();
        plannedOrder.setUserId(userId);
        plannedOrder.setOrderDto(createOrderDto("test" + random.nextInt(111), userId, false));
        return plannedOrder;
    }

}
