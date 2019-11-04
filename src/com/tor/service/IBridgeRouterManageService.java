package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.BridgeRouter;


import java.util.List;

public interface IBridgeRouterManageService {

    public ServerResponse<List<BridgeRouter>> getAllBridgeRouter();

}
