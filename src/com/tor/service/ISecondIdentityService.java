package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.pojo.PageBean;

import java.util.List;

public interface ISecondIdentityService {

    public ServerResponse<PageBean> getClassifyList(String trainFilePath,String testFilePath,String feature, String algorithm);

    public ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Flow> flowList);

    public List<Flow> getAllList(String trainFilePath,String testFilePath, String feature, String algorithm);
}
