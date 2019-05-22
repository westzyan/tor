package com.tor.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.tor.pojo.Traffic;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindBridge {
    /**
     * 读取CSV文件
     */
    public static Map<String, String> readCsv(String filePath) {
//        List<Traffic> list = new ArrayList<Traffic>();
//        Set set = new HashSet();
        Map<String, String> ipMap = new HashMap<String, String>();
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = filePath;
            CsvReader reader = new CsvReader(csvFilePath, '\t', Charset.forName("UTF-8")); // 一般用这编码读就可以了

//			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                if(reader.getValues() != null )
                csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {

                String cell = csvList.get(row)[0]; // 取得第row行第0列的数据
                String cell1 = csvList.get(row)[1]; // 取得第row行第1列的数据
                String cell2 = csvList.get(row)[2]; // 取得第row行第2列的数据
                String cell3 = csvList.get(row)[3]; // 取得第row行第3列的数据
                String cell4 = csvList.get(row)[4]; // 取得第row行第4列的数据
//                System.out.println(row + ": " + cell + " " + cell1 + " " + cell2 + " " + cell3 + " " + cell4 );
                if (cell.equals("155.138.223.47")){
                    ipMap.put(cell2,cell3);
                }else {
                    ipMap.put(cell,cell1);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        System.out.println("map.size:"+ipMap.size());
        return ipMap;
    }

    public static Map<String, String> readCsv(String filePath,int flag) {
//        List<Traffic> list = new ArrayList<Traffic>();
//        Set set = new HashSet();
        Map<String, String> ipMap = new HashMap<String, String>();
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = filePath;
            CsvReader reader = new CsvReader(csvFilePath, '\t', Charset.forName("UTF-8")); // 一般用这编码读就可以了

//			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                if(reader.getValues() != null )
                    csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {
                String[] port = csvList.get(row)[2].split(",");

                String cell = csvList.get(row)[0]; // 取得第row行第0列的数据
                String cell1 = port[0]; // 取得第row行第1列的数据
                String cell2 = csvList.get(row)[1]; // 取得第row行第2列的数据
                String cell3 = port[1]; // 取得第row行第3列的数据
                String cell4 = csvList.get(row)[3]; // 取得第row行第4列的数据
//                System.out.println(row + ": " + cell + " " + cell1 + " " + cell2 + " " + cell3 + " " + cell4 );


                if (cell.equals("155.138.223.47")){
                    ipMap.put(cell2,cell3);
                }else {
                    ipMap.put(cell,cell1);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        System.out.println("map.size:"+ipMap.size());
        return ipMap;
    }


    public static Map<String, String> readCsv(String filePath,String flag) {
//        List<Traffic> list = new ArrayList<Traffic>();
//        Set set = new HashSet();
        Map<String, String> ipMap = new HashMap<String, String>();
        try {

            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = "/home/ubuntu2/AW/主动探测/Tor_query_EXPORT.csv";
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8")); // 一般用这编码读就可以了

			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                if(reader.getValues() != null )
                    csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {
                String cell = csvList.get(row)[4]; // 取得第row行第0列的数据
                String cell1 = csvList.get(row)[6]; // 取得第row行第1列的数据
//                System.out.println(row + ": " + cell + " " + cell1);
                ipMap.put(cell,cell1);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("map.size:"+ipMap.size());
        return ipMap;
    }

    public  static  void spider(){
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL("https://www.rndsystems.com/cn");//爬取的网址、这里爬取的是一个生物网站
            urlconn = url.openConnection();
            pw = new PrintWriter(new FileWriter("/home/ubuntu2/SiteURL.txt"), true);//将爬取到的链接放到D盘的SiteURL文件中
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    pw.println(buf_m.group());
                }
            }
            System.out.println("爬取成功^_^");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }

    public static void writeCsv(String filePath, Map<String,String> map) {
        try {

            String csvFilePath = filePath;
            CsvWriter wr = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            String[] header = {"IP", "port"};
            wr.writeRecord(header);
            String[] contents = new String[2];
            for (Map.Entry<String, String> entry: map.entrySet()){
                contents[0] = entry.getKey();
                contents[1] = entry.getValue();
//                System.out.println(" ip:" + entry.getKey()+"   port:" + entry.getValue());
                wr.writeRecord(contents);
            }
            wr.close();
            System.out.println("write done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String,String> getBridge(Map<String,String> delayAndBridgeMap,Map<String,String> delayMap,Map<String,String> bridgeMap){
        for (Map.Entry<String, String> entry: delayAndBridgeMap.entrySet()){
            boolean contains = delayMap.containsKey(entry.getKey());
            if (!contains){
                System.out.println(" ip:" + entry.getKey()+"   port:" + entry.getValue());
                bridgeMap.put(entry.getKey(),entry.getValue());
            }
        }
        return bridgeMap;
    }








    public static Map<String,String> txt2String(String filePath) {
        File file = new File(filePath);
        StringBuilder result = new StringBuilder();
        Map<String, String> test = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
//            System.out.println("hahah");
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                System.out.println(s);
//                if (!s.startsWith("@") && !StringUtils.isEmpty(s)) {
//                    String[] feature = s.split(",");
//
//                    result.append(s + System.lineSeparator());
//                }
                String ipPort = s.substring(44,65).replace(" ","");
                String[] ipAndPort = ipPort.split(":");
                test.put(ipAndPort[0],ipAndPort[1]);

//                System.out.println(ipPort);
            }
            result.append(666);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }













    public static void main(String[] args) {

        Map<String,String> bridgeMap = new HashMap<String,String>();
        Map<String,String> delayAndBridgeMap = txt2String("/home/ubuntu2/AW/Bridge/torPcap/20190521/test.csv");
//        Map<String,String> delayAndBridgeMap1 = FindBridge.readCsv("/home/ubuntu2/AW/Bridge/torPcap/20190419/links.csv");
//        Map<String,String> delayAndBridgeMap2 = FindBridge.readCsv("/home/ubuntu2/AW/Bridge/torPcap/20190421/links.csv");
        Map<String,String> delayMap = FindBridge.readCsv("dd","666");
        int count = 0;
        for (Map.Entry<String, String> entry: delayAndBridgeMap.entrySet()){
            boolean contains = delayMap.containsKey(entry.getKey());
            if (!contains){
                count++;
//                System.out.println(count+ " ip:" + entry.getKey()+"   port:" + entry.getValue());
                System.out.println(entry.getKey()+":"+entry.getValue());
                bridgeMap.put(entry.getKey(),entry.getValue());
            }
        }
        System.out.println("bridge number："+ count);
//        for (Map.Entry<String, String> entry: delayAndBridgeMap1.entrySet()){
//            boolean contains = delayMap.containsKey(entry.getKey());
//            if (!contains){
//                count++;
//                System.out.println(count+ " ip:" + entry.getKey()+"   port:" + entry.getValue());
//                bridgeMap.put(entry.getKey(),entry.getValue());
//            }
//        }
//        System.out.println("bridge number："+ count);
//        for (Map.Entry<String, String> entry: delayAndBridgeMap2.entrySet()){
//            boolean contains = delayMap.containsKey(entry.getKey());
//            if (!contains){
//                count++;
//                System.out.println(count+ " ip:" + entry.getKey()+"   port:" + entry.getValue());
//                bridgeMap.put(entry.getKey(),entry.getValue());
//            }
//        }
        System.out.println("bridge number："+ count);
        FindBridge findBridge = new FindBridge();
        findBridge.writeCsv("/home/ubuntu2/AW/Bridge/torPcap/bridge11111111",bridgeMap);


//        String s = "tcp        0      0 155.138.223.47:9001     ";
//        String s1 = "tcp        0      0 155.138.223.47:9001     141.255.166.150:60935";
//        System.out.println(s.length());
//        System.out.println(s1.length());

    }



}
