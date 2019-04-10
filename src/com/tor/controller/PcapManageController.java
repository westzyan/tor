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
public class PcapManageController {

    @Autowired
    private IPcapManageService iPcapManageService;


    @RequestMapping("/pcap/list")
    public String getAllPcap(Model model) {
//        return iPcapManageService.getAllPcap();
        System.out.println(iPcapManageService.getAllPcap().getData().size());
        System.out.println("/pcap/list-------------------");

        model.addAttribute("res", iPcapManageService.getAllPcap());
        model.addAttribute("size", iPcapManageService.getAllPcap().getData().size());
        return "pcap_manage";
    }


    @RequestMapping("/pcap/delete_by_id")
    public String deletePcapById(Model model, Integer id) {
        model.addAttribute("res", iPcapManageService.deletePcapById(id));
//        物理删除
//        Pcap pcap = iPcapManageService.selectById(id);
//        if (pcap != null){
//            File pcapFile = new File(pcap.getFilePath() + "/" + pcap.getFileName());
//            pcapFile.delete();
//        }
        return "pcap_manage";
    }

    @RequestMapping("/pcap/delete_by_ids")
    public String deletePcapById(Model model, String ids) {
        //2,3,4
        String[] idArray = ids.split(",");
        for(int i = 0; i < idArray.length; i++){
            int id = Integer.parseInt(idArray[i]);
            if (!iPcapManageService.deletePcapById(id).isSuccess()){
                model.addAttribute("res", ServerResponse.createBySuccess("删除失败，id为"+id, iPcapManageService.getAllPcap()));
            }
        }
        model.addAttribute("res", ServerResponse.createBySuccess("删除成功", iPcapManageService.getAllPcap()));
//        物理删除
//        Pcap pcap = iPcapManageService.selectById(id);
//        if (pcap != null){
//            File pcapFile = new File(pcap.getFilePath() + "/" + pcap.getFileName());
//            pcapFile.delete();
//        }
        return "pcap_manage";
    }

    @RequestMapping("/pcap/insert")
    public String insertPcap(Model model,  String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            model.addAttribute("res", ServerResponse.createByErrorMessage("文件不存在"));
        } else {
            String sha1Value = PcapManageUtil.getFileSha1(file);
            Pcap pcap1 = iPcapManageService.selectBySha1Value(sha1Value);
            if (pcap1 != null) {
                model.addAttribute("res", ServerResponse.createByErrorMessage("仓库中已存在该文件,该文件名为" + pcap1.getFileName() + " 文件路径为" +
                        pcap1.getFilePath()));
            } else {
                Pcap pcap = new Pcap();
                pcap.setFileName(file.getName());
                pcap.setFilePath(file.getParent());
                pcap.setFileSize((file.length() / (1024f * 1024f)));
                pcap.setSha1Value(PcapManageUtil.getFileSha1(file));
                pcap.setIsHandled("未处理");
                model.addAttribute("res", iPcapManageService.insertPcap(pcap));
            }
        }
        return "pcap_manage";
    }

}
