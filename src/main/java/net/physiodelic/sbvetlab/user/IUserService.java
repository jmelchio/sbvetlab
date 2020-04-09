package net.physiodelic.sbvetlab.user;

import java.util.List;
import java.util.Map;

public interface IUserService {
  User login(String userName, String password);
  void update(User user);
  User create(User user);
  void delete(User user);
  User get(Long userId);
  List<User> list(Map<String, Object> filter);
  User getByUserName(String name);
}
