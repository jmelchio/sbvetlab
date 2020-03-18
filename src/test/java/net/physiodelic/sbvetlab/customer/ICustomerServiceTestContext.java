package net.physiodelic.sbvetlab.customer;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ICustomerServiceTestContext {
  @MockBean
  public CustomerRepository customerMockRepository;

  @Bean
  public ICustomerService customerServiceWithMockRepo(CustomerRepository customerRepository) {
    return new CustomerService(customerMockRepository);
  }
}
