package com.tor.service;

import com.tor.common.ServerResponse;
import com.tor.pojo.Pcap;

import java.util.List;

public interface IPcapManageService {

    ServerResponse<List<Pcap>> getAllPcap();

    ServerResponse deletePcapById(Integer id);

    ServerResponse<List<Pcap>> insertPcap(Pcap pcap);

    Pcap selectBySha1Value(String sha1Value);

    Pcap selectById(Integer id);
}
