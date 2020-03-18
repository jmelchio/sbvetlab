package net.physiodelic.sbvetlab.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
  private ICustomerService customerService;

  @Autowired
  public CustomerController(ICustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("")
  public List<Customer> getCustomers() {
    return customerService.list(null);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    Customer customer = customerService.get(id);
    if (customer == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(customer, HttpStatus.OK);
    }
  }

  @GetMapping("/customername/{customerName}")
  public ResponseEntity<Customer> getCustomerByName(@PathVariable String customerName) {
    Customer customer = customerService.getByCustomerName(customerName);
    if (customer == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(customer, HttpStatus.OK);
    }
  }

  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer newCustomer = customerService.create(customer);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }
}
