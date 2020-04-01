package net.physiodelic.sbvetlab.customer;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ICustomerServiceTestConfig {
  @Bean
  public CustomerRepository customerRepository() {
    return mock(CustomerRepository.class);
  }

  @Bean
  public ICustomerService customerService(CustomerRepository customerRepository) {
    return new CustomerService(customerRepository);
  }
}
