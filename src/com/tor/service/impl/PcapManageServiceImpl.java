package com.tor.service.impl;

import com.tor.common.ServerResponse;
import com.tor.mapper.PcapMapper;
import com.tor.pojo.Pcap;
import com.tor.service.IPcapManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iPcapManageService")
public class PcapManageServiceImpl implements IPcapManageService {

    @Autowired
    private PcapMapper pcapMapper;

    public ServerResponse<List<Pcap>> getAllPcap(){
        return ServerResponse.createBySuccess(pcapMapper.selectAll());
    }

    public ServerResponse deletePcapById(Integer id){
        if(pcapMapper.selectByPrimaryKey(id) == null){
            return ServerResponse.createByErrorMessage("查无此文件包");
        }else {
            if(pcapMapper.deleteByPrimaryKey(id) > 0){
                return ServerResponse.createBySuccessMessage("删除成功");
            }else {
                return ServerResponse.createByErrorMessage("删除失败");
            }
        }
    }

    public ServerResponse insertPcap(Pcap pcap){
        if(pcapMapper.insert(pcap) > 0){
            return ServerResponse.createBySuccessMessage("导入成功");
        }else {
            return ServerResponse.createByErrorMessage("导入失败");
        }
    }

    public Pcap selectBySha1Value(String sha1Value){
        return pcapMapper.selectBysha1Value(sha1Value);
    }

    public Pcap selectById(Integer id){
        return pcapMapper.selectByPrimaryKey(id);
    }


}
