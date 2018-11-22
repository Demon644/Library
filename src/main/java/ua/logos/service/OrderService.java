package ua.logos.service;

import ua.logos.domain.OrderDTO;

import java.util.List;

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

}
