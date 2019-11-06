package com.tor.service.impl;

import com.alibaba.fastjson.JSON;
import com.tor.common.ServerResponse;
import com.tor.mapper.BridgeRouterMapper;
import com.tor.pojo.BridgeRouter;
import com.tor.service.IBridgeRouterManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iBridgeRouterManageService")
public class BridgeRouterManageServiceImpl implements IBridgeRouterManageService {

    @Autowired
    private BridgeRouterMapper bridgeRouterMapper;

    public ServerResponse<List<BridgeRouter>> getAllBridgeRouter() {
        return ServerResponse.createBySuccess(bridgeRouterMapper.selectAll());
    }

    public static void main(String[] args) {
        BridgeRouterManageServiceImpl bridgeRouterServiceImpl = new BridgeRouterManageServiceImpl();
        String jsonFiveTuple = JSON.toJSONString(bridgeRouterServiceImpl.getAllBridgeRouter().getData());
        System.out.println(jsonFiveTuple);
    }


}
