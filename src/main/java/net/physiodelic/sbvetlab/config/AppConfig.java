package net.physiodelic.sbvetlab.config;

import net.physiodelic.sbvetlab.customer.CustomerRepository;
import net.physiodelic.sbvetlab.customer.ICustomerService;
import net.physiodelic.sbvetlab.customer.CustomerService;
import net.physiodelic.sbvetlab.user.User;
import net.physiodelic.sbvetlab.user.UserRepository;
import net.physiodelic.sbvetlab.user.UserService;
import net.physiodelic.sbvetlab.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

  @Bean
  public ICustomerService customerService(CustomerRepository customerRepository) {
    return new CustomerService(customerRepository);
  }

  @Bean
  CommandLineRunner commandLineRunner(UserRepository userRepository){
    User user = User.builder()
        .adminUser(false)
        .userName("johnDoe")
        .firstName("John")
        .lastName("Doe")
        .eMail("john.doe@mail.com")
        .id(1234567L)
        .build();

    return args -> logger.info("We have inserted user: "
        + userRepository.save(user).toString());
  }
}
