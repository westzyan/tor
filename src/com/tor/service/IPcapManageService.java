package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.Pcap;

import java.util.List;

public interface IPcapManageService {

    public ServerResponse<List<Pcap>> getAllPcap();

    public ServerResponse deletePcapById(Integer id);

    public ServerResponse insertPcap(Pcap pcap);

    public Pcap selectBySha1Value(String sha1Value);

    public Pcap selectById(Integer id);
}
