package ia.example.shoppinglist.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.implementations.OrderServiceImpl;

@org.springframework.web.bind.annotation.RestController("order")
public class OrderController extends RestController<OrderDto> {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        super(orderService);
        this.orderService = orderService;
    }

    /**
     * Получить списки покупок для пользователя
     *
     * @return
     */
    @GetMapping(value = "user/{userId}")
    public List<OrderDto> findOrderListByUserId(@RequestParam String userId) {
        return orderService.findOrderListByUserId(userId);
    }

    /**
     * Получить список для пользователя
     *
     * @return
     */
    public OrderDto findOrderByUserId(String userId, String orderId) {
        return null;
    }


    /**
     * создать и  Сдеалать список актуальным. У всех остальных списков статус становится не актуальным.
     *
     * @param order
     */
    public void makeOrderActual(@RequestBody OrderDto order) {
    }

    /**
     * Сделать список актуальным
     * @param orderId
     */
    public void useActualOrder(String orderId){

    }

    /**
     * Получить актуальный список для пользователя
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "order/user/{id}")
    public OrderDto getActualOrderByUserId(@RequestParam String userId) {


        return null;
    }

    /**
     * Создать список на основе существующего у пользователя
     */
    @PutMapping(value = "")
    public void createOrderBasedOrder(OrderDto order, String userId) {

    }

    /**
     * Создать задачу для планирования покупки на основе списка
     */
    public void createPlannedTaskBasedOrder(OrderDto order, String userId) {

    }

    /**
     * Получить список всех шаблонов для пользоваля
     */

    /**
     * Внести информацию о покупку
     */
    @PutMapping(value = "")
    public void updateOrderWithProduct(String userId, String orderId, @PathVariable EntryOrderDto[] entryOrderDtos) {

    }
}
