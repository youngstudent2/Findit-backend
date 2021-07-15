package cn.edu.nju.FindIt.service;

import cn.edu.nju.FindIt.model.User;

public interface AuthService {
    User findUserByNJUID(String NJUID);

    User registerUser(User user);
}
