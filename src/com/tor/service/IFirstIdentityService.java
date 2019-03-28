package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;

import java.util.List;

public interface IFirstIdentityService {

    public ServerResponse<List<Traffic>> getIdentityList(String filePath);

    public ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Traffic> trafficList);
}
