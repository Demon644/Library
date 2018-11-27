package ua.logos.service;

import ua.logos.domain.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void saveOrder(OrderDTO orderDTO);

//    OrderDTO changeBookId(Long id);
//
//    OrderDTO changeOrderToken(Long id);
//
//    OrderDTO changeUserId(Long id);

    OrderDTO findOrderById(Long id);

    List<OrderDTO> findAllOrders();

    void deleteOrderById(Long id);

    List<OrderDTO> findOrderByPhoneNumber(String number);

    List<OrderDTO> findOrderByLogin(String login);
}
