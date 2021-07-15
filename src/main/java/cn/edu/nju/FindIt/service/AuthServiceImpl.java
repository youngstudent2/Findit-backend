package cn.edu.nju.FindIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.FindIt.model.User;
import cn.edu.nju.FindIt.repository.RoleRepository;
import cn.edu.nju.FindIt.repository.UserRepository;
import cn.edu.nju.FindIt.utils.enums.ExceptionEnum;
import cn.edu.nju.FindIt.utils.exception.BasicException;

@Service
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByNJUID(String NJUID) {
        return userRepository.getUserByNJUID(NJUID);
    }

    @Override
    public User registerUser(User user) {
        grantDefaultRole(user);
        user = userRepository.save(user);
        if (user.getId() == null){
            throw new BasicException(ExceptionEnum.SERVER_EXCEPTION.customMessage("register failed, account save error"));
        }
        return user;
    }

    private void grantDefaultRole(User user) {
        user.setRole(roleRepository.findByName("user"));
    }
}
