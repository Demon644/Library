package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.ErrorDTO;
import ua.logos.domain.OrderDTO;
import ua.logos.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OrderDTO dto, BindingResult br) {

        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();

            ErrorDTO error = new ErrorDTO(errMsg);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        orderService.saveOrder(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<OrderDTO> dtos = orderService.findAllOrders();
        return new ResponseEntity<List<OrderDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<?> getById(@PathVariable("orderId") Long id) {
        OrderDTO dto = orderService.findOrderById(id);
        return new ResponseEntity<OrderDTO> (dto, HttpStatus.OK);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> delete(@PathVariable("orderId") Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/by_number/{number}")
    public ResponseEntity<?> getByPhoneNumber(@PathVariable("number") String phoneNumber) {
        List<OrderDTO> byNumberDtos = orderService.findOrderByPhoneNumber(phoneNumber);
        return new ResponseEntity<List<OrderDTO>>(byNumberDtos,HttpStatus.OK);
    }

    @GetMapping("/by_login/{login}")
    public ResponseEntity<?> getByLogin(@PathVariable("login") String login) {
        List<OrderDTO> dtos = orderService.findOrderByLogin(login);
        return new ResponseEntity<List<OrderDTO>>(dtos,HttpStatus.OK);
    }

}
