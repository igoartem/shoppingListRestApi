package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.exeption.EntityNotFoundException;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.implementations.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param userId
     * @param activate
     * @param name     - фильтр по названию списка
     * @return
     */
    @GetMapping(value = "user")
    @ResponseBody
    public List<OrderDto> findOrdersByUserId(@RequestParam(value = "userId") String userId,
                                             @RequestParam(value = "activate", required = false) Boolean activate,
                                             @RequestParam(value = "name", required = false) String name) {
        return orderService.findOrdersByUserId(userId, activate, name);
    }

    /**
     * создать список.
     * По умолчинию список создается не актуальным
     */
    @PostMapping(value = "create")
    public void createOrder(@RequestBody OrderDto orderDto,
                            @RequestParam(value = "activate", required = false) Boolean activate,
                            @RequestParam(value = "userId") String userId) throws BindException {
        orderService.createOrder(orderDto, activate, userId);
    }

    /**
     * Обновить список покупок, если он сушествует
     */
    @PutMapping(value = "update")
    public void useActualOrder(@RequestBody OrderDto orderDto,
                               @RequestParam(value = "userId") String userId,
                               @RequestParam(value = "activate", required = false) Boolean activate) throws BindException, EntityNotFoundException {
        orderService.updateOrderDto(orderDto, userId, activate);
    }

    /**
     * Добавить в список запись
     */
    @PostMapping(value = "entry/create")
    public void createEntryOrder(@RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "orderId") String orderId,
                                 @RequestParam(value = "entryOrder") EntryOrderDto entryOrderDto) {
        orderService.createEntryOrder(userId, orderId, entryOrderDto);
    }

    /**
     * Обновить информацию о записях в списке
     */
    @PutMapping(value = "entry/update")
    public void updateEntryOrder(@RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "orderId") String orderId,
                                 @RequestParam(value = "entryOrder") EntryOrderDto entryOrderDto) {
        orderService.updateEntryOrder(userId, orderId, entryOrderDto);
    }
}
