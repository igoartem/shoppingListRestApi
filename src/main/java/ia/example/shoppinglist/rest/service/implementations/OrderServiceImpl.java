package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.OrderService;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(UniversalMapper universalMapper, OrderRepository orderRepository) {
        super(universalMapper, Order.class, OrderDto.class);
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> findOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findOrderByUserId(userId);
        List<OrderDto> orderDtos = Collections.emptyList();
        orders.stream().forEach(order -> {
            orderDtos.add((OrderDto) super.universalMapper.toDto(order, OrderDto.class));
        });
        return orderDtos;
    }

   public void createActualOrder(OrderDto orderDto){
        Order order = (Order) universalMapper.toEntity(orderDto, Order.class);
       orderRepository.save(order);
       String userId = order.getUser().getId();
       setActualOrder(userId, order.getId());
   }

   public void setActualOrder(String orderId, String userId){
        Order order = orderRepository.findOne(orderId);
        order.setActual(true);
        orderRepository.save(order);
   }

   public List<OrderDto> getActualOrderByUserId(String userId){
        List<Order> orders = orderRepository.findActulaOrderByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
       if(!CollectionUtils.isEmpty(orders)) {
           orders.forEach(order -> orderDtos.add((OrderDto) universalMapper.toDto(order, OrderDto.class)));
       }
       return orderDtos;
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) orderRepository;
    }
}
