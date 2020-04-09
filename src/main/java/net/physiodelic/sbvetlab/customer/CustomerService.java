package net.physiodelic.sbvetlab.customer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CustomerService implements ICustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer login(String customerName, String password) {
    return null;
  }

  @Override
  public void update(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public Customer create(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public void delete(Customer customer) {
    customerRepository.delete(customer);
  }

  @Override
  public Customer get(Long customerId) {
    return customerRepository.findById(customerId).orElse(null);
  }

  @Override
  public List<Customer> list(Map<String, Object> filter) {
    return StreamSupport
        .stream(customerRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Customer getByCustomerName(String name) {
    return customerRepository.findByCustomerName(name).orElse(null);
  }
}
