package com.tor.service.impl;

import com.alibaba.fastjson.JSON;
import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.service.ISecondIdentityService;
import com.tor.util.ClassifyList;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iSecondIdentityService")
public class SecondIdentityServiceImpl implements ISecondIdentityService {

/*    public ServerResponse<PageBean> getClassifyList(String trainFilePath,String testFilePath, String feature, String algorithm) {
        ServerResponse<List<Flow>> serverResponse = ClassifyList.getClassifyList(trainFilePath, testFilePath, feature, algorithm);
        List<Flow> flowList = serverResponse.getData();
        int status = serverResponse.getStatus();
        String msg = serverResponse.getMsg();
        if (status == 0) {
            return this.queryForPage(20, 1, flowList);
        } else {
            return ServerResponse.createByErrorMessage(msg);
        }
    }*/

    public ServerResponse<String> getLabelList(String trainFilePath,String testFilePath,String feature, String algorithm)  {
        ClassifyList classifyList = new ClassifyList();
        System.out.println("getLabelList1");
        List<Flow> flowList = classifyList.getClassifyList(trainFilePath, testFilePath,feature, algorithm);
        System.out.println("getLabelList2"+ flowList.size());
        if (flowList.size() == 0) {
            System.out.println("---------------------");
            return ServerResponse.createByErrorMessage("该数据包未发现流");
        } else {
            System.out.println("fdsafasffaadsf");
            return ServerResponse.createBySuccess(JSON.toJSONString(flowList));
        }
    }

    public String getFeatures(String trainFilePath,String feature) {
        ClassifyList classifyList = new ClassifyList();


        return classifyList.showSelectFeatures(trainFilePath,feature);

    }

    public int getFlowNumber(String trainFilePath,String testFilePath,String feature, String algorithm) {
        ClassifyList classifyList = new ClassifyList();

        return classifyList.getClassifyList(trainFilePath, testFilePath,feature, algorithm).size();
    }


//    public List<Flow> getAllList(String trainFilePath,String testFilePath, String feature, String algorithm) {
//        ServerResponse<List<Flow>> serverResponse = classifyUtil.getClassifyList(trainFilePath,testFilePath, feature, algorithm);
//        List<Flow> flowList = serverResponse.getData();
//        return flowList;
//    }

  /*  *//**
     * 分页查询
     *
     * @param pageSize 每页显示多少记录
     * @param page     当前页
     * @param flowList 流List
     * @return 封装了分页信息的bean
     *//*
    public ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Flow> flowList) {
        System.out.println(flowList == null);
        int allRow = flowList.size();  //总记录数
        int totalPage = PageBean.countTatalPage(pageSize, allRow); //总页数
        final int offset = PageBean.countOffset(pageSize, page); //当前页开始记录
        final int length = pageSize; // 每页记录数
        final int currentPage = PageBean.countCurrentPage(page); // 当前页
        List<Flow> list = null;
        if (offset < flowList.size() && offset + length <= flowList.size()) {
            list = flowList.subList(offset, offset + length);
        } else if (offset < flowList.size() && offset + length >= flowList.size()) {
            list = flowList.subList(offset, flowList.size());
        } else {
            list = null;
        }
//		List list = itemDao.queryForPage(hql, offset, length); //
        //把分页信息保存到Bean当中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return ServerResponse.createBySuccess(pageBean);
    }
*/
}
