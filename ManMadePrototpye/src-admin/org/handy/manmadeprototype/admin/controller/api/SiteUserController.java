package org.handy.manmadeprototype.admin.controller.api;

import com.wordnik.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.handy.manmadeprototype.auth.model.SiteUser;
import org.handy.manmadeprototype.auth.service.SiteUserService;
import org.springframework.web.bind.annotation.*;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongming on 15/12/19.
 */

@RestController
@Api("用户管理接口")
@RequestMapping("/admin/api/user")
public class SiteUserController {

    private Logger logger = LogManager.getLogger(SiteUserController.class);

    @Autowired
    private SiteUserService siteUserService;

    @ApiOperation("获取用户列表")
    @RequestMapping(method = RequestMethod.GET)
    public List<SiteUser> getUsers() {
        List<SiteUser> siteUserList = siteUserService.get(null, null); // 参数待确定

        return siteUserList;
    }

    @ApiOperation("根据id查找用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<SiteUser> getUserById(
        @ApiParam(value = "用户名", required = true)
        @PathVariable
        long id) {

        List<SiteUser> siteUserList = siteUserService.get(id, null);
        return siteUserList;
    }

    @ApiOperation("更新用户状态")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public List<SiteUser> updateUser(
            @RequestBody
            Map<String, Object> params
    ) {
        long id = new Long(params.get("id").toString());
        String username = (String)params.get("username");
        String password = (String)params.get("password");
        String authorities = (String)params.get("authorities");
        boolean enabled = (Boolean) params.get("enabled");

        try {
            // TODO
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
