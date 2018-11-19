package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.OrderDTO;
import ua.logos.entity.OrderEntity;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.OrderRepository;
import ua.logos.service.OrderService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapperUtils orderMapper;


    @Override
    public void saveOrder(OrderDTO orderDTO) {
        OrderEntity entity = orderMapper.map(orderDTO, OrderEntity.class);
        orderRepository.save(entity);
    }

    @Override
    public OrderDTO changeBookId(Long id) {
        return null;
    }

    @Override
    public OrderDTO changeOrderToken(Long id) {
        return null;
    }

    @Override
    public OrderDTO changeUserId(Long id) {
        return null;
    }

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
}
