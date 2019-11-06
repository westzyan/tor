package com.tor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;

import com.tor.pojo.Flow;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class ArffUtil {

	/*	
		将抓取的流量形成的csv文件转换成适用于Weak的arff文件
		参数1：CSV文件路径
		参数2：arff文件名及存储路径
	 */
    public static void csvToArff(String filePath1, String filePath2) {
        String[] contents = new String[10000];
        String head = new StringBuffer().
        		append("@relation feature.arff"+ System.lineSeparator())
                .append("@attribute duration numeric"+ System.lineSeparator())   
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
            ArrayList<String[]> csvList = new ArrayList<String[]>(); 
            String csvFilePath = filePath1;
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8")); 

            reader.readHeaders(); 

            while (reader.readRecord()) { 
                csvList.add(reader.getValues());
            }
            reader.close();

            for (int row = 0; row < csvList.size(); row++) {
                contents[row] = stringBuilder.append(csvList.get(row)[7] + ",")
                	.append(csvList.get(row)[20] + ",")
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
            
            
//          System.out.println(contents[0]);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } 
        try {
            File mergeFile = new File(filePath2);
            
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
//          System.out.println("change done!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    /*
     	进行特征筛选后，将不用的特征删除，重新生成arff文件
     	参数1：CSV文件路径
     	参数2：选择的特征，以字符串形式输入，中间逗号分隔
     	参数3：新arff文件存储路径
    */
    
    public static void delete(String filePath, String features, String newFilePath){
        String[] feature = features.split(",");
//      System.out.println(feature.length);
        List<Flow> flowList = new ArrayList<Flow>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@relation ").append(new File(newFilePath).getName()).append(System.lineSeparator());
        
        for (int i = 0; i < feature.length-1; i++){
            stringBuilder.append("@attribute ").append(feature[i]).append(" numeric").append(System.lineSeparator());
        }
        	//@attribute label  {TOR,NONTOR,'?'}
        	stringBuilder.append("@attribute ").append(feature[feature.length-1]).append(" {TOR,NONTOR,'?'}").append(System.lineSeparator());
        stringBuilder.append("@data").append(System.lineSeparator());
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
            String csvFilePath = filePath;
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8")); // 一般用这编码读就可以了

            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

            while (reader.readRecord()) { // 逐行读入除表头的数据
                Flow flow = new Flow();
                flow.setFlowID(reader.getValues()[0]);
                flow.setSrcIP(reader.getValues()[1]);
                flow.setSrcPort(reader.getValues()[2]);
                flow.setDstIP(reader.getValues()[3]);
                flow.setDstPort(reader.getValues()[4]);
                flow.setProtocol(reader.getValues()[5]);
                flow.setTimeStamp(reader.getValues()[6]);
                flow.setFlowDuration(reader.getValues()[7]);
                flow.setFlowBytsPsec(reader.getValues()[20]);
                flow.setFlowPktsPsec(reader.getValues()[21]);
                flow.setFlowIATMean(reader.getValues()[22]);
                flow.setFlowIATStd(reader.getValues()[23]);
                flow.setFlowIATMax(reader.getValues()[24]);
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
//        insert(flowList);
        StringBuilder stringBuilder1 = new StringBuilder();
        for (Flow flow:flowList){
//          System.out.println("id" +flow.getFlowID());
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
//          System.out.println(fieldMap.get(feature[0]));
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
//          System.out.println("write done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

