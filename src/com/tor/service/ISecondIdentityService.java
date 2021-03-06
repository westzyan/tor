package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.pojo.PageBean;

import java.util.List;

public interface ISecondIdentityService {

//    ServerResponse<PageBean> getClassifyList(String trainFilePath,String testFilePath,String feature, String algorithm);

    List<Flow> getLabelList(String trainFilePath,String testFilePath,String feature, String algorithm);

    String getFeatures(String trainFilePath,String feature);

    int getFlowNumber(String trainFilePath,String testFilePath,String feature, String algorithm);


//    ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Flow> flowList);

//    List<Flow> getAllList(String trainFilePath,String testFilePath, String feature, String algorithm);
}
