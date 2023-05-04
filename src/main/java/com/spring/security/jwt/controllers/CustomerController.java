package com.spring.security.jwt.controllers;

import com.spring.security.jwt.customresponse.CustomResponse;
import com.spring.security.jwt.exception.CustomerNotFoundException;
import com.spring.security.jwt.models.Customer;
import com.spring.security.jwt.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private String code;

    private Object data;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper mapper;

    // ******************************* Register Customer*****************************************************************************//

    // @SuppressWarnings("finally")
//    @PostMapping
//    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
//        try {
//            Customer customer1 = customerService.registerCustomer(customer);
//            data = customer1;
//            code = "CREATED";
//        } catch (CustomerNotFoundException customerNotFoundException) {
//            code = "DATA_NOT_CREATED";data =null;
//        } 	catch (RuntimeException runtimeException) {
//            code = "RUNTIME_EXCEPTION";data =null;
//        } catch (Exception exception) {
//            code = "EXCEPTION";data =null;
//        } finally {
//            return CustomResponse.response(code, data);
//        }
//    }

    // ******************************** Get All Customer***************************************************************************//
    @SuppressWarnings("finally")
    @GetMapping
    public ResponseEntity<?> getCustomerList() {
        try {
            List<Customer> customers = customerService.getCustomerList();

            data = customers;
            code = "SUCCESS";
        } catch (CustomerNotFoundException customerNotFoundException) {
            code = "DATA_NOT_FOUND"; data =null;
        } catch (RuntimeException runtimeException) {
            code = "RUNTIME_EXCEPTION"; data =null;
        } catch (Exception exception) {
            code = "EXCEPTION"; data =null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    // ******************************** Get customer By ID*************************************************************************//
    @SuppressWarnings("finally")
    @GetMapping("{customerNumber}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerNumber) {
        try {

            Customer customer = customerService.getCustomerById(customerNumber);
            data = customer;

            code = "SUCCESS";
        } catch (CustomerNotFoundException customerNotFoundException) {
            data=null;  code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data=null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data=null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    // *********************************Delete Customer By ID******************************************************************//
    @SuppressWarnings("finally")
    @DeleteMapping("{customerNumber}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable Long customerNumber) {

        try {
            String customer = customerService.deleteCustomer(customerNumber);
            data = customer;
            code = "SUCCESS";
        } catch (CustomerNotFoundException customerNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }
    //******************************* Edit or Update CustomerDetails************************************************************//
    @SuppressWarnings("finally")
    @PutMapping("{customerNumber}")
    public ResponseEntity<?> updateCustomer(@Valid @PathVariable Long customerNumber,
                                            @RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.updateCustomerDetail(customerNumber, customer);
            data = customer1;
            code = "CREATED";
        } catch (CustomerNotFoundException customerNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null;  code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }
}