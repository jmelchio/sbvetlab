package net.physiodelic.sbvetlab.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.physiodelic.sbvetlab.config.UtilConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(UtilConfig.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ICustomerService customerService;

  private final Customer customer = Customer.builder()
      .customerName("johnDoe")
      .firstName("John")
      .lastName("Doe")
      .eMail("johnDoe@mail.com")
      .id(123456L)
      .build();

  @Test
  void getCustomersOk() throws Exception {

    given(customerService.list(null)).willReturn(Collections.singletonList(customer));

    mockMvc.perform(get("/customers")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$.[0].first_name").value(customer.getFirstName()))
        .andExpect(jsonPath("$.[0].last_name").value(customer.getLastName()))
        .andExpect(jsonPath("$.[0].customer_name").value(customer.getCustomerName()))
        .andExpect(jsonPath("$.[0].email").value(customer.getEMail()))
        .andExpect(jsonPath("$.[0].id").value(customer.getId()));

    verify(customerService).list(null);
  }

  @Test
  void customerByCustomerNameOk() throws Exception {
    given(customerService.getByCustomerName(anyString())).willReturn(customer);

    mockMvc.perform(MockMvcRequestBuilders.get("/customers/customername/johnDoe"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(6))
        .andExpect(jsonPath("$.first_name").value(customer.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(customer.getLastName()))
        .andExpect(jsonPath("$.customer_name").value(customer.getCustomerName()))
        .andExpect(jsonPath("$.email").value(customer.getEMail()))
        .andExpect(jsonPath("$.id").value(customer.getId()));

    verify(customerService).getByCustomerName(eq("johnDoe"));
  }

  @Test
  void customerByCustomerNameNotFound() throws Exception {
    given(customerService.getByCustomerName(eq("johnDoe"))).willReturn(null);

    mockMvc.perform(get("/customers/customername/johnDoe")).andExpect(status().isNotFound());

    verify(customerService).getByCustomerName(eq("johnDoe"));
  }

  @Test
  void customerByIdOk() throws Exception {
    given(customerService.get(eq(1L))).willReturn(customer);

    mockMvc.perform(get("/customers/1")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(6))
        .andExpect(jsonPath("$.first_name").value(customer.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(customer.getLastName()))
        .andExpect(jsonPath("$.customer_name").value(customer.getCustomerName()))
        .andExpect(jsonPath("$.email").value(customer.getEMail()))
        .andExpect(jsonPath("$.id").value(customer.getId()));

    verify(customerService).get(eq(1L));
  }

  @Test
  void customerByIdNotFound() throws Exception {
    given(customerService.get(eq(2L))).willReturn(null);

    mockMvc.perform(get("/customers/2")).andExpect(status().isNotFound());

    verify(customerService).get(eq(2L));
  }

  @Test
  void createCustomerIsCreated() throws Exception {
    given(customerService.create(any(Customer.class))).willReturn(customer);
    byte[] body = objectMapper.writeValueAsBytes(customer);

    mockMvc.perform(post("/customers").content(body).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(6))
        .andExpect(jsonPath("$.first_name").value(customer.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(customer.getLastName()))
        .andExpect(jsonPath("$.customer_name").value(customer.getCustomerName()))
        .andExpect(jsonPath("$.email").value(customer.getEMail()))
        .andExpect(jsonPath("$.id").value(customer.getId()));

    verify(customerService).create(any(Customer.class));
  }
}