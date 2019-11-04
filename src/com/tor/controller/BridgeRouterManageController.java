package com.tor.controller;


import com.alibaba.fastjson.JSON;
import com.tor.service.IBridgeRouterManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BridgeRouterManageController {

    @Autowired
    private IBridgeRouterManageService iBridgeRouterManageService;

    @RequestMapping(value = "/bridge/list")
    public String bridge(Model model) {

        String bridgeListJson = JSON.toJSONString(iBridgeRouterManageService.getAllBridgeRouter().getData());

        System.out.println(bridgeListJson);
        model.addAttribute("res", bridgeListJson);

        return "bridge";
    }

}
