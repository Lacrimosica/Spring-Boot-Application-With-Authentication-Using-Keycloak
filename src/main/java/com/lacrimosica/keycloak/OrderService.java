package com.lacrimosica.keycloak;

import com.lacrimosica.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lacrimosica.repositories.OrderRepository;
import com.lacrimosica.statuses.OrderStatus;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @PostConstruct
    public void init(){
        orderRepository.saveAll(
                Stream.of(
                        (new Order("Order 1")),
                        (new Order("Order 2")),
                        (new Order("Order 3")),
                        (new Order("Order 4")),
                        (new Order("Order 5")),
                        (new Order("Order 6"))
                ).collect(Collectors.toList()));

    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order addOrder(Order order){
        order.setStatus(OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    public boolean checkOrderStatus(OrderStatus orderStatus, Long id){
        return orderRepository.findById(id).map(order ->{
            return order.getStatus() == orderStatus;
        }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order completeOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        if(checkOrderStatus(OrderStatus.IN_PROGRESS, id)) {
            order.setStatus(OrderStatus.COMPLETED);
            return orderRepository.save(order);
        }else{
            return null;
        }
    }
    public Order cancelOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        if(checkOrderStatus(OrderStatus.IN_PROGRESS, id)) {
            order.setStatus(OrderStatus.CANCELLED);
            return orderRepository.save(order);
        }
        return null;
    }
    public Order updateOrder(Long id, Order order){
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        existingOrder.setDescription(order.getDescription());
        existingOrder.setStatus(order.getStatus());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }

}
