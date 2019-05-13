package com.tor.service.impl;

import com.alibaba.fastjson.JSON;
import com.tor.common.ServerResponse;
import com.tor.mapper.CountryOnionRelayMapper;
import com.tor.pojo.CountryOnionRelay;
import com.tor.service.IInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iInitService")
public class InitServiceImpl implements IInitService {

    @Autowired
    private CountryOnionRelayMapper countryOnionRelayMapper;

    public ServerResponse<List<CountryOnionRelay>> getAllCountryOnionRelay() {
        return ServerResponse.createBySuccess(countryOnionRelayMapper.selectAll());
    }

    public static void main(String[] args) {
        InitServiceImpl initService = new InitServiceImpl();
        String jsonFiveTuple = JSON.toJSONString(initService.getAllCountryOnionRelay().getData());
        System.out.println(jsonFiveTuple);
    }


}
