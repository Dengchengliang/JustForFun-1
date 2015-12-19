package org.handy.manmadeprototype.auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by zhongming on 15/12/19.
 */
public class SiteUserDetail extends User {

    private long id;

    public SiteUserDetail(String username,
                          String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SiteUserDetail(String username,
                          String password,
                          boolean enabled,
                          boolean accountNoExpired,
                          boolean credentialsNoExpired,
                          boolean accountNoLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, authorities);
    }

    public SiteUserDetail(long id,
                          String username,
                          String password,
                          boolean enabled,
                          boolean accountNoExpired,
                          boolean credentialsNoExpired,
                          boolean accountNoLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNoExpired, credentialsNoExpired, accountNoLocked, authorities);
        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
