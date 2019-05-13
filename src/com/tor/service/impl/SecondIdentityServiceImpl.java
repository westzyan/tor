package com.tor.service.impl;

import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import com.tor.pojo.PageBean;
import com.tor.service.ISecondIdentityService;
import com.tor.util.ClassifyUtil;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iSecondIdentityService")
public class SecondIdentityServiceImpl implements ISecondIdentityService {

    public ServerResponse<PageBean> getClassifyList(String trainFilePath,String testFilePath, String feature, String algorithm) {
        ClassifyUtil classifyUtil = new ClassifyUtil();
        ServerResponse<List<Flow>> serverResponse = classifyUtil.getClassifyList(trainFilePath, testFilePath,feature, algorithm);
        List<Flow> flowList = serverResponse.getData();
        int status = serverResponse.getStatus();
        String msg = serverResponse.getMsg();
        if (status == 0) {
            return this.queryForPage(20, 1, flowList);
        } else {
            return ServerResponse.createByErrorMessage(msg);
        }
    }

    public List<Flow> getAllList(String trainFilePath,String testFilePath, String feature, String algorithm) {
        ClassifyUtil classifyUtil = new ClassifyUtil();
        ServerResponse<List<Flow>> serverResponse = classifyUtil.getClassifyList(trainFilePath,testFilePath, feature, algorithm);
        List<Flow> flowList = serverResponse.getData();
        return flowList;
    }

    /**
     * 分页查询
     *
     * @param pageSize 每页显示多少记录
     * @param page     当前页
     * @param flowList 流List
     * @return 封装了分页信息的bean
     */
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

}
