package net.physiodelic.sbvetlab.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserService implements IUserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User login(String userName, String password) {
    return null;
  }

  @Override
  public void update(User user) {
    userRepository.save(user);
  }

  @Override
  public User create(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(User user) {
    userRepository.delete(user);
  }

  @Override
  public User get(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }

  @Override
  public List<User> list(Map<String, Object> filter) {
    return StreamSupport
        .stream(userRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public User getByUserName(String userName) {
    return userRepository.findByUserName(userName).orElse(null);
  }
}
