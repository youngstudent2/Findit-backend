package cn.edu.nju.FindIt.security.qq;

import cn.edu.nju.FindIt.model.User;
import cn.edu.nju.FindIt.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QqAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        QqAuthenticationToken qqAppletAuthenticationToken = null;
        if (authentication instanceof QqAuthenticationToken) {
            qqAppletAuthenticationToken = (QqAuthenticationToken) authentication;
        }
        User user = authService.findUserByNJUID(qqAppletAuthenticationToken.getNJUID());
        //执行注册逻辑
        if (user == null) {
            log.debug("user not exist, began to register. openid is [{}]", qqAppletAuthenticationToken.getOpenid());
            user = new User();
            user.setNJUID(qqAppletAuthenticationToken.getNJUID());
            user.setQqOpenId(qqAppletAuthenticationToken.getOpenid());
            user.setQqSessionKey(qqAppletAuthenticationToken.getSessionKey());
            user.setWxOpenId("");
            user.setWxSessionKey("");

            log.info("user is [{}]", user);
            user = authService.registerUser(user);
        }
        else if(user.getQqOpenId().isEmpty()){
            log.debug("user exist, began to register for wx. openid is [{}]", qqAppletAuthenticationToken.getOpenid());
            user.setQqOpenId(qqAppletAuthenticationToken.getOpenid());
            user.setQqSessionKey(qqAppletAuthenticationToken.getSessionKey());

            log.info("user is [{}]", user);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        user.getRole();
        user.getRole().getName();
        new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new QqAuthenticationToken(user.getId(), user.getNJUID(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return QqAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
