package tech.build.customerconnect.service;

import org.springframework.stereotype.Service;
import tech.build.customerconnect.controller.dto.CreateCustomerDto;
import tech.build.customerconnect.entity.CustomerEntity;
import tech.build.customerconnect.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerEntity createCustomer(CreateCustomerDto dto) {
        var entity = new CustomerEntity();
        entity.setFullName(dto.fullName());
        entity.setEmail(dto.email());
        entity.setCpf(dto.cpf());
        entity.setPhoneNumber(dto.phoneNumber());

        return customerRepository.save(entity);
    }
}
