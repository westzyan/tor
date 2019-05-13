package com.tor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import com.tor.service.IFirstIdentityService;
import com.tor.util.GetTrafficList;
import com.tor.util.Identification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service("iFirstIdentityService")
public class FirstIdentityServiceImpl implements IFirstIdentityService {

    /**
     * 输入文件路径，获取判断后的ServerResponse，并且带有分页，与下面的函数只是返回值不一样
     * @param filePath
     * @return ServerResponse<PageBean>
     */
    public ServerResponse<PageBean> getIdentityList(String filePath) {
        Identification identification = new Identification();
        ServerResponse<List<Traffic>> serverResponse = identification.getLastTrafficList(filePath);
        List<Traffic> trafficList = serverResponse.getData();
        int status = serverResponse.getStatus();
        String msg = serverResponse.getMsg();

        if (status == 0) {
            return this.queryForPage(20, 1, trafficList);
        } else {
            return ServerResponse.createByErrorMessage(msg);
        }
    }

    /**
     * 获取判断后的流量集合
     * @param filePath flag (flag:0 代表判断过的流量，1：代表获取五元组，即未判断的)
     * @return List<Traffic>
     */
    public List<Traffic> getAllList(String filePath,int flag) {
        if (flag == 0){
            Identification identification = new Identification();
            ServerResponse<List<Traffic>> serverResponse = identification.getLastTrafficList(filePath);
            List<Traffic> trafficList = serverResponse.getData();
            return trafficList;
        }else {
            List<Traffic> trafficList = new GetTrafficList().getFiveTuple(filePath);
            return trafficList;
        }
    }

    /**
     * 分页查询
     *
     * @param pageSize    每页显示多少记录
     * @param page        当前页
     * @param trafficList 流量List
     * @return 封装了分页信息的bean
     */
    public ServerResponse<PageBean> queryForPage(Integer pageSize, Integer page, List<Traffic> trafficList) {
        System.out.println(trafficList == null);
        int allRow = trafficList.size();  //总记录数
        int totalPage = PageBean.countTatalPage(pageSize, allRow); //总页数
        final int offset = PageBean.countOffset(pageSize, page); //当前页开始记录
        final int length = pageSize; // 每页记录数
        final int currentPage = PageBean.countCurrentPage(page); // 当前页
        List<Traffic> list = null;
        if (offset < trafficList.size() && offset + length <= trafficList.size()) {
            list = trafficList.subList(offset, offset + length);
        } else if (offset < trafficList.size() && offset + length >= trafficList.size()) {
            list = trafficList.subList(offset, trafficList.size());
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


    /**
     * 获取五元组信息，分页功能
     * @param filePath
     * @return
     */
    public ServerResponse<PageBean> getFiveTupleList(String filePath) {
        GetTrafficList getTrafficList = new GetTrafficList();
        List<Traffic> trafficList= getTrafficList.getFiveTuple(filePath);
        if (trafficList.size() == 0){
            return ServerResponse.createByErrorMessage("未解析到流量");
        }
        return this.queryForPage(20, 1, trafficList);
    }

    /**
     * 获取五元组信息，返回json 字符串，前台分页
     * @param filePath
     * @return
     */
    public ServerResponse<String> getFiveTuple(String filePath) {
        GetTrafficList getTrafficList = new GetTrafficList();
        List<Traffic> trafficList= getTrafficList.getFiveTuple(filePath);
        if (trafficList.size() == 0){
            return ServerResponse.createByErrorMessage("未解析到流量");
        }
        String jsonFiveTuple = JSON.toJSONString(trafficList);
        return ServerResponse.createBySuccess(jsonFiveTuple);
    }

    public static void main(String[] args) {
        FirstIdentityServiceImpl firstIdentityService = new FirstIdentityServiceImpl();
        ServerResponse serverResponse = firstIdentityService.getFiveTuple("/home/ubuntu2/AW/Bridge/torPcap/20190506/test.pcap");
        System.out.println(serverResponse.getData());
    }


}
