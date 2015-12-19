package org.handy.manmadeprototype.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by zhongming on 15/12/19.
 */
public class SiteAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LogManager.getLogger(SiteAuthenticationProvider.class);

    @Autowired
    private SiteUserService siteUserService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
        String username = token.getName();

        // 从数据库找到用户信息
        UserDetails userDetails = null;
        if(username != null) {
            userDetails = siteUserService.loadUserByUsername(username);
        }

        if(userDetails == null) {
            throw new UsernameNotFoundException("用户名/密码无效");
        } else if(!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if(!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if(!userDetails.isAccountNonLocked()) {
            throw new LockedException("账号已被锁");
        } else if(!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }

        // 从数据库中获取的密码
        String pwdInDB = userDetails.getPassword();

        // 输入的密码
        String rawPwd = (String)token.getCredentials();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 核对密码
        if(!passwordEncoder.matches(rawPwd, pwdInDB)) {
            throw new BadCredentialsException("密码不正确");
        }

        // 访问授权
        return new UsernamePasswordAuthenticationToken(userDetails, pwdInDB, userDetails.getAuthorities());
    }

    public boolean supports(Class<?> authentication){

        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
