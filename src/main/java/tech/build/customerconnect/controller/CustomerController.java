package tech.build.customerconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.build.customerconnect.controller.dto.CreateCustomerDto;
import tech.build.customerconnect.entity.CustomerEntity;
import tech.build.customerconnect.service.CustomerService;

import java.net.URI;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> createdCustomer(@RequestBody CreateCustomerDto dto) {
        var customer = customerService.createCustomer(dto);
        return ResponseEntity.created(URI.create("/customers/" + customer.getCustomerId())).build();
    }
}
