package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.UserRepository;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.OrderService;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {

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
        List<OrderDto> orderDtos = Collections.emptyList();
        orders.stream().forEach(order -> {
            orderDtos.add((OrderDto) super.universalMapper.toDto(order, OrderDto.class));
        });
        return orderDtos;
    }

    public void createOrder(OrderDto orderDto, Boolean activate, String userId) throws BindException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new BindException(new Object(), "qq11");
        }
        Order order = (Order) universalMapper.toEntity(orderDto, Order.class);
        order.setActual(activate);
        order.setCreateDate(LocalDateTime.now());
        User user = userRepository.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new BindException(new Object(), "QQQ");
        }
        order.setUserId(user.getId());
        orderRepository.save(order);

    }

    public void updateOrderDto(OrderDto orderDto, String userId, Boolean activate) throws BindException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new BindException(new Object(), "qqqq");
        }
        Order oldOrder = orderRepository.findOne(orderDto.getId());
        if (ObjectUtils.isEmpty(oldOrder)) {
            throw new BindException(new Object(), "asdasf");
        }
        Order newOrder = (Order) universalMapper.toEntity(orderDto, Order.class);
        newOrder.setActual(!ObjectUtils.isEmpty(activate) ? activate : false);
//        Проверка что пользователь существвет?
//       User user = userRepository.findOne(userId);
        //Проверим что у задачи тот же пользователь
        newOrder.setUserId(userId);
        orderRepository.save(newOrder);

    }

    //обновляем только существющие объекты?
    public void updateEntryOrder(String userId, String orderId, EntryOrderDto[] entryOrderDtos) {
        //получим спикок и обновим его

    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) orderRepository;
    }
}
