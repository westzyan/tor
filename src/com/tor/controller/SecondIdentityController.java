package com.tor.controller;

import com.alibaba.fastjson.JSON;
import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import com.tor.service.ISecondIdentityService;
import com.tor.service.impl.SecondIdentityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String identity(Model model,  String trainFilePath, String testFilePath, String feature, String algorithm) {

        int count = 0;
        if (StringUtils.isEmpty(trainFilePath)||StringUtils.isEmpty(testFilePath)) {
            ServerResponse response = ServerResponse.createByErrorMessage("文件路径为空");
            model.addAttribute("res", response);
        }

        else if (StringUtils.isEmpty(feature) || StringUtils.isEmpty(algorithm)) {
            ServerResponse response = ServerResponse.createByErrorMessage("参数错误");
            model.addAttribute("res", response);
        }
        else {
            //ServerResponse<List<Traffic>> response = iFirstIdentityService.getIdentityList(filePath);
            List<Flow> flowList = iSecondIdentityService.getLabelList(trainFilePath, testFilePath, feature, algorithm);

            if (flowList == null || flowList.size() == 0){
                ServerResponse response =  ServerResponse.createByErrorMessage("没有读取到流");
                model.addAttribute("res", response);
            }else {

                for (int i = 0; i < flowList.size(); i++){
                    if (!flowList.get(i).getLabel().equals("NONTOR")){
                        count++;
                    }
                }
                String flowListJson = JSON.toJSONString(flowList);
                String selectFeatures = iSecondIdentityService.getFeatures(trainFilePath, feature);
                model.addAttribute("res", ServerResponse.createBySuccess(flowListJson));
                model.addAttribute("data",flowListJson);
                model.addAttribute("select_features", selectFeatures);
                model.addAttribute("flow_number", flowList.size());
                model.addAttribute("flow_tor_number", count);
            }
        }
        return "second_identity";
    }


    @RequestMapping(value = "/second/identity_init")
    public String identityInit(Model model) {

        List<Flow> flowList = iSecondIdentityService.getLabelList("/home/ubuntu2/test/second/2018-11-26train.csv", "/home/ubuntu2/test/second/2018-11-27test.csv", "CfsSubsetEval+BestFirst", "C4.5");
        String flowListJson = JSON.toJSONString(flowList);
        String selectFeatures = iSecondIdentityService.getFeatures("/home/ubuntu2/test/second/2018-11-27test.csv", "CfsSubsetEval+BestFirst");
        int count = 0;
        for (int i = 0; i < flowList.size(); i++){
            if (!flowList.get(i).getLabel().equals("NONTOR")){
                count++;
            }
        }
        model.addAttribute("res", ServerResponse.createBySuccess(flowListJson));
        model.addAttribute("data",flowListJson);
        model.addAttribute("select_features", selectFeatures);
        model.addAttribute("flow_number", flowList.size());
        model.addAttribute("flow_tor_number", count);
        return "second_identity_2";
    }


/*    @RequestMapping(value = "/second/identity_by_page")
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
    }*/

}
