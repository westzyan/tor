package com.tor.util;

import java.util.List;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;

import com.tor.pojo.Flow;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassifyList {

	public   List<Flow> getClassifyList(String trainFilePath, String testFilePath, String featureSelectAlgorithm, String algorithm) {
		//分类算法
		int flag=0;
		if(algorithm.equals("C4.5"))
			flag=1;
		else if(algorithm.equals("RandomForest"))
			flag=2;
		/*
		 	选择的特征
		 	参数1：csv训练文件路径
		 	参数2：使用的特征选择方法

		 */
		String selectFeatures;
		try {
			File file=new File(trainFilePath);
			String traindelete=file.getParent()+File.separator+"trainFiledelete.arff";//训练文件删除多余特征之后的临时文件

			String testdelete=file.getParent()+File.separator+"testFiledelete.arff";//测试文件删除多余特征之后的临时文件
			selectFeatures = ClassifyFeatures.getClassifyFeature(trainFilePath,featureSelectAlgorithm);
			//对csv训练文件保留选择的特征，删除多余的特征，并存储为arff文件
			ArffUtil.delete(trainFilePath, selectFeatures, traindelete);
			//对csv测试文件保留选择的特征，删除多余的特征，并存储为arff文件
			ArffUtil.delete(testFilePath,selectFeatures, testdelete);

			ArrayList<Flow> display = new ArrayList<Flow>();
			try {
				ArrayList<String[]> csvList = new ArrayList<String[]>();
				String csvFilePath = testFilePath;
				CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));

				reader.readHeaders();

				while (reader.readRecord()) {
					csvList.add(reader.getValues());
				}
				reader.close();

				// loading training data,修改过的训练文件
				DataSource sourceTrain = new DataSource(traindelete);
				Instances train = sourceTrain.getDataSet();
				if (train.classIndex() == -1)
					// 设置关系为最后一列数据
					train.setClassIndex(train.numAttributes() - 1);

				// load testing data,修改过的测试文件
				DataSource sourceTest = new DataSource(testdelete);
				Instances test = sourceTest.getDataSet();
				if (test.classIndex() == -1)
					// 设置关系为最后一列数据
					test.setClassIndex(test.numAttributes() - 1);

				for (int row = 0; row < csvList.size(); row++) {
					Flow flow = new Flow();
					// 将csv中的特征给Features中对应的变量赋值
					flow.setFlowID(csvList.get(row)[0]);
					flow.setSrcIP(csvList.get(row)[1]);
					flow.setSrcPort(csvList.get(row)[2]);
					flow.setDstIP(csvList.get(row)[3]);
					flow.setDstPort(csvList.get(row)[4]);
					flow.setProtocol(csvList.get(row)[5]);
					flow.setTimeStamp(csvList.get(row)[6]);
					flow.setFlowDuration(csvList.get(row)[7]);
					flow.setFlowBytsPsec(csvList.get(row)[20]);
					flow.setFlowPktsPsec(csvList.get(row)[21]);
					flow.setFlowIATMean(csvList.get(row)[22]);
					flow.setFlowIATStd(csvList.get(row)[23]);
					flow.setFlowIATMax(csvList.get(row)[24]);
					flow.setFlowIATMin(csvList.get(row)[25]);
					flow.setFwdIATTot(csvList.get(row)[26]);
					flow.setFwdIATMean(csvList.get(row)[27]);
					flow.setFwdIATStd(csvList.get(row)[28]);
					flow.setFwdIATMax(csvList.get(row)[29]);
					flow.setFwdIATMin(csvList.get(row)[30]);
					flow.setBwdIATtot(csvList.get(row)[31]);
					flow.setBwdIATMean(csvList.get(row)[32]);
					flow.setBwdIATStd(csvList.get(row)[33]);
					flow.setBwdIATMax(csvList.get(row)[34]);
					flow.setBwdIATMin(csvList.get(row)[35]);
					flow.setActiveMean(csvList.get(row)[75]);
					flow.setActiveStd(csvList.get(row)[76]);
					flow.setActiveMax(csvList.get(row)[77]);
					flow.setActiveMin(csvList.get(row)[78]);
					flow.setIdleMean(csvList.get(row)[79]);
					flow.setIdleStd(csvList.get(row)[80]);
					flow.setIdleMax(csvList.get(row)[81]);
					flow.setIdleMin(csvList.get(row)[82]);
					// 调用分类函数进行分类，并将分类结果的标签赋值，存入List中
					flow.setLabel(AlgorithmUtil.classify(train, test,flag).get(row));
					// 将该Features存入ArrayList
					display.add(flow);
					//打印测试
//					for (int i = 0; i < display.size(); i++) {
//						System.out.printf("%-50s %-50s %-50s %-50s ",
//								display.get(i).getFlowIATMax(),
//								display.get(i).getBwdIATMean(),
//								display.get(i).getBwdIATStd(),
//								display.get(i).getLabel());
//								System.out.println();
//					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println(ex);
			}


			return display;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






		return null;


	}
	//返回特征选择的结果
	public  String showSelectFeatures(String trainFilePath,String featureSelectAlgorithm)  {
		try {
			return ClassifyFeatures.getClassifyFeature(trainFilePath,featureSelectAlgorithm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		ClassifyList classifyList = new ClassifyList();
		List<Flow> flow = classifyList.getClassifyList("/home/ubuntu2/yingshirui/new/2018-11-26.csv","/home/ubuntu2/yingshirui/new/2018-11-27.csv","CfsSubsetEval+BestFirst","RandomForest");
		System.out.println(flow.size());
		System.out.println(flow.get(0).getSrcIP());
		System.out.println(classifyList.showSelectFeatures("/home/ubuntu2/yingshirui/new/2018-11-26.csv","CfsSubsetEval+BestFirst"));
	}


}