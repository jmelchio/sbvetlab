package net.physiodelic.sbvetlab.user;

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

@RestController()
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public List<User> getUsers() {
    return userService.list(null);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.get(id);
    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }
  }

  @GetMapping("/username/{userName}")
  public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
    User user = userService.getByUserName(userName);
    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userService.create(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }
}
