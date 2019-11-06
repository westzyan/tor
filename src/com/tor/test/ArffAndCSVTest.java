package com.tor.test;

import com.csvreader.CsvReader;
import com.tor.pojo.Flow;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;

public class ArffAndCSVTest {

    public static String txt2String(String filePath) {
        File file = new File(filePath);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            System.out.println("hahah");
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                System.out.println(s);
                if (!s.startsWith("@") && !StringUtils.isEmpty(s)) {
                    String[] feature = s.split(",");

                    result.append(s + System.lineSeparator());
                }

            }
            result.append(666);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public static void readCsv(String filePath) {
        String[] contents = new String[10000];
        String head = new StringBuffer().append("@attribute flowID numeric"+ System.lineSeparator())
                .append("@attribute srcIP numeric"+ System.lineSeparator())
                .append("@attribute srcPort numeric"+ System.lineSeparator())
                .append("@attribute dstIP numeric"+ System.lineSeparator())
                .append("@attribute dstPort numeric"+ System.lineSeparator())
                .append("@attribute protocol numeric"+ System.lineSeparator())
                .append("@attribute timestamp numeric"+ System.lineSeparator())
                .append("@attribute flowDuration numeric"+ System.lineSeparator())
                .append("@attribute flowBytsPsec numeric"+ System.lineSeparator())
                .append("@attribute flowPktsPsec numeric"+ System.lineSeparator())
                .append("@attribute flowIATMean numeric"+ System.lineSeparator())
                .append("@attribute flowIATStd numeric"+ System.lineSeparator())
                .append("@attribute flowIATMax numeric"+ System.lineSeparator())
                .append("@attribute flowIATMin numeric"+ System.lineSeparator())
                .append("@attribute fwdIATTot numeric"+ System.lineSeparator())
                .append("@attribute fwdIATMean numeric"+ System.lineSeparator())
                .append("@attribute fwdIATStd numeric"+ System.lineSeparator())
                .append("@attribute fwdIATMax numeric"+ System.lineSeparator())
                .append("@attribute fwdIATMin numeric"+ System.lineSeparator())
                .append("@attribute bwdIATtot numeric"+ System.lineSeparator())
                .append("@attribute bwdIATMean numeric"+ System.lineSeparator())
                .append("@attribute bwdIATStd numeric"+ System.lineSeparator())
                .append("@attribute bwdIATMax numeric"+ System.lineSeparator())
                .append("@attribute bwdIATMin numeric"+ System.lineSeparator())
                .append("@attribute activeMean numeric"+ System.lineSeparator())
                .append("@attribute activeStd numeric"+ System.lineSeparator())
                .append("@attribute activeMax numeric"+ System.lineSeparator())
                .append("@attribute activeMin numeric"+ System.lineSeparator())
                .append("@attribute idleMean numeric"+ System.lineSeparator())
                .append("@attribute idleStd numeric"+ System.lineSeparator())
                .append("@attribute idleMax numeric"+ System.lineSeparator())
                .append("@attribute idleMin numeric"+ System.lineSeparator())
                .append("@attribute label {TOR,NONTOR}"+ System.lineSeparator())
                .append("@data"+ System.lineSeparator())
                .toString();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = "/home/ubuntu2/2018-11-26_Flow.csv";
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8")); // 一般用这编码读就可以了

            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {
                contents[row] = stringBuilder.append(csvList.get(row)[0] + ",").append(csvList.get(row)[1] + ",").append(csvList.get(row)[2] + ",")
                        .append(csvList.get(row)[3] + ",").append(csvList.get(row)[4] + ",").append(csvList.get(row)[5] + ",")
                        .append(csvList.get(row)[6] + ",").append(csvList.get(row)[7] + ",")
                        .append(csvList.get(row)[21] + ",")
                        .append(csvList.get(row)[22] + ",").append(csvList.get(row)[23] + ",").append(csvList.get(row)[24] + ",")
                        .append(csvList.get(row)[25] + ",").append(csvList.get(row)[26] + ",").append(csvList.get(row)[27] + ",")
                        .append(csvList.get(row)[28] + ",").append(csvList.get(row)[29] + ",").append(csvList.get(row)[30] + ",")
                        .append(csvList.get(row)[31] + ",").append(csvList.get(row)[32] + ",").append(csvList.get(row)[33] + ",")
                        .append(csvList.get(row)[34] + ",").append(csvList.get(row)[35] + ",").append(csvList.get(row)[75] + ",")
                        .append(csvList.get(row)[76] + ",").append(csvList.get(row)[77] + ",").append(csvList.get(row)[78] + ",")
                        .append(csvList.get(row)[79] + ",").append(csvList.get(row)[80] + ",").append(csvList.get(row)[81] + ",")
                        .append(csvList.get(row)[82] + ",").append(csvList.get(row)[83]).append(System.lineSeparator()).toString();
            }
            System.out.println(contents[0]);


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        try {
            File mergeFile = new File("/home/ubuntu2/test.arff");
            //文件不存在时候，主动创建文件。
            if (!mergeFile.exists()) {
                mergeFile.createNewFile();
            }

            FileWriter fw = new FileWriter(mergeFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(head);
            for (int i = 0; i < contents.length; i++) {
                if (contents[i] != null) {
                    bw.write(contents[i]);
                }
            }
            bw.close();
            fw.close();
            System.out.println("test2 done!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public static void merge(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        StringBuilder result = new StringBuilder();
        String[] content1 = new String[30000];
        String[] content2 = new String[30000];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file1));//构造一个BufferedReader类来读取文件
            String s = null;
            content1[0] = "Fans";
            int count = 1;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                System.out.println(s);
                content1[count] = s;
                count++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(file2));//构造一个BufferedReader类来读取文件
            String s = null;
            content2[0] = "Followers";
            int count = 1;
            while ((s = br1.readLine()) != null) {//使用readLine方法，一次读一行
//                System.out.println(s);
                content2[count] = s;
                count++;
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(content1);
        System.out.println(content2);
        StringBuilder sb = new StringBuilder();
        try {
            File mergeFile = new File("/home/ubuntu2/merge.csv");
            //文件不存在时候，主动穿件文件。
            if (!mergeFile.exists()) {
                mergeFile.createNewFile();
            }

            FileWriter fw = new FileWriter(mergeFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < content1.length; i++) {
                if (content1[i] != null) {
                    sb = sb.append(content1[i] + ",").append(content2[i]).append(System.lineSeparator());
                }
            }
            bw.write(sb.toString());
            bw.close();
            fw.close();
            System.out.println("test2 done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void delete(String filePath, String features, String newFilePath){
        String[] feature = features.split(",");
        List<Flow> flowList = new ArrayList<Flow>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("relation ").append(new File(newFilePath).getName()).append(System.lineSeparator());
        for (int i = 0; i < feature.length; i++){
            stringBuilder.append("@attribute ").append(feature[i]).append(" numeric").append(System.lineSeparator());
        }
        stringBuilder.append("@data").append(System.lineSeparator());
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = filePath;
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8")); // 一般用这编码读就可以了

            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
//                Flow flow = new Flow();
//                flow.setFlowID(reader.getValues()[0]);
                csvList.add(reader.getValues());
                Flow flow = new Flow();
                flow.setFlowID(reader.getValues()[0]);
                flow.setSrcIP(reader.getValues()[1]);
                flow.setSrcPort(reader.getValues()[2]);
                flow.setDstIP(reader.getValues()[3]);
                flow.setDstPort(reader.getValues()[4]);
                flow.setProtocol(reader.getValues()[5]);
                flow.setTimeStamp(reader.getValues()[6]);
                flow.setFlowDuration(reader.getValues()[7]);
                flow.setFlowBytsPsec(reader.getValues()[21]);
                flow.setFlowPktsPsec(reader.getValues()[22]);
                flow.setFlowIATMean(reader.getValues()[23]);
                flow.setFlowIATStd(reader.getValues()[24]);
                flow.setFlowIATMin(reader.getValues()[25]);
                flow.setFwdIATTot(reader.getValues()[26]);
                flow.setFwdIATMean(reader.getValues()[27]);
                flow.setFwdIATStd(reader.getValues()[28]);
                flow.setFwdIATMax(reader.getValues()[29]);
                flow.setFwdIATMin(reader.getValues()[30]);
                flow.setBwdIATtot(reader.getValues()[31]);
                flow.setBwdIATMean(reader.getValues()[32]);
                flow.setBwdIATStd(reader.getValues()[33]);
                flow.setBwdIATMax(reader.getValues()[34]);
                flow.setBwdIATMin(reader.getValues()[35]);
                flow.setActiveMean(reader.getValues()[75]);
                flow.setActiveStd(reader.getValues()[76]);
                flow.setActiveMax(reader.getValues()[77]);
                flow.setActiveMin(reader.getValues()[78]);
                flow.setIdleMean(reader.getValues()[79]);
                flow.setIdleStd(reader.getValues()[80]);
                flow.setIdleMax(reader.getValues()[81]);
                flow.setIdleMin(reader.getValues()[82]);
                flow.setLabel(reader.getValues()[83]);

                flowList.add(flow);
            }
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        for (Flow flow:flowList){
            System.out.println("id" +flow.getFlowID());
            Map<String,Object> fieldMap = new HashMap<String,Object>();
            Class flowClass = flow.getClass();
            Field[] fs = flowClass.getDeclaredFields();
            for(Field field :fs){
                String fieldName = field.getName();
                String firstLetter = fieldName.substring(0,1).toUpperCase();
                String getMethodName = "get"+firstLetter+fieldName.substring(1);
                try {
                    Method getMethod = flowClass.getMethod(getMethodName,new Class[]{});
                    Object value = getMethod.invoke(flow, new Object[] {}); //这个对象字段get方法的值
                    fieldMap.put(fieldName,value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < feature.length; i++){
                if(fieldMap.get(feature[i]) != null){
                    stringBuilder1.append(fieldMap.get(feature[i])).append(",");
                }
            }
            stringBuilder1.deleteCharAt(stringBuilder1.length() - 1);
            stringBuilder1.append(System.lineSeparator());
        }

        try {
            File mergeFile = new File(newFilePath);
            //文件不存在时候，主动创建文件。
            if (!mergeFile.exists()) {
                mergeFile.createNewFile();
            }

            FileWriter fw = new FileWriter(mergeFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(stringBuilder.toString());
            bw.write(stringBuilder1.toString());
            bw.close();
            fw.close();
            System.out.println("write done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static String runCmd(String cmdpath, String cmd) {
        String result="";
        File dir = new File(cmdpath);
        try {
            Process ps = Runtime.getRuntime().exec(cmd, null, dir);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), Charset.forName("GBK")));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result+=line+"\n";
            }

            br.close();
            System.out.println("close ... ");
            ps.waitFor();
            System.out.println("wait over ...");

            return result;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("child thread donn");
        return null;
    }








    public static void main(String[] args) {
//        txt2String("/home/ubuntu2/AW/数据包/dataset-tor/Tor.arff");
//        System.out.println(txt2String("/home/ubuntu2/AW/数据包/dataset-tor/Tor.arff"));
//        readCsv("s");
//        merge("/home/ubuntu2/Fans_result.csv", "/home/ubuntu2/Followers_result.csv");
//        Map<String,Object> fieldMap = new HashMap<String,Object>();
//        Flow flow = new Flow();
//        flow.setFlowID("666");
//        flow.setSrcPort("222");
//        flow.setSrcIP("111");
//        Class flowClass = flow.getClass();
//        Field[] fs = flowClass.getDeclaredFields();
//        for(Field field :fs){
//            String fieldName = field.getName();
//            String firstLetter = fieldName.substring(0,1).toUpperCase();
//            String getMethodName = "get"+firstLetter+fieldName.substring(1);
//            try {
//                Method getMethod = flowClass.getMethod(getMethodName,new Class[]{});
//                Object value = getMethod.invoke(flow, new Object[] {}); //这个对象字段get方法的值
//                fieldMap.put(fieldName,value);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        Iterator<Map.Entry<String, Object>> iterator = fieldMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//


//        delete("/home/ubuntu2/2018-11-26_Flow.csv","flowID,srcIP","/home/ubuntu2/test.arff");
        runCmd("/home/ubuntu2","ping www.baidu.com");

    }
}
