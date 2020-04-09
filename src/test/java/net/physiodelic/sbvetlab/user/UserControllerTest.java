package net.physiodelic.sbvetlab.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.physiodelic.sbvetlab.config.UtilConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Import(UtilConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private IUserService userService;

  private User user = User.builder()
      .adminUser(false)
      .userName("johnDoe")
      .firstName("John")
      .lastName("Doe")
      .eMail("john.doe@mail.com")
      .id(1234567L)
      .build();

  @Test
  void getUsersOk() throws Exception {

    given(userService.list(null)).willReturn(Collections.singletonList(user));

    mockMvc.perform(get("/users")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$.[0].first_name").value(user.getFirstName()))
        .andExpect(jsonPath("$.[0].last_name").value(user.getLastName()))
        .andExpect(jsonPath("$.[0].user_name").value(user.getUserName()))
        .andExpect(jsonPath("$.[0].email").value(user.getEMail()))
        .andExpect(jsonPath("$.[0].id").value(user.getId()))
        .andExpect(jsonPath("$.[0].admin_user").value(user.getAdminUser()));

    verify(userService).list(null);
  }

  @Test
  void userByUserNameOk() throws Exception {
    given(userService.getByUserName(eq("johnDoe"))).willReturn(user);

    mockMvc.perform(get("/users/username/johnDoe")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(7))
        .andExpect(jsonPath("$.first_name").value(user.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(user.getLastName()))
        .andExpect(jsonPath("$.user_name").value(user.getUserName()))
        .andExpect(jsonPath("$.email").value(user.getEMail()))
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.admin_user").value(user.getAdminUser()));

    verify(userService).getByUserName(eq("johnDoe"));
  }

  @Test
  void userByUserNameNotFound() throws Exception {
    given(userService.getByUserName(eq("johnDoe"))).willReturn(null);

    mockMvc.perform(get("/users/username/johnDoe")).andExpect(status().isNotFound());

    verify(userService).getByUserName(eq("johnDoe"));
  }

  @Test
  void userByIdOk() throws Exception {
    given(userService.get(eq(3L))).willReturn(user);

    mockMvc.perform(get("/users/3")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(7))
        .andExpect(jsonPath("$.first_name").value(user.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(user.getLastName()))
        .andExpect(jsonPath("$.user_name").value(user.getUserName()))
        .andExpect(jsonPath("$.email").value(user.getEMail()))
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.admin_user").value(user.getAdminUser()));

    verify(userService).get(eq(3L));
  }

  @Test
  void userByIdNotFound() throws Exception {
    given(userService.get(eq(4L))).willReturn(null);

    mockMvc.perform(get("/users/4")).andExpect(status().isNotFound());

    verify(userService).get(eq(4L));
  }

  @Test
  void createUserIsCreated() throws Exception {
    given(userService.create(any(User.class))).willReturn(user);
    byte[] body = objectMapper.writeValueAsBytes(user);

    mockMvc.perform(post("/users").content(body).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(7))
        .andExpect(jsonPath("$.first_name").value(user.getFirstName()))
        .andExpect(jsonPath("$.last_name").value(user.getLastName()))
        .andExpect(jsonPath("$.user_name").value(user.getUserName()))
        .andExpect(jsonPath("$.email").value(user.getEMail()))
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.admin_user").value(user.getAdminUser()));

    verify(userService).create(any(User.class));
  }
}