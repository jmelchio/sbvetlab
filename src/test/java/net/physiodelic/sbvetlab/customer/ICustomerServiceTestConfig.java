package net.physiodelic.sbvetlab.customer;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test"})
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
