package cn.edu.nju.FindIt.service;

import cn.edu.nju.FindIt.model.User;

public interface UserService {
    User getUserByNJUID(String NJUID);
}
