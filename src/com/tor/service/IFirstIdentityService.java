package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IFirstIdentityService {

    public ServerResponse<PageBean> getIdentityList(String filePath);

    public List<Traffic> getAllList(String filePath);

    public ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Traffic> trafficList);
}
