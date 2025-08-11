package tech.build.customerconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.build.customerconnect.controller.dto.ApiResponse;
import tech.build.customerconnect.controller.dto.CreateCustomerDto;
import tech.build.customerconnect.controller.dto.PaginationResponse;
import tech.build.customerconnect.controller.dto.UpdateCustomerDto;
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

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(name = "orderBy", defaultValue = "desc") String orderBy,
                                                               @RequestParam(name = "cpf", required = false) String cpf,
                                                               @RequestParam(name = "email", required = false) String email) {

        var pageResp = customerService.findAll(page, pageSize, orderBy, cpf, email);
        return ResponseEntity.ok(new ApiResponse<>(
                pageResp.getContent(),
                new PaginationResponse(pageResp.getNumber(), pageResp.getSize(), pageResp.getTotalElements(), pageResp.getTotalPages()
        )));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable Long customerId) {

        var user = customerService.findById(customerId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerDto dto) {

        var user = customerService.updateCustomer(customerId, dto);
        if (user.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long customerId) {

        var deleted = customerService.deleteById(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
