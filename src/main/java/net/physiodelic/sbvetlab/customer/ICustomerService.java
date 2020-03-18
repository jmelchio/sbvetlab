package net.physiodelic.sbvetlab.customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
  Customer login(String customerName, String password);
  void update(Customer customer);
  Customer create(Customer customer);
  void delete(Customer customer);
  Customer get(Long customerId);
  List<Customer> list(Map<String, Object> filter);
  Customer getByCustomerName(String name);
}
