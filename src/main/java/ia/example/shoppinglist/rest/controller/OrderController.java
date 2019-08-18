package ia.example.shoppinglist.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.BasicService;

@org.springframework.web.bind.annotation.RestController("order")
public class OrderController extends RestController<OrderDto> {

    private BasicService basicService;

    public OrderController(BasicService basicService) {
        super(basicService);
    }

    /**
     * Получить списки покупок для пользователя
     *
     * @return
     */
    public List<OrderDto> findListByUserId() {
        return null;
    }

    /**
     * Получить список для пользователя
     *
     * @return
     */
    public OrderDto findOrderByUserId() {
        return null;
    }

    public void createOrder() {

    }

    /**
     * Сдеалать список актуальным. У всех остальных списков статус становится не актуальным.
     *
     * @param order
     */
    public void makeOrderActual(@RequestBody OrderDto order) {

    }

    /**
     * Получить актуальный список для пользователя
     * @param userId
     * @return
     */
    @GetMapping(value = "a/{id}")
    public OrderDto getActualOrderByUserId(String userId) {
        return null;
    }

    /**
     * Создать список на основе существующего у пользователя
     */
    public void createOrderBasedOrder(String idOrder, String userId){

    }

}
