package com.tor.mapper;

import com.tor.pojo.Pcap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PcapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pcap record);

    int insertSelective(Pcap record);

    Pcap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pcap record);

    int updateByPrimaryKey(Pcap record);

    List<Pcap> selectAll();

    Pcap selectBysha1Value(String sha1Value);
}
