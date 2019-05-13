package com.tor.util;

import com.tor.pojo.Pcap;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PcapManageUtil {

    public List<Pcap> lookAllPcap() {
        File file = new File("/home/ubuntu2/AW/Bridge/20190320/tor.pcap");
        System.out.println(file.getName());

        return null;
    }

    public static String getFileSha1(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static List<String> loadNIC() throws Exception{
        List<String> nicList = new ArrayList<>();
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface nif = nifs.nextElement();
            // 获得与该网络接口绑定的 IP 地址，一般只有一个
            Enumeration<InetAddress> addresses = nif.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                    nicList.add(nif.getName());
//                    System.out.println("网卡接口名称：" + nif.getName());
//                    System.out.println("网卡接口地址：" + addr.getHostAddress());
//                    System.out.println();
                }
            }
        }

        return nicList;
    }

    public static void main(String[] args) throws Exception{
        List list = PcapManageUtil.loadNIC();
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
