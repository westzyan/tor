package com.tor.controller;

import com.tor.common.ServerResponse;
import com.tor.pojo.Pcap;
import com.tor.service.IPcapManageService;
import com.tor.util.PcapManageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/pcap/")
public class PcapManageController {

    @Autowired
    private IPcapManageService iPcapManageService;

    @RequestMapping("list.do")
    @ResponseBody
    public String getAllPcap(Model model){
//        return iPcapManageService.getAllPcap();
        System.out.println(iPcapManageService.getAllPcap().getData().size());
        model.addAttribute("res", iPcapManageService.getAllPcap());
        return "lll";
    }



    public String deletePcapById(Model model, Integer id){
        model.addAttribute("res", iPcapManageService.deletePcapById(id));
//        物理删除
//        Pcap pcap = iPcapManageService.selectById(id);
//        if (pcap != null){
//            File pcapFile = new File(pcap.getFilePath() + "/" + pcap.getFileName());
//            pcapFile.delete();
//        }
        return null;
    }

    public String insertPcap(Model model, Pcap pcap, String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            model.addAttribute("res", ServerResponse.createByErrorMessage("文件不存在"));
        }else{
            String sha1Value = PcapManageUtil.getFileSha1(file);
            Pcap pcap1 = iPcapManageService.selectBySha1Value(sha1Value);
            if( pcap1 != null){
                model.addAttribute("res",ServerResponse.createByErrorMessage("仓库中已存在该文件,该文件名为"+ pcap1.getFileName()+" 文件路径为" +
                        pcap1.getFilePath()));
            }else {
                pcap.setFileName(file.getName());
                pcap.setFilePath(file.getParent());
                pcap.setFileSize((file.length()/(1024f*1024f)));
                pcap.setSha1Value(PcapManageUtil.getFileSha1(file));
                pcap.setIsHandled("未处理");
                model.addAttribute("res", iPcapManageService.insertPcap(pcap));
            }
        }
        return null;
    }

}
