package com.tor.service.impl;

import com.tor.common.ServerResponse;
import com.tor.pojo.PageBean;
import com.tor.pojo.Traffic;
import com.tor.service.IFirstIdentityService;
import com.tor.util.Identification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service("iFirstIdentityService")
public class FirstIdentityServiceImpl implements IFirstIdentityService {


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

    public List<Traffic> getAllList(String filePath) {
        Identification identification = new Identification();
        ServerResponse<List<Traffic>> serverResponse = identification.getLastTrafficList(filePath);
        List<Traffic> trafficList = serverResponse.getData();

        return trafficList;
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



    //ssssss
}
