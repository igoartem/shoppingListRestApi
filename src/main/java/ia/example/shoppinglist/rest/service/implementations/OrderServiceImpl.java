package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.exeption.EntityNotFoundException;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.UserRepository;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.OrderService;
import ia.example.shoppinglist.rest.service.UniversalMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(UniversalMapper universalMapper, OrderRepository orderRepository, UserRepository userRepository) {
        super(universalMapper, Order.class, OrderDto.class);
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDto> findOrdersByUserId(String userId, Boolean activate, String name) {
        List<Order> orders = orderRepository.findOrderByUserId(userId, activate);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add((OrderDto) universalMapper.toDto(order, OrderDto.class)));
        return orderDtos;
    }

    public void createOrder(OrderDto orderDto, Boolean activate, String userId) throws BindException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new IllegalArgumentException("Object order is null");
        }
        Order order = (Order) universalMapper.toEntity(orderDto, Order.class);
        order.setActual(activate);
        order.setCreateDate(LocalDateTime.now());
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BindException(new Object(), "User not found");
        }
        order.setUserId(user.getId());
        orderRepository.save(order);

    }

    public void updateOrderDto(OrderDto orderDto, String userId, Boolean activate) throws EntityNotFoundException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new IllegalArgumentException("Object order is null");
        }
        Order oldOrder = orderRepository.findOne(orderDto.getId());
        if (ObjectUtils.isEmpty(oldOrder)) {
            throw new EntityNotFoundException(Order.class, null, oldOrder.getId());
        }
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("Object user is null");
        }
        //Проверим что у задачи тот же пользователь
        if (!oldOrder.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Object user uncorrected");
        }
        Order newOrder = (Order) universalMapper.toEntity(orderDto, Order.class);
        newOrder.setActual(!ObjectUtils.isEmpty(activate) ? activate : false);
        newOrder.setUserId(userId);
        orderRepository.save(newOrder);
    }

    public void createEntryOrder(String userId, String orderId, EntryOrderDto entryOrderDto) {
        updateEntryOrder(true, userId, orderId, entryOrderDto);
    }

    public void updateEntryOrder(String userId, String orderId, EntryOrderDto entryOrderDto) {
        updateEntryOrder(false, userId, orderId, entryOrderDto);
    }

    private void updateEntryOrder(Boolean create, String userId, String orderId, EntryOrderDto entryOrderDto) {
        log.debug("Run updateEntryOrder with create: {}", create);
        //получим спикок и обновим его
        Order order = orderRepository.findOne(orderId);
        if (ObjectUtils.isEmpty(order)) {
            throw new IllegalArgumentException("Object order is null");
        }
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("Object user is null");
        }
        EntryOrder entryOrder = (EntryOrder) universalMapper.toEntity(entryOrderDto, EntryOrder.class);
        if (!create) {
            if (StringUtils.isEmpty(entryOrderDto.getId())) {
                throw new IllegalArgumentException("EntryOrderId is null");
            }
            Optional<EntryOrder> optionalEntryOrder = order.getEntryOrders().stream().
                    filter(p -> p.getId().equals(entryOrder.getId())).
                    findFirst();
            if (!optionalEntryOrder.isPresent()) {
                throw new IllegalArgumentException("Entry order not found");
            }
            order.getEntryOrders().remove(optionalEntryOrder.get());
        }
        order.getEntryOrders().add(entryOrder);
        orderRepository.save(order);
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) orderRepository;
    }
}
