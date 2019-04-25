package com.tor.util;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import com.tor.pojo.Traffic;


/**
 * CSV操作(导出和导入)
 */
public class CSVUtil {

    /**
     * 读取CSV文件
     */
    public static void readCsv(String filePath) {
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = "/home/ubuntu2/AW/pcap/xiaoming.csv";
            CsvReader reader = new CsvReader(csvFilePath, '\t', Charset.forName("UTF-8")); // 一般用这编码读就可以了

//			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {

                String cell = csvList.get(row)[0]; // 取得第row行第0列的数据
                String cell1 = csvList.get(row)[1]; // 取得第row行第1列的数据
                String cell2 = csvList.get(row)[2]; // 取得第row行第2列的数据
                String cell3 = csvList.get(row)[3]; // 取得第row行第2列的数据
                String cell4 = csvList.get(row)[4]; // 取得第row行第2列的数据

                System.out.println(cell + " " + cell1 + " " + cell2 + " " + cell3 + " " + cell4);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * 写入CSV文件
     */
    public static void writeCsv(String filePath, List<Traffic> list) {
        try {

            String csvFilePath = filePath;
            CsvWriter wr = new CsvWriter(csvFilePath, '\t', Charset.forName("UTF-8"));
            String[] header = {"ID", "源IP", "源端口", "目的IP", "目的端口", "协议", "随机数", "密码套件长度", "密码套件", "服务器名称长度", "服务器名称", "证书颁布者和拥有者", "证书起效时间和失效时间", "是否疑似Tor流量"};
            wr.writeRecord(header);
//			String[] contents = { "数据库", "数据表", "编码格式", "ddddddddd" };
            String[] contents = new String[14];
            for (Traffic traffic : list) {
                contents[0] = traffic.getId() + "";
                contents[1] = traffic.getSourceIP();
                contents[2] = traffic.getSourcePort() + "";
                contents[3] = traffic.getDestinationIP();
                contents[4] = traffic.getDestinationPort() + "";
                contents[5] = traffic.getProtocol() + "";
                contents[6] = traffic.getRandomBytes();
                contents[7] = traffic.getCipherSuitesLength() + "";
                contents[8] = traffic.getCipherSuite();
                contents[9] = traffic.getServerNameLength() + "";
                contents[10] = traffic.getServerName();
                contents[11] = traffic.getSubjectAndIssuer();
                contents[12] = traffic.getBeforeAndAfter();
                contents[13] = traffic.getTor();
                wr.writeRecord(contents);
            }
            wr.close();
            System.out.println("write done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String commandStr = "sudo tshark -r /home/ubuntu2/AW/pcap/09-22.pcap "
                + "-T fields -e ip.src -e tcp.srcport -e udp.srcport -e ip.dst -e tcp.dstport -e udp.dstport -e ip.proto "
                + "-e ssl.handshake.random_bytes -e ssl.handshake.cipher_suites_length "
                + "-e ssl.handshake.ciphersuite -e ssl.handshake.extensions_server_name_len "
                + "-e ssl.handshake.extensions_server_name -e x509sat.uTF8String -e x509af.utcTime";
//		writeCsv("a",new GetList().exeCmd(commandStr));
        readCsv("111");
    }

}