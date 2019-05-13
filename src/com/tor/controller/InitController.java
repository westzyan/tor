package com.tor.controller;


import com.alibaba.fastjson.JSON;
import com.tor.service.IInitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InitController {

    @Autowired
    private IInitService iInitService;

    @RequestMapping(value = "init")
    public String bridge(Model model) {

        String jsonFiveTuple = JSON.toJSONString(iInitService.getAllCountryOnionRelay().getData());

        model.addAttribute("res", jsonFiveTuple);

        return "init";
    }

    @RequestMapping(value = "/toIdentity")
    public String toIdentity() {
        return "first_identity";
    }

}
