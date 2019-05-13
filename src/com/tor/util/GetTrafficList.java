package com.tor.util;


import com.tor.pojo.Traffic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GetTrafficList {
    /**
     * 处理使用tshark命令在命令行返回的字符串，输入字符串为命令行中的一行，其内容为报文的信息，如五元组和密码套件等
     * 由于分析包为空的值为空，存在连续制表符，故将制表符之间插入空格以便分割属性值，返回的字符串数组即为五元组等信息
     *
     * @param trafficString example:		String s2 = "192.168.1.100	52534	192.168.1.197	5555	6				96:9d:05:5f :3a:8e:1c:ce:43:f9:e9:f6:60:a0:76:70:fc:70:85:18:95:ed:fd:57:d9:ff:05:44	30	49195,49199,52393,52392,49196,49200,49162,49161,49171,49172,51,57,47,53,255	28	www.b46xlsadb4cil5ofw5kj.com			";
     * @return
     */
    public String[] trafficStringHandle(String trafficString) {
        String newTrafficString[] = new String[10000];
        for (int i = 0; i < trafficString.length() - 1; i++) {
            if (trafficString.charAt(i) == '\t' && trafficString.charAt(i + 1) == '\t') {
                newTrafficString[i] = "\t ";
            } else {
                newTrafficString[i] = "" + trafficString.charAt(i);
            }
        }
        if (trafficString.charAt(0) == '\t') {
            newTrafficString[0] = " \t";
        }
        if (trafficString.charAt(trafficString.length() - 1) == '\t') {
            newTrafficString[trafficString.length() - 1] = "\t ";
        } else {
            newTrafficString[trafficString.length() - 1] = "" + trafficString.charAt(trafficString.length() - 1);
        }
        StringBuffer sb = new StringBuffer();
        String allStr = "";
        for (int i = 0; i < newTrafficString.length; i++) {
            if (newTrafficString[i] != null) {
                allStr = sb.append(newTrafficString[i]).toString();
            }
        }

//        System.out.println(allStr);
        String splitTraffic[] = allStr.split("\t");
//        for (int i = 0; i < splitTraffic.length; i++) {
//            System.out.println(splitTraffic[i]);
//        }
        return splitTraffic;
    }

    /**
     * 使用命令行，输入命令，将过滤过的流量字段封装为流量对象，添加到list集合并返回
     *
     * @param commandStr
     * @return
     */
    public List<Traffic> exeCmd(String commandStr) {
        List<Traffic> list = new ArrayList<Traffic>();
        int count = 0;
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
//			p.waitFor();
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                String[] a = new GetTrafficList().trafficStringHandle(line);
                Traffic traffic = new Traffic();
                traffic.setId(count);
                traffic.setSourceIP(a[0]);
                traffic.setDestinationIP(a[3]);
                if (a[6].equals("6") || a[6].equals("17")) {
                    int proto = Integer.parseInt(a[6]);
                    if (proto == 6) {
                        traffic.setSourcePort(Integer.parseInt(a[1]));
                        traffic.setDestinationPort(Integer.parseInt(a[4]));
                    }
                    if (proto == 17) {
                        traffic.setSourcePort(Integer.parseInt(a[2]));
                        traffic.setDestinationPort(Integer.parseInt(a[5]));
                    }
                    traffic.setProtocol(proto);
                    traffic.setRandomBytes(a[7]);
                    if (a[8].equals(" ")) {
                        traffic.setCipherSuitesLength(0);
                    } else {
                        traffic.setCipherSuitesLength(Integer.parseInt(a[8]));
                    }
                    traffic.setCipherSuite(a[9]);
                    if (a[10].equals(" ")) {
                        traffic.setServerNameLength(0);
                    } else {
                        traffic.setServerNameLength(Integer.parseInt(a[10]));
                    }
                    traffic.setServerName(a[11]);
                    traffic.setSubjectAndIssuer(a[12]);
                    traffic.setBeforeAndAfter(a[13]);
                    traffic.setTor("");
                    list.add(traffic);
                    count++;
                } else {
                    count++;
//					traffic.setProtocol();;
                    traffic.setTor("NO");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println("listsize = " + list.size());
        return list;
    }


    /**
     * 输入命令，获取流量包的五元组信息，只包含tcp,udp协议
     * @param filePath
     * @return Traffic的List集合
     */
    public List<Traffic> getFiveTuple(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        String commandStr = stringBuilder.append("sudo tshark -r ").append(filePath).append(" -T fields -e frame.time -e ip.src -e tcp.srcport -e udp.srcport -e ip.dst -e tcp.dstport -e udp.dstport -e ip.proto ").toString();
        List<Traffic> list = new ArrayList<Traffic>();
        int count = 0;
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
//			p.waitFor();
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println("I am the line " +line);
                String[] a = new GetTrafficList().trafficStringHandle(line);
                Traffic traffic = new Traffic();
                traffic.setId(count);
                traffic.setTime(a[0]);
                traffic.setSourceIP(a[1]);
                traffic.setDestinationIP(a[4]);
                if (a[7].equals("6") || a[7].equals("17")) {
                    int proto = Integer.parseInt(a[7]);
                    if (proto == 6) {
                        traffic.setSourcePort(Integer.parseInt(a[2]));
                        traffic.setDestinationPort(Integer.parseInt(a[5]));
                    }
                    if (proto == 17) {
                        traffic.setSourcePort(Integer.parseInt(a[3]));
                        traffic.setDestinationPort(Integer.parseInt(a[6]));
                    }
                    traffic.setProtocol(proto);
                    count++;
                    list.add(traffic);
                } else {
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("FiveTuplelistsize = " + list.size());
        return list;
    }

    public static void main(String[] args) {
        String commandStr = "/home/ubuntu2/AW/Bridge/torPcap/20190506/test.pcap";
//        new GetTrafficList().exeCmd(commandStr);
        List<Traffic> list = new GetTrafficList().getFiveTuple(commandStr);
        for(Traffic traffic:list){
            System.out.println("time:"+traffic.getTime()+ "  ip:"+traffic.getSourceIP()+"  srcport"+ traffic.getSourcePort()+"   dst"+ traffic.getDestinationIP()+"   dstport "+traffic.getDestinationPort()+"   xieyi"+traffic.getProtocol());
        }

//		String s2 = "			192.168.1.100	52534	192.168.1.197	5555	6	96:9d:05:5f :3a:8e:1c:ce:43:f9:e9:f6:60:a0:76:70:fc:70:85:18:95:ed:fd:57:d9:ff:05:44	30	49195,49199,52393,52392,49196,49200,49162,49161,49171,49172,51,57,47,53,255	28	www.b46xlsadb4cil5ofw5kj.com";
//		new GetList().trafficStringHandle(s2);
    }

}