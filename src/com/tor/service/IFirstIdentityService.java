package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IFirstIdentityService {

    ServerResponse<PageBean> getIdentityList(String filePath);

    List<Traffic> getAllList(String filePath,int flag);

    ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Traffic> trafficList);

    ServerResponse<PageBean> getFiveTupleList(String filePath);

    ServerResponse<String> getFiveTuple(String filePath);
}
