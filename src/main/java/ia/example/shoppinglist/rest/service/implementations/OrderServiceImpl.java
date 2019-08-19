package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.rest.dto.OrderDto;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.rest.dto.EntityDto;
import ia.example.shoppinglist.rest.service.OrderService;
import ia.example.shoppinglist.rest.service.UniversalMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(CrudRepository<Entity, String> crudRepository, UniversalMapper universalMapper, OrderRepository orderRepository) {
        super(crudRepository, universalMapper, Order.class, OrderDto.class);
        this.orderRepository = orderRepository;
    }


    public List<OrderDto> findOrderListByUserId(String userId){
        List<Order> orders = orderRepository.findOrderByUserId(userId);
        List<OrderDto> orderDtos = Collections.emptyList();
        orders.stream().forEach(order -> {
            orderDtos.add((OrderDto) super.universalMapper.toDto(order, OrderDto.class));
        });
        return orderDtos;
    }


}
