package ia.example.shoppinglist.rest.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.implementations.OrderServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/order")
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
    @GetMapping(value = "")
    @ResponseBody
    public List<OrderDto> findOrdersByUserId(@RequestParam(value = "userId", required = true) String userId) {
        return orderService.findOrdersByUserId(userId);
    }

    /**
     * создать и сдеалать список актуальным. У всех остальных списков статус становится не актуальным.
     */
    @PostMapping(value = "create")
    public void createActualOrder(@RequestBody OrderDto orderDto, @RequestParam String userId) {
//        orderService
    }

    /**
     * Сделать список актуальным
     *
     * @param orderId
     */
    public void useActualOrder(String orderId) {

    }

    /**
     * Получить актуальный список для пользователя
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "order/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto getActualOrderByUserId(@PathVariable(value = "userId") String userId) {

        return null;
    }

    /**
     * Создать список на основе существующего у пользователя
     */
    //    @PutMapping(value = "")
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
    //    @PutMapping(value = "")
    //    public void updateOrderWithProduct(String userId, String orderId, @PathVariable EntryOrderDto[] entryOrderDtos) {

    //    }
}
