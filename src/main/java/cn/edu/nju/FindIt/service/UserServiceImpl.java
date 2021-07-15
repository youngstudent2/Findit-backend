package cn.edu.nju.FindIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.FindIt.model.User;
import cn.edu.nju.FindIt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByNJUID(String NJUID) {
        return userRepository.getUserByNJUID(NJUID);
    }
}
