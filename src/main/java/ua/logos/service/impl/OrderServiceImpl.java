package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.OrderDTO;
import ua.logos.entity.OrderEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.OrderRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.OrderService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils orderMapper;


    @Override
    public void saveOrder(OrderDTO orderDTO) {
        UserEntity user = userRepository.findByLogin(orderDTO.getUser().getLogin()).get();

        OrderEntity entity = orderMapper.map(orderDTO, OrderEntity.class);
        entity.setUsers(user);
        orderRepository.save(entity);
    }

//    @Override
//    public OrderDTO changeBookId(Long id) {
//        return null;
//    }
//
//    @Override
//    public OrderDTO changeOrderToken(Long id) {
//        return null;
//    }
//
//    @Override
//    public OrderDTO changeUserId(Long id) {
//        return null;
//    }

    @Override
    public List<OrderDTO> findAllOrders() {
        List<OrderEntity> entities = orderRepository.findAll();
        List<OrderDTO> dtos = orderMapper.mapAll(entities, OrderDTO.class);
        return dtos;
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record with id[" + id + "] not found")
        );
        OrderDTO dto = orderMapper.map(entity,OrderDTO.class);
        return dto;
    }

    @Override
    public void deleteOrderById(Long id) {
    OrderEntity entity = orderRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Record with id[" + id + "[ not found")
    );
        if(entity != null) {
            orderRepository.deleteById(id);
        }
    }

    @Override
    public List<OrderDTO> findOrderByPhoneNumber(String number) {
        List<OrderEntity> entities = orderRepository.findByPhoneNumber(number);
        List<OrderDTO> dtos = orderMapper.mapAll(entities, OrderDTO.class);
        return dtos;
    }

    @Override
    public List<OrderDTO> findOrderByLogin(String login) {
        List<OrderEntity> entities = orderRepository.findAllByUsersLogin(login);
        List<OrderDTO> dtos = orderMapper.mapAll(entities, OrderDTO.class);
        return dtos;
    }
}
