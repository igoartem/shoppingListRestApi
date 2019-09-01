package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.rest.dto.PlannedOrderDto;
import ia.example.shoppinglist.rest.service.implementations.PlannedOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/plannedorder")
public class PlannedOrderController extends RestController {

    private final PlannedOrderServiceImpl plannedOrderService;

    @Autowired
    public PlannedOrderController(PlannedOrderServiceImpl plannedOrderService, PlannedOrderServiceImpl plannedOrderService1) {
        super(plannedOrderService);
        this.plannedOrderService = plannedOrderService1;
    }

    /**
     * Получить список всех шаблонов для пользоваля
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "user")
    @ResponseBody
    public List<PlannedOrderDto> findOPlannedOrdersByUserId(@RequestParam(value = "userId") String userId) {
        return plannedOrderService.findOPlannedOrdersByUserId(userId);
    }

    /**
     * Создать шаблон для пользоватедя
     */
    @PostMapping(value = "create")
    public void createOrder(@RequestBody PlannedOrderDto plannedOrderDto,
                            @RequestParam(value = "userId") String userId) {
        plannedOrderService.createPlannedOrder(plannedOrderDto, userId);
    }

    /**
     * Заплонировать список покупок на основе существующего
     * Приедщий список игнорируется
     */
    @PostMapping(value = "createbyorder")
    public void createOrderByOrder(@RequestBody PlannedOrderDto plannedOrderDto,
                                   @RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "orderId") String orderId) {
        plannedOrderService.createPlannedOrderByOrder(plannedOrderDto, userId, orderId);
    }
}
