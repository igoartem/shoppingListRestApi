package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.implementations.PlannedOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Создать задачу для планирования покупки на основе списка
     */
    public void createPlannedTaskBasedOrder(OrderDto order, String userId) {

    }

    /**
     * Получить список всех шаблонов для пользоваля
     */

}
