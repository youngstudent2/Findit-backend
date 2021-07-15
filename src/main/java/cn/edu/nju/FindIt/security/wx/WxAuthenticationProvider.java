package cn.edu.nju.FindIt.security.wx;

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
public class WxAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WxAuthenticationToken wxAppletAuthenticationToken = null;
        if (authentication instanceof WxAuthenticationToken) {
            wxAppletAuthenticationToken = (WxAuthenticationToken) authentication;
        }
        User user = authService.findUserByNJUID(wxAppletAuthenticationToken.getNJUID());
        //执行注册逻辑
        if (user == null) {
            log.debug("user not exist, began to register. openid is [{}]", wxAppletAuthenticationToken.getOpenid());
            user = new User();
            user.setNJUID(wxAppletAuthenticationToken.getNJUID());
            user.setWxOpenId(wxAppletAuthenticationToken.getOpenid());
            user.setWxSessionKey(wxAppletAuthenticationToken.getSessionKey());
            user.setQqOpenId("");
            user.setQqSessionKey("");

            log.info("user is [{}]", user);
            user = authService.registerUser(user);
        }
        else if(user.getWxOpenId().isEmpty()){
            log.debug("user exist, began to register for wx. openid is [{}]", wxAppletAuthenticationToken.getOpenid());
            user.setWxOpenId(wxAppletAuthenticationToken.getOpenid());
            user.setWxSessionKey(wxAppletAuthenticationToken.getSessionKey());

            log.info("user is [{}]", user);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        user.getRole();
        user.getRole().getName();
        new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new WxAuthenticationToken(user.getId(), user.getNJUID(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
