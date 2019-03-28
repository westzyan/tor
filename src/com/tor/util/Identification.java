package com.tor.util;


import com.tor.common.ServerResponse;
import com.tor.pojo.Traffic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//获取初步的client hello 报文
public class Identification {

    /**
     * 从待判别的流量中获取所有的client hello流量，以List集合形式返回
     *
     * @param list
     * @return
     */
    public List<Traffic> getClientHello(List<Traffic> list) {
        List<Traffic> clientList = new ArrayList<>();
        for (Traffic traffic : list) {
            // if(traffic.getCipherSuitesLength()>0) {
            // System.out.println(traffic.getCipherSuitesLength());
            // }
            // if(traffic.getCipherSuitesLength() == 28 || traffic.getCipherSuitesLength()
            // == 30)
            // find the real length = 48,so changed
            if (traffic.getCipherSuitesLength() > 28) {
                clientList.add(traffic);
            }

        }
        return clientList;
    }

    /**
     * 判断字符串是否只包含小写字母和数字
     *
     * @param string
     * @return
     */
    public boolean isLetterAndDigits(String string) {
        boolean flag = true;
        // int f = 1;
        // boolean flag1 = false;
        // boolean flag2 = false;
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLowerCase(string.charAt(i)) && !Character.isDigit(string.charAt(i))) {
                flag = false;
                // f = 0;
            }
        }

        return flag;

    }

    /**
     * 判断是否为Tor格式的servername
     *
     * @param host
     * @return
     */
    public boolean idenServerNameOrSubject(String host) {
        boolean flag = true;
        String[] str = host.split("\\.");
        if (str.length != 3) {
            flag = false;
            return flag;
        } else if (!str[0].equals("www")) {
            flag = false;
            return flag;
        } else if (!str[2].equals("com") && !str[2].equals("net")) {
            flag = false;
            return flag;
        } else if (!new Identification().isLetterAndDigits(str[1])) {
            flag = false;
            return flag;
        }
        return flag;
    }

    /**
     * 判断client hello报文是否疑似tor流量
     *
     * @param traffic
     * @return
     */
    public boolean idenClientHello(Traffic traffic) {
        boolean flag = true;
        System.out.println(traffic.getServerName());
        // System.out.println("panduanclient");
        if (traffic.getServerNameLength() < 16) {
            // System.out.println("panduanclient length bufuhe");
            flag = false;
        } else {
            // System.out.println("panduanclient length fuhe");
            if (new Identification().idenServerNameOrSubject(traffic.getServerName()) == false) {
                flag = false;
                // System.out.println("servername geshibufu");
            }
        }

        return flag;
    }

    /**
     * 将获取的subject和issuer两部分分解开
     *
     * @param subAndIssu
     * @return
     */
    public String[] getSubAndIsuArray(String subAndIssu) {
        String str[] = subAndIssu.split(",");
        return str;
    }

    /**
     * 计算两个时间的时间差，以秒计数 格式日期格式，在此我用的是"2018-01-24 19:49:50"这种格式
     * 可以更改为自己使用的格式，例如：yyyy/MM/dd HH:mm:ss 。。。
     *
     * @param strTime1 现在时间
     * @param strTime2 以前时间
     * @return
     */
    public static long getTimeDifference(String strTime1, String strTime2) {
        long l = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date now = df.parse(strTime1);
            Date date = df.parse(strTime2);
            l = now.getTime() - date.getTime(); // 获取时间差
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 判断证书生效时间和失效时间是否为Tor标准
     *
     * @param time
     * @return
     */
    public boolean idenCertificationTime(String time) {
        boolean flag = true;
        String str[] = time.split(",");
        if (str.length != 2) {
            flag = false;
            return flag;
        } else {
            // 18-02-28 00:00:00 (UTC),19-01-01 23:59:59 (UTC)
            // 处理时间
            String substr0 = str[0].substring(0, 17);
            System.out.println("substr0" + substr0);
            String substr1 = str[1].substring(0, 17);
            System.out.println("substr1" + substr1);
            long timeDifference = Identification.getTimeDifference(substr1, substr0) / 1000;
            System.out.println("longtime" + timeDifference);
            // tor 证书失效时间最少2个小时，即7200s,最长失效时间10个月，初步定为26784000s
            if (timeDifference < 7200 || timeDifference > 26784000) {
                flag = false;
                return flag;
            } else {
                // 暂时处理到这里，ascii的时间转为长整形，与证书序列号的比较暂缓
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 判断Serverhello报文是否疑似tor流量
     *
     * @param traffic
     * @return
     */
    public boolean idenServerHello(Traffic traffic) {
        boolean flag = true;
        Identification iden = new Identification();
        if (traffic.getSubjectAndIssuer().length() < 33 || traffic.getSubjectAndIssuer().length() > 57) {
            System.out.println(traffic.getSubjectAndIssuer());
            flag = false;
            return flag;
        } else {
            String str[] = traffic.getSubjectAndIssuer().split(",");
            System.out.println(str[0]);
            System.out.println(str[1]);
            if (str.length != 2) {
                System.out.println("strlength" + str.length);
                flag = false;
                return flag;
            } else {
                System.out.println("ssssssss");
                for (int i = 0; i < str.length; i++) {
                    if (iden.idenServerNameOrSubject(str[i]) == false) {
                        flag = false;
                        System.out.println("servername  jia" + i);
                        return flag;
                    }
                }
                if (flag == true) {
                    System.out.println("zuihou");
                    flag = iden.idenCertificationTime(traffic.getBeforeAndAfter());
                    System.out.println("time" + flag);
                }
            }
        }
        return flag;
    }

    /**
     * 将本次连接中传输数据的报文判定
     *
     * @param tra    此流结果已经知道，获取其五元组信息
     * @param list   将集合中traffic所属流全部判别
     * @param result 判别结果：YES ，NO，可疑
     */
    public List<Traffic> setApplication(Traffic tra, List<Traffic> list, String result) {
        // DBhelp dbhelp = new DBhelp();
        String src = tra.getSourceIP();
        int srcport = tra.getSourcePort();
        String dst = tra.getDestinationIP();
        int dstport = tra.getDestinationPort();
        for (Traffic traffic : list) {
            if (((traffic.getSourceIP().equals(src) && traffic.getSourcePort() == srcport
                    && traffic.getDestinationIP().equals(dst) && traffic.getDestinationPort() == dstport)
                    || (traffic.getDestinationIP().equals(src) && traffic.getDestinationPort() == srcport
                    && traffic.getSourceIP().equals(dst) && traffic.getSourcePort() == dstport))) {
                traffic.setTor(result);
                System.out.println("id:" + traffic.getId() + traffic.getTor());
            }
        }
        return list;
    }

    /**
     * 根据clienthello报文中疑似Tor的报文获取其对应的serverhello和certification
     *
     * @param clientTraffic
     * @param list
     * @return
     */
    public Traffic getServerHelloAndCertification(Traffic clientTraffic, List<Traffic> list) {
        int id = clientTraffic.getId();
        String src = clientTraffic.getSourceIP();
        int srcport = clientTraffic.getSourcePort();
        String dst = clientTraffic.getDestinationIP();
        int dstport = clientTraffic.getDestinationPort();

        Traffic serverHelloTraffic = null;
        for (Traffic traffic : list) {
            if ((traffic.getId() > id) && traffic.getRandomBytes().length() > 1 && traffic.getSourceIP().equals(dst)
                    && traffic.getSourcePort() == dstport && traffic.getDestinationIP().equals(src)
                    && traffic.getDestinationPort() == srcport) {
                serverHelloTraffic = traffic;
                return serverHelloTraffic;
            }

        }
        return serverHelloTraffic;
    }

    /**
     * 将未找到连接过程的流量设置为可疑流量
     *
     * @param list
     * @param list
     * @return
     */
    public List<Traffic> setSuspiciousTraffic(List<Traffic> list) {
        for (Traffic traffic : list) {
            if (traffic.getTor().equals("") || traffic.getTor() == null) {
                traffic.setTor("未找到连接过程，可疑");
            }
            if (traffic.getProtocol() != 6) {
                traffic.setTor("NO");
            }

        }
        return list;
    }

    /**
     * 将传过来的文件名修改，生成要保存的csv文件名
     *
     * @param filePath
     * @return
     */
    public String getNewFilePath(String filePath) {
        // /home/ubuntu2/AW/pcap/ftp.pcapng
        String directory = filePath.substring(0, filePath.lastIndexOf("/")) + "/csv";
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }
//		System.out.println(directory);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
//		System.out.println(fileName);
        return directory + "/" + fileName + ".csv";
    }

    /**
     * 输入pcap包文件绝对路径，在本机中判别，首先判别client hello， 然后判别server
     * hello，最后判别certificate，返回判别过的结果集合
     *
     * @param filepath 待判别文件的绝对路径
     * @return 返回判别过的traffic流量集合
     */

    public ServerResponse<List<Traffic>> getLastTrafficList(String filepath) {
        String commandStr = "sudo tshark -r " + filepath
                + " -T fields -e ip.src -e tcp.srcport -e udp.srcport -e ip.dst -e tcp.dstport -e udp.dstport -e ip.proto "
                + "-e ssl.handshake.random_bytes -e ssl.handshake.cipher_suites_length "
                + "-e ssl.handshake.ciphersuite -e ssl.handshake.extensions_server_name_len "
                + "-e ssl.handshake.extensions_server_name -e x509sat.uTF8String -e x509af.utcTime";
        System.out.println("commandStr:   " + commandStr);
        // String commandStr = "sudo tshark -r /home/ubuntu2/AW/pcap/ftp.pcapng "
        // + "-T fields -e ip.src -e tcp.srcport -e udp.srcport -e ip.dst -e tcp.dstport
        // -e udp.dstport -e ip.proto "
        // + "-e ssl.handshake.random_bytes -e ssl.handshake.cipher_suites_length "
        // + "-e ssl.handshake.ciphersuite -e ssl.handshake.extensions_server_name_len "
        // + "-e ssl.handshake.extensions_server_name -e x509sat.uTF8String -e
        // x509af.utcTime";
        List<Traffic> list = new GetTrafficList().exeCmd(commandStr); // 获取所有流量
        if (list == null || list.size() == 0) {
            System.out.println("没有读取到流量");
            return ServerResponse.createByErrorMessage("没有读取到流量,可能为非pcap文件");
        }
        Identification iden = new Identification();
        List<Traffic> clientList = iden.getClientHello(list);// 获取client hello报文

        if (clientList == null) {// 若没有此流量则无法判断
            System.out.println("没有找到client hello流量");
            //待处理

            return ServerResponse.createByErrorMessage("没有找到client hello流量");
        }
        Traffic serverHello = null;
        for (Traffic clientTraffic : clientList) {
            if (iden.idenClientHello(clientTraffic) == true) {
                serverHello = iden.getServerHelloAndCertification(clientTraffic, list);

                System.out.println("yisitor  clientid:" + clientTraffic.getId() + "   sourceip:"
                        + clientTraffic.getSourceIP() + "  cipher:" + clientTraffic.getCipherSuite() + "test");
                if (serverHello != null) {
                    System.out.println("serverid:" + serverHello.getId() + "   sourceip:" + serverHello.getSourceIP()
                            + "  cipher:" + serverHello.getCipherSuite() + "test");
                    if (new Identification().idenServerHello(serverHello) == true) {
                        clientTraffic.setTor("yes");
                        System.out.println("id:" + clientTraffic.getId() + "yes");
                        serverHello.setTor("yes");
                        System.out.println("id:" + serverHello.getId() + "yes");
                        iden.setApplication(clientTraffic, list, "YES");
                    } else {
                        serverHello.setTor("NO");
                        iden.setApplication(clientTraffic, list, "NO");
                        System.out.println("id:" + serverHello.getId() + "no");
                    }
                } else {
                    System.out.println("id:" + clientTraffic.getId() + "未找到其对应的serverhello报文");
                    iden.setApplication(clientTraffic, list, "未找到该流的server hello报文，可疑");
                }

            } else {
                // set NO TOR
                clientTraffic.setTor("NO");
                // dbhelp.updateTraffic(clientTraffic);
                list = iden.setApplication(clientTraffic, list, "NO");
            }

        }
        list = iden.setSuspiciousTraffic(list);
        if (list != null && list.size() > 0) {
            CSVUtil.writeCsv(iden.getNewFilePath(filepath), list);
        }

        return ServerResponse.createBySuccess(list);

    }

    public static void main(String[] args) {
        // System.out.println(new Date());
        // // DBhelp dbhelp = new DBhelp();
        // String commandStr = "sudo tshark -r /home/ubuntu2/AW/pcap/video.pcapng "
        // + "-T fields -e ip.src -e tcp.srcport -e udp.srcport -e ip.dst -e tcp.dstport
        // -e udp.dstport -e ip.proto "
        // + "-e ssl.handshake.random_bytes -e ssl.handshake.cipher_suites_length "
        // + "-e ssl.handshake.ciphersuite -e ssl.handshake.extensions_server_name_len "
        // + "-e ssl.handshake.extensions_server_name -e x509sat.uTF8String -e
        // x509af.utcTime";
        // // List<Traffic> list = dbhelp.getAllTraffic(); //获取所有流量
        // List<Traffic> list = new GetTrafficList().exeCmd(commandStr); // 获取所有流量
        // if (list == null) {
        // System.out.println("没有读取到流量");
        // return;
        // }
        // // for(Traffic traffic:list)
        // // {
        // //// if(traffic.getCipherSuite().length()>1)
        // //// {
        // // System.out.println("id:"+traffic.getId()+" ip:"+traffic.getSourceIP() + "
        // // cip:" +traffic.getCipherSuite().length()+"test");
        // ////
        // //// }
        // //
        // // }
        // Identification iden = new Identification();
        // List<Traffic> clientList = iden.getClientHello(list);// 获取clienthello报文
        // // System.out.println("clientlist"+clientList == null);
        // if (clientList == null) {
        // System.out.println("没有找到clienthello流量");
        // return;
        // }
        // System.out.println(clientList.size());
        // for (Traffic c : clientList) {
        // System.out.println("465456");
        // System.out.println("clienthello" + c.getId() + " sourceip:" + c.getSourceIP()
        // + " cipher:"
        // + c.getCipherSuite() + "test");
        // }
        // Traffic serverHello = null;
        // for (Traffic clientTraffic : clientList) {
        // if (iden.idenClientHello(clientTraffic) == true) {
        // serverHello = iden.getServerHelloAndCertification(clientTraffic, list);
        //
        // System.out.println("yisitor clientid:" + clientTraffic.getId() + " sourceip:"
        // + clientTraffic.getSourceIP() + " cipher:" + clientTraffic.getCipherSuite() +
        // "test");
        // if (serverHello != null) {
        // System.out.println("serverid:" + serverHello.getId() + " sourceip:" +
        // serverHello.getSourceIP()
        // + " cipher:" + serverHello.getCipherSuite() + "test");
        // if (new Identification().idenServerHello(serverHello) == true) {
        // clientTraffic.setTor("yes");
        // System.out.println("id:" + clientTraffic.getId() + "yes");
        // serverHello.setTor("yes");
        // System.out.println("id:" + serverHello.getId() + "yes");
        // // dbhelp.updateTraffic(clientTraffic);
        // // dbhelp.updateTraffic(serverHello);
        // // iden.setApplication(clientTraffic,list);
        // } else {
        // serverHello.setTor("no");
        // System.out.println("id:" + serverHello.getId() + "no");
        // // dbhelp.updateTraffic(serverHello);
        // // iden.setApplication(serverHello, list);
        // }
        // } else {
        // System.out.println("id:" + clientTraffic.getId() + "未找到其对应的serverhello报文");
        // }
        //
        // } else {
        // // set NO TOR
        // clientTraffic.setTor("no");
        // // dbhelp.updateTraffic(clientTraffic);
        // // iden.setApplication(clientTraffic, list);
        // }
        //
        // }
        Identification iden = new Identification();
        Traffic hello = new Traffic();
        // hello.setId(2194);
        // hello.setSourceIP("192.168.1.197");
        // hello.setSourcePort(5555);
        // hello.setDestinationIP("192.168.1.100");
        // hello.setDestinationPort(56382);
        // hello.setProtocol(6);
        // hello.setRandomBytes("f4:63:49:a2:ee:e7:5e:74:a8:30:0a:34:7c:9a:f5:e6:d4:79:17:53:42:6f:b4:a0:28:cc:20:44");
        // hello.setCipherSuite("49200");
        // hello.setSubjectAndIssuer("www.6j6itcjorsh.com,www.pad3uygc3r5y.net");
        // hello.setBeforeAndAfter("18-06-30 00:00:00 (UTC),19-01-19 00:00:00 (UTC)");

        hello.setId(2194);
        hello.setSourceIP("192.168.1.197");
        hello.setSourcePort(5555);
        hello.setDestinationIP("192.168.1.100");
        hello.setDestinationPort(56382);
        hello.setProtocol(6);
        hello.setRandomBytes("f4:63:49:a2:ee:e7:5e:74:a8:30:0a:34:7c:9a:f5:e6:d4:79:17:53:42:6f:b4:a0:28:cc:20:44");
        hello.setCipherSuitesLength(30);
        hello.setCipherSuite("49195,49199,52393,52392,49196,49200,49162,49161,49171,49172,51,57,47,53,255");
        hello.setServerNameLength(31);
        hello.setServerName("www.nfngjtcjvqi6afrdffdqj5r.com");
        // hello.setSubjectAndIssuer("www.nfngjtcjvqi6afrdffdqj5r.com");
        // hello.setBeforeAndAfter("18-06-30 00:00:00 (UTC),19-01-19 00:00:00 (UTC)");

        // boolean f = iden.idenServerHello(hello);
        boolean f = iden.idenClientHello(hello);
        System.out.println(f);
        System.out.println(iden.getNewFilePath("/home/ubuntu2/AW/pcap/video.pcapng"));
        System.out.println(new Date());
    }

}