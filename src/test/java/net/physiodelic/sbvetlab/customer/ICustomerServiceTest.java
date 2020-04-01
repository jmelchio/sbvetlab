package net.physiodelic.sbvetlab.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ICustomerServiceTestConfig.class})
@DisplayName("Testing the customer service")
class ICustomerServiceTest {
  @Autowired
  private ICustomerService customerServiceWithMockRepo;

  @Autowired
  private CustomerRepository customerMockRepository;

  private Customer customer = Customer.builder()
      .customerName("johnDoe")
      .firstName("John")
      .lastName("Doe")
      .eMail("johnDoe@mail.com")
      .id(123456L)
      .build();


  @Test
  @DisplayName("create returns a new Customer")
  void testCreateOk() {
    Customer newCustomer = Customer.builder()
        .customerName("johnDoe")
        .firstName("John")
        .lastName("Doe")
        .eMail("johnDoe@mail.com")
        .build();

    given(customerMockRepository.save(any(Customer.class))).willReturn(customer);

    Customer result = customerServiceWithMockRepo.create(newCustomer);

    assertThat(result).isEqualTo(customer);

    verify(customerMockRepository).save(eq(newCustomer));
  }
}