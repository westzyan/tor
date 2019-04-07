package com.tor.util;

import com.tor.pojo.Pcap;

import java.io.File;
import java.util.List;

public class PcapManageUtil {

    public List<Pcap> lookAllPcap(){
        File file = new File("/home/ubuntu2/AW/Bridge/20190320/tor.pcap");
        System.out.println(file.getName());

        return null;
    }
}
