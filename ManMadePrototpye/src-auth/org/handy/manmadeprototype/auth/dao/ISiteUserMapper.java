package org.handy.manmadeprototype.auth.dao;

import org.handy.manmadeprototype.auth.model.SiteUser;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhongming on 15/12/19.
 */

@Repository
public interface ISiteUserMapper {

    /**
     * 查询用户信息
     * @param id 用户id
     * @param username 用户名
     * @return 网站用户详情列表
     */
    List<SiteUser> get(@Param("id") long id, @Param("username") String username);

    Integer insert(@Param("username") String username, @Param("password") String password);

    Integer update(
            @Param("id") Long id,
            @Param("username") String username,
            @Param("password") String password,
            @Param("authorities") String authorities,
            @Param("enabled") Boolean enabled
    );
}
