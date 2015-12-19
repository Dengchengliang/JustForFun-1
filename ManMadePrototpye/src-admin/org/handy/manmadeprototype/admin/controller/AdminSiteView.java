package org.handy.manmadeprototype.admin.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.handy.manmadeprototype.auth.model.SiteUserDetail;
import org.handy.manmadeprototype.auth.service.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhongming on 15/12/19.
 */

@Controller
@RequestMapping("/amdin")
public class AdminSiteView {

    @Autowired
    private SiteUserService siteUserService;

    private static final Logger logger = LogManager.getLogger(AdminSiteView.class);

    //Admin页面主入口
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndex() {

        ModelAndView view = new ModelAndView();
        view.setViewName("/admin/index");

        //获取当前用登录用户详细信息
        SiteUserDetail siteUserDetail = siteUserService.getCurrentUser();

        view.addObject("user", siteUserDetail.getUsername());
        return view;
    }
}
