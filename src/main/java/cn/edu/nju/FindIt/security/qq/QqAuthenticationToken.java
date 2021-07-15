package cn.edu.nju.FindIt.security.qq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class QqAuthenticationToken extends AbstractAuthenticationToken {
    private String openid;
    private String sessionKey;
    private String NJUID;
    private String signature;
    private Long id;

    public QqAuthenticationToken(String openid, String sessionKey, String NJUID, String signature) {
        super(null);
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.NJUID = NJUID;
        this.signature = signature;
    }

    public QqAuthenticationToken(String NJUID, String sessionKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.NJUID = NJUID;
        this.sessionKey = sessionKey;
        super.setAuthenticated(true);
    }

    public QqAuthenticationToken(Long id, String NJUID, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.NJUID = NJUID;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.NJUID;
    }

    public Object getPrincipal() {
        return this.sessionKey;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }


}
