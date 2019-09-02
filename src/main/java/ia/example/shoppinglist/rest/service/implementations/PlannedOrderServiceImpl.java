package ia.example.shoppinglist.rest.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;
import ia.example.shoppinglist.repositories.UserRepository;
import ia.example.shoppinglist.rest.dto.PlannedOrderDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;

@Service
public class PlannedOrderServiceImpl extends AbstractServiceImpl {

    private final PlannedOrderRepository plannedOrderRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PlannedOrderServiceImpl(UniversalMapper universalMapper, PlannedOrderRepository plannedOrderRepository, UserRepository userRepository,
            OrderRepository orderRepository) {
        super(universalMapper, PlannedOrder.class, PlannedOrderDto.class);
        this.plannedOrderRepository = plannedOrderRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) plannedOrderRepository;
    }

    public List<PlannedOrderDto> findOPlannedOrdersByUserId(String userId) {
        List<PlannedOrder> plannedOrders = plannedOrderRepository.findPlannedOrderByUserId(userId);
        List<PlannedOrderDto> plannedOrderDtos = new ArrayList<>();
        plannedOrders.forEach(order -> plannedOrderDtos.add((PlannedOrderDto) super.universalMapper.toDto(order, PlannedOrderDto.class)));
        return plannedOrderDtos;
    }

    public void createPlannedOrder(PlannedOrderDto orderDto, String userId) {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new IllegalArgumentException("Object order is null");
        }
        PlannedOrder plannedOrder = (PlannedOrder) universalMapper.toEntity(orderDto, PlannedOrder.class);
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("Object user is null");
        }
        plannedOrder.setUserId(user.getId());
        plannedOrderRepository.save(plannedOrder);

    }

    public void createPlannedOrderByOrder(PlannedOrderDto orderDto, String userId, String orderId) {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new IllegalArgumentException("Object order is null");
        }
        PlannedOrder plannedOrder = (PlannedOrder) universalMapper.toEntity(orderDto, PlannedOrder.class);
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("Object user is null");
        }
        Order order = orderRepository.findOne(orderId);
        if (ObjectUtils.isEmpty(order)) {
            throw new IllegalArgumentException("Object orderId not found");
        }
        plannedOrder.setOrder(order);
        plannedOrder.setUserId(user.getId());
        plannedOrderRepository.save(plannedOrder);
    }
}
