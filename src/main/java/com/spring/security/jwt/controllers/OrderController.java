package com.spring.security.jwt.controllers;


import com.spring.security.jwt.customresponse.CustomResponse;
import com.spring.security.jwt.dto.OrderDto;
import com.spring.security.jwt.exception.OrderNotFoundException;
import com.spring.security.jwt.models.Order;
import com.spring.security.jwt.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private String code;

    private Object data;

    @Autowired
    private OrderService iOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @SuppressWarnings("finally")
    @GetMapping
    public ResponseEntity<Object> getAllOrder() {
        try {
            List<Order> list = iOrderService.getAllOrder();

            data = list;
            code = "SUCCESS";
        } catch (Exception e) {
            code = "EXCEPTION"; data = null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    @SuppressWarnings("finally")
    @PostMapping
    public ResponseEntity<Object> placeOrder(@Valid @RequestBody OrderDto OrderDto) {
        try {
            Order DtoToEntity = modelMapper.map(OrderDto, Order.class);
            Order orderEntity = iOrderService.createOrder(DtoToEntity);
            OrderDto ordertoDto = modelMapper.map(orderEntity, OrderDto.class);
            data = ordertoDto;
            code = "CREATED";
        } catch (OrderNotFoundException orderNotFoundException) {
            code = "DATA_NOT_CREATED"; data = null;
        } catch (RuntimeException order) {
            code = "RUNTIME_EXCEPTION"; data = null;
        } catch (Exception e) {
            code = "EXCEPTION"; data = null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    @SuppressWarnings("finally")
    @GetMapping("{orderNumber}")
    public ResponseEntity<Object> showOrderById(@PathVariable Integer orderNumber) {
        try {
            Order orderFound = iOrderService.findOrderById(orderNumber);

            data = orderFound;
            code = "SUCCESS";
        } catch (OrderNotFoundException orderNotFoundException) {
            data = null;
            code = "ORDER_NOT_FOUND_ERROR";
        } catch (RuntimeException r) {
            data = null;
            code = "RUNTIME_EXCEPTION";
        } catch (Exception e) {
            data = null;
            code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    @SuppressWarnings("finally")
    @DeleteMapping("{orderNumber}")
    public ResponseEntity<Object> deleteOrder(@PathVariable @Valid Integer orderNumber) {
        try {
            String deletedOrder = iOrderService.deleteOrder(orderNumber);
            data = deletedOrder;
            code = "SUCCESS";
        } catch (OrderNotFoundException e) {
            data = null;
            code = "ORDER_NOT_FOUND_ERROR";
        } catch (RuntimeException runtimeException) {
            data = null;
            code = "RUNTIME_EXCEPTION";
        } catch (Exception e) {
            data = null;
            code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    @SuppressWarnings("finally")
    @PatchMapping("/updateShippingDate/{orderNumber}")
    public ResponseEntity<Object> updateShippingDate(@Valid @PathVariable Integer orderNumber,
                                                     @RequestBody @Valid Order order) {
        try {
            Order updatedDate = iOrderService.updateShippingDate(orderNumber, order);
            data = updatedDate;
            code = "SUCCESS";
        } catch (OrderNotFoundException e) {
            data = null;
            code = "ORDER_NOT_FOUND_ERROR";
        } catch (RuntimeException runtimeException) {
            data = null;
            code = "RUNTIME_EXCEPTION";
        } catch (Exception r) {
            data = null;
            code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    @SuppressWarnings("finally")
    @PatchMapping("/updateStatus/{orderNumber}")
    public ResponseEntity<Object> updateStatus(@PathVariable @Valid Integer orderNumber,
                                               @RequestBody @Valid Order order) {
        try {
            Order updatedStatus = iOrderService.updateOrderStatus(orderNumber, order);
            data = updatedStatus;
            code = "SUCCESS";
        } catch (OrderNotFoundException orderNotFoundException) {
            data = null;
            code = "ORDER_NOT_FOUND_ERROR";
        } catch (RuntimeException runtimeException) {
            data = null;
            code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data = null;
            code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }
}