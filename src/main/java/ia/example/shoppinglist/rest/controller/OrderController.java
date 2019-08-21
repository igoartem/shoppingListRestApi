package ia.example.shoppinglist.rest.controller;

import java.util.List;

import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.implementations.OrderServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/order")
public class OrderController extends RestController<OrderDto> {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        super(orderService);
        this.orderService = orderService;
    }

    /**
     * Получить списки покупок для пользователя
     *
     * @return
     */
    @GetMapping()
    @ResponseBody
    public List<OrderDto> findOrdersByUserId(@RequestParam(value = "userId", required = true) String userId) {
        return orderService.findOrdersByUserId(userId);
    }

    /**
     * создать и сдеалать список актуальным. У всех остальных списков статус становится не актуальным.
     */
    @PostMapping(value = "create")
    public void createActualOrder(@RequestBody OrderDto orderDto) {
        orderService.createActualOrder(orderDto);
    }

    /**
     * Сделать список актуальным
     *
     * @param orderId
     */
    @PutMapping(value = "activate")
    public void useActualOrder(@RequestParam(value = "orderId") String orderId, @RequestParam(value = "userId") String userId) {
        orderService.setActualOrder(orderId, userId);
    }

    /**
     * Получить актуальные списки покупое для пользователя
     *
     * @param userId
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getActualOrderByUserId(@PathVariable(value = "userId") String userId) {
        return orderService.getActualOrderByUserId(userId);
    }

    /**
     * Создать список на основе существующего у пользователя
     */
    @PostMapping
    public void createOrderBasedOrder(@RequestParam(value = "orderId") String orderId,@RequestParam(value = "userId") String userId) {

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
        public void updateOrderWithProduct(@RequestParam(value = "userId") String userId, @RequestParam("orderId") String orderId, @PathVariable EntryOrderDto[] entryOrderDtos) {

        }
}
