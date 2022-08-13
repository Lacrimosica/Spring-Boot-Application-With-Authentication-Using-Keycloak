package com.lacrimosica.controllers;

import com.lacrimosica.keycloak.Order;
import com.lacrimosica.keycloak.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/orders")
    ResponseEntity<List<Order>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<Order> one(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(id));
    }

    @PostMapping("/orders")
    ResponseEntity<Order> newOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(order));
    }

    @PostMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        if(orderService.cancelOrder(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(id));
        }else{
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("an Order in " + order.getStatus() + " status can not be cancelled");
        }
    }

    @PutMapping("/orders/{id}/update")
    ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order order) {
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(orderService.updateOrder(id, order));
    }
    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        if(orderService.completeOrder(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(id));
        }else{
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("an Order in " + order.getStatus() + " status can not be cancelled");
        }
    }


    @DeleteMapping("/orders/{id}/delete")
    ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}