package com.tor.controller;


import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import com.tor.service.IFirstIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FirstIdentityController {

    @Autowired
    private IFirstIdentityService iFirstIdentityService;

    @RequestMapping(value = "/first/goto_identity")
    public String identity() {
        return "first_identity";
    }


    @RequestMapping(value = "/first/identity")
    public String identity(HttpSession session, Model model, String filePath) {
        int count = 0;
        int count1 = 0;
        if (StringUtils.isEmpty(filePath)) {
            ServerResponse response = ServerResponse.createByErrorMessage("文件路径为空");
            session.setAttribute("list", null);
            model.addAttribute("res", response);
        } else {
            //ServerResponse<List<Traffic>> response = iFirstIdentityService.getIdentityList(filePath);
            ServerResponse<PageBean> response = iFirstIdentityService.getIdentityList(filePath);
            List<Traffic> trafficList = iFirstIdentityService.getAllList(filePath,0);
            for (int i = 0; i < trafficList.size(); i++){
                if (trafficList.get(i).getTor().equals("YES")){
                    count++;
                }
                if (trafficList.get(i).getTor().equals("未找到连接过程，可疑")){
                    count1++;
                }
            }
            session.setAttribute("list", trafficList);
            model.addAttribute("res", response);
            model.addAttribute("tor_number",count);
            model.addAttribute("tor_number1",count1);
        }
        return "first_identity";
    }

    @RequestMapping(value = "/first/identity_by_init_page")
    public String identityByInitPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,HttpSession session, Model model, String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            ServerResponse response = ServerResponse.createByErrorMessage("文件路径为空");
            session.setAttribute("list", null);
            model.addAttribute("res", response);
        } else {
            //ServerResponse<List<Traffic>> response = iFirstIdentityService.getIdentityList(filePath);
            ServerResponse<PageBean> response = iFirstIdentityService.getIdentityList(filePath);
            List<Traffic> trafficList = iFirstIdentityService.getAllList(filePath,0);
            session.setAttribute("list", trafficList);
            model.addAttribute("res", response);
        }
        return "first_identity";
    }

    @RequestMapping(value = "/first/identity_by_page")
    public String identityByPage(HttpSession session, Model model, Integer page) {
        List<Traffic> trafficList = (List<Traffic>) session.getAttribute("list");
        if (trafficList == null) {
            ServerResponse response = ServerResponse.createByErrorMessage("判断超时，请重新判断");
            model.addAttribute("res", response);
        } else if (StringUtils.isEmpty(page)) {
            ServerResponse response = ServerResponse.createByErrorMessage("参数错误");
            model.addAttribute("res", response);
        } else {
            ServerResponse<PageBean> response = iFirstIdentityService.queryForPage(20, page, trafficList);
            model.addAttribute("res", response);
        }
        return "first_identity";
    }


    @RequestMapping(value = "/first/show_five_tuple")
    public String showFiveTuple(HttpSession session, Model model, String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            ServerResponse response = ServerResponse.createByErrorMessage("文件路径为空");
            session.setAttribute("list", null);
            model.addAttribute("res", response);
        } else {
            //ServerResponse<List<Traffic>> response = iFirstIdentityService.getIdentityList(filePath);
            ServerResponse<String> response = iFirstIdentityService.getFiveTuple(filePath);
            model.addAttribute("res", response);
        }
        return "show_five_tuple";
    }

    @RequestMapping(value = "/first/show_five_tuple_by_page")
    public String showFiveTupleByPage(HttpSession session, Model model, Integer page) {
        List<Traffic> trafficList = (List<Traffic>) session.getAttribute("list");
        if (trafficList == null) {
            ServerResponse response = ServerResponse.createByErrorMessage("超时，请重新判断");
            model.addAttribute("res", response);
        } else if (StringUtils.isEmpty(page)) {
            ServerResponse response = ServerResponse.createByErrorMessage("page参数错误");
            model.addAttribute("res", response);
        } else {
            ServerResponse<PageBean> response = iFirstIdentityService.queryForPage(20, page, trafficList);
            model.addAttribute("res", response);
        }
        return "show_five_tuple";
    }


    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "top")
    public String top() {
        return "top";
    }

    @RequestMapping(value = "left")
    public String left() {
        return "left";
    }

    @RequestMapping(value = "right")
    public String right() {
        return "right";
    }

    @RequestMapping(value = "/first/first_identity")
    public String firstIdentity() {
        return "first_identity";
    }

    @RequestMapping(value = "/first/show_five_tuple_init")
    public String showFiveTupleInit() {
        return "show_five_tuple";
    }
}
