package com.tor.controller;

import com.tor.common.ServerResponse;
import com.tor.pojo.Pcap;
import com.tor.service.IPcapManageService;
import com.tor.util.PcapManageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    public String insertPcap(Model model, String filePath) {
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


    //加载本机网卡名称
    @RequestMapping("/pcap/load_NIC")
    public String loadNIC(HttpSession session, Model model) {
//        return iPcapManageService.getAllPcap();
//        System.out.println(iPcapManageService.getAllPcap().getData().size());
//        System.out.println("/pcap/list-------------------");
        try {
            model.addAttribute("nic", PcapManageUtil.loadNIC());
            session.setAttribute("nic", PcapManageUtil.loadNIC());
            System.out.println(PcapManageUtil.loadNIC().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pcap_capture";
    }

    //生成捕获数据包命令
    //暂时将过滤规则简化
    @RequestMapping("/pcap/capture")
    public String capturePcap(Model model ,String nicName, String count, String host, String src, String dst,String port,
                              String srcPort,String dstPort, String protocol, String fileName) {
        StringBuffer stringBuffer = new StringBuffer("tcpdump命令：");
        if(StringUtils.isEmpty(nicName)){
            stringBuffer.append("网卡地址为空，请选择网卡地址");
        }else if(StringUtils.isEmpty(count)){
            stringBuffer.append("网络数据包数目为空，请填入数据包数目");
        }else if(StringUtils.isEmpty(fileName)){
            stringBuffer.append("文件名为空");
        }else {
            if(!StringUtils.isEmpty(host)){
                if (!StringUtils.isEmpty(port)){
                    if (!StringUtils.isEmpty(protocol)){
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (" + "(port" + port + ") and (host "+ host + "))'" + " -w "+fileName +"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (" + "(port" + port + ") and (host "+ host + "))'" + " -w "+fileName +"<br>请复制命令运行";
                    }else {
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " '((host" + host + ") and (port "+ port + "))'" + " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((host" + host + ") and (port "+ port + "))'" + " -w "+fileName+"<br>请复制命令运行";
                    }
                }else {
                    if (!StringUtils.isEmpty(protocol)){
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (host "+ host + "))'" + " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (host "+ host + "))'" + " -w "+fileName+"<br>请复制命令运行";
                    }else {
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " '(host "+ host + "'" + " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '(host "+ host + "'" + " -w "+fileName+"<br>请复制命令运行";
                    }
                }
            }else {
                if (!StringUtils.isEmpty(port)){
                    if (!StringUtils.isEmpty(protocol)){
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (" + "(port" + port + "))'" + " -w "+fileName +"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (" + "(port" + port + ") and (host "+ host + "))'" + " -w "+fileName +"<br>请复制命令运行";
                    }else {
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " port "+ port + " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((host" + host + ") and (port "+ port + "))'" + " -w "+fileName+"<br>请复制命令运行";
                    }
                }else {
                    if (!StringUtils.isEmpty(protocol)){
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count +  protocol +  " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '((" + protocol + ") and (host "+ host + "))'" + " -w "+fileName+"<br>请复制命令运行";
                    }else {
                        stringBuffer.append("tcpdump -i "+ nicName + " -s 0 -c " + count + " -w "+fileName+"<br>请复制命令运行");
//                        msg = msg + "tcpdump -i "+ nicName + " -s 0 -c " + count + " '(host "+ host + "'" + " -w "+fileName+"<br>请复制命令运行";
                    }
                }
            }

        }

        System.out.println(stringBuffer.toString());

        model.addAttribute("msg", stringBuffer.toString());

        return "pcap_capture";
    }

    //进入捕获界面
    @RequestMapping("/pcap/goto_capture")
    public String captureIndex(Model model) {
        model.addAttribute("nic",null);
        return "pcap_capture";
    }


}
