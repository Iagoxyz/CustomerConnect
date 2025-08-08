package tech.build.customerconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.build.customerconnect.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
