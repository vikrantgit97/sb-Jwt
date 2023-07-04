package com.spring.security.jwt.serviceimpl;
import com.spring.security.jwt.exception.OrderNotFoundException;
import com.spring.security.jwt.exception.ProductNotFoundException;
import com.spring.security.jwt.models.Order;
import com.spring.security.jwt.models.OrderDetails;
import com.spring.security.jwt.models.Product;
import com.spring.security.jwt.repository.OrderDetailsRepository;
import com.spring.security.jwt.repository.OrderRepository;
import com.spring.security.jwt.service.OrderService;
import com.spring.security.jwt.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Order> getAllOrder() {
        if (orderRepo.count() != 0) {
            return orderRepo.findAll();
        } else {
            throw new OrderNotFoundException(" Orders not found ");
        }
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        order.setComments(order.getComments());
        orderRepo.save(order);
        List<OrderDetails> orderDetailsDtoList = order.getOrderDetails();
        List<Integer> pid = orderDetailsDtoList.stream().map(OrderDetails::getProductCode).collect(Collectors.toList());
        List<Product> products = productService.getlistbyproductCode(pid);
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductCode, product -> product));
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetails orderDetailsDto : orderDetailsDtoList) {
            Product product = productMap.get(orderDetailsDto.getProductCode());
            if (product.getQuantityInStock() < orderDetailsDto.getQuantityOrdered()) {
                throw new ProductNotFoundException("Product is out of stock");
            }

            productService.updateProductQuantityInStock(product.getProductCode(), orderDetailsDto.getQuantityOrdered());
            OrderDetails orderDetails = modelMapper.map(orderDetailsDtoList, OrderDetails.class);
            orderDetails.setQuantityOrdered(orderDetailsDto.getQuantityOrdered());
            orderDetails.setPriceEach(product.getPrice());
            //orderDetails.setProduct(product);
            //orderDetails.setOrder(order);
            orderDetails.setProductCode(Integer.valueOf(product.getProductCode()));
            orderDetails.setOrderNumber(order.getOrderNumber());
            orderDetailsList.add(orderDetails);
        }

        orderDetailsRepo.saveAll(orderDetailsList);
        // order.setOrderDetails(orderDetailsList);
        orderRepo.save(order);
        return order;
    }

    @Override
    public Order findOrderById( Integer orderNumber) {
        return orderRepo.findById(orderNumber).orElseThrow(() -> new OrderNotFoundException("Order does not exist"));
    }

    @Override
    public String deleteOrder(Integer orderNumber) {
        Optional<Order> OrederToBeDeleted = orderRepo.findById(orderNumber);
        if (OrederToBeDeleted.isPresent()) {
            orderRepo.deleteById(orderNumber);
            return "Order deleted successfully";
        } else {
            throw new OrderNotFoundException("Order not found with Id" + orderNumber);
        }
    }

    @Override
    public Order updateShippingDate(Integer orderNumber,  Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            Order order2 = foundOrder.get();
            order2.setShippedDate(order.getShippedDate());
            return orderRepo.save(order2);
        } else {
            throw new OrderNotFoundException("Shipping Date not found for order number " + orderNumber);
        }
    }

    @Override
    public Order updateOrderStatus(Integer orderNumber, Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            order.setStatus(order.getStatus());
            return orderRepo.save(order);
        } else {
            throw new OrderNotFoundException("Comment of given order could not be updated");
        }
    }

    @Override
    public Order updateComment(Integer orderNumber, Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            Order order2 = foundOrder.get();
            order2.setComments(order.getComments());
            return orderRepo.save(order2);
        } else {
            throw new OrderNotFoundException("Shipping Date not found for order number " + orderNumber);
        }
    }
}
