package com.tor.controller;

import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import com.tor.service.ISecondIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SecondIdentityController {

    @Autowired
    private ISecondIdentityService iSecondIdentityService;

    @RequestMapping(value = "/second/goto_identity")
    public String identity() {
        return "second_identity";
    }


    @RequestMapping(value = "/second/identity")
    public String identity(HttpSession session, Model model, String filePath, String feature, String algorathm) {

        if (StringUtils.isEmpty(filePath)) {
            ServerResponse response = ServerResponse.createByErrorMessage("文件路径为空");
            session.setAttribute("list", null);
            model.addAttribute("res", response);
        } else if (StringUtils.isEmpty(feature) || StringUtils.isEmpty(algorathm)) {
            ServerResponse response = ServerResponse.createByErrorMessage("参数错误");
            session.setAttribute("list", null);
            model.addAttribute("res", response);
        } else {

            //ServerResponse<List<Traffic>> response = iFirstIdentityService.getIdentityList(filePath);
            ServerResponse<PageBean> response = iSecondIdentityService.getClassifyList(filePath, feature, algorathm);
            List<Flow> flowList = iSecondIdentityService.getAllList(filePath, feature, algorathm);
            session.setAttribute("list", flowList);
            model.addAttribute("res", response);
        }
        return"second_identity";
    }


    @RequestMapping(value = "/second/identity_by_page")
    public String identityByPage(HttpSession session, Model model, Integer page) {
        List<Flow> flowList = (List<Flow>) session.getAttribute("list");
        if (flowList == null) {
            ServerResponse response = ServerResponse.createByErrorMessage("判断超时，请重新判断");
            model.addAttribute("res", response);
        } else if (StringUtils.isEmpty(page)) {
            ServerResponse response = ServerResponse.createByErrorMessage("参数错误");
            model.addAttribute("res", response);
        } else {
            ServerResponse<PageBean> response = iSecondIdentityService.queryForPage(20, page, flowList);
            model.addAttribute("res", response);
        }
        return "second_identity";
    }
}
