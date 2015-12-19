package org.handy.manmadeprototype.auth.service;

import org.handy.manmadeprototype.auth.dao.ISiteUserMapper;
import org.handy.manmadeprototype.auth.model.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.handy.manmadeprototype.auth.model.SiteUserDetail;

/**
 * Created by zhongming on 15/12/18.
 */

@Service(value = "SiteUserService")
public class SiteUserService implements UserDetailsService {

    private Logger logger = LogManager.getLogger(SiteUserService.class);

    @Autowired
    private ISiteUserMapper siteUserMapper;

    // 用户名密码正则校验
    private Pattern userNamePtn = Pattern.compile("^[a-zA-Z0-9_-]{6,20}$");
    private Pattern passWordPtn = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z].{6,20}))");


    /**
     * 根据用户名加载UserDetails信息
     * UserDetailsService接口方法,用于AuthenticationProvider获取 用户基本信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO

        return null;
    }

    /**
     * 获取当前登录用户详细信息
     * @return
     */
    public SiteUserDetail getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName().equals("anonymousUser")) {
            // TODO 匿名登录

        }else {
            return (SiteUserDetail) auth.getPrincipal();
        }

        return null;
    }

    /**
     * 新用户注册
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public Integer userRregister(String username, String password) throws Exception {

        if(username == null || password == null) {
            throw new Exception("用户名和密码不能为空!");
        }
        if(!validator(username, userNamePtn)){
            throw new Exception("用户名格式不正确!");
        }
        if(!validator(password, passWordPtn)){
            throw new Exception("密码不符合规范!");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        // TODO 将注册信息插入数据库,返回插入状态码
        return 0; // return insert code
    }

    public Integer updateUserInfo(Long id,
                                  String username,
                                  String password,
                                  String authorities,
                                  boolean enabled) throws Exception {
        if(id == null) {
            throw new Exception("用户id不能为空!");
        }
        if(username != null && !validator(username, userNamePtn)) {
            throw new Exception("用户名不符合规范!");
        }
        if(password != null && !validator(password, passWordPtn)) {
            throw new Exception("密码不符合规范!");
        }
        if(username != null && get(null, username).size() != 0) {
            throw new Exception("用户名已被占用!");
        }
        if(password != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            password = passwordEncoder.encode(password);
        }

        return siteUserMapper.update(id, username, password, authorities, enabled);
    }




    /**
     * 获取用户列表
     * @param id   用户id
     * @param name 用户名
     * @return
     */
    public List<SiteUser> get(Long id, String name) {
        return siteUserMapper.get(id, name);
    }


    /**
     * 登录验证函数
     * @param neeCheckStr 待验证字符串
     * @param checkPtn    验证正则表达式
     * @return            返回验证结果
     */
    private boolean validator(String neeCheckStr, Pattern checkPtn) {
        Matcher matcher = checkPtn.matcher(neeCheckStr);
        return matcher.matches();
    }
}
