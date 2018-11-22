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

    @GetMapping("{categoryId}")
    public ResponseEntity<?> getById(@PathVariable("categoryId") Long id) {
        OrderDTO dto = orderService.findOrderById(id);
        return new ResponseEntity<OrderDTO> (dto, HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> delete(@PathVariable("categoryId") Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
