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
					Flow features = new Flow();
					// 将csv中的特征给Features中对应的变量赋值
					features.setFlowDuration(csvList.get(row)[7]);
					features.setFlowBytsPsec(csvList.get(row)[20]);
					features.setFlowPktsPsec(csvList.get(row)[21]);
					features.setFlowIATMean(csvList.get(row)[22]);
					features.setFlowIATStd(csvList.get(row)[23]);
					features.setFlowIATMax(csvList.get(row)[24]);
					features.setFlowIATMin(csvList.get(row)[25]);
					features.setFwdIATTot(csvList.get(row)[26]);
					features.setFwdIATMean(csvList.get(row)[27]);
					features.setFwdIATStd(csvList.get(row)[28]);
					features.setFwdIATMax(csvList.get(row)[29]);
					features.setFwdIATMin(csvList.get(row)[30]);
					features.setBwdIATtot(csvList.get(row)[31]);
					features.setBwdIATMean(csvList.get(row)[32]);
					features.setBwdIATStd(csvList.get(row)[33]);
					features.setBwdIATMax(csvList.get(row)[34]);
					features.setBwdIATMin(csvList.get(row)[35]);
					features.setActiveMean(csvList.get(row)[75]);
					features.setActiveStd(csvList.get(row)[76]);
					features.setActiveMax(csvList.get(row)[77]);
					features.setActiveMin(csvList.get(row)[78]);
					features.setIdleMean(csvList.get(row)[79]);
					features.setIdleStd(csvList.get(row)[80]);
					features.setIdleMax(csvList.get(row)[81]);
					features.setIdleMin(csvList.get(row)[82]);
					// 调用分类函数进行分类，并将分类结果的标签赋值，存入List中
					features.setLabel(AlgorithmUtil.classify(train, test,flag).get(row));
					// 将该Features存入ArrayList
					display.add(features);
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
//		List<Flow> flow=getClassifyList("/home/ubuntu2/yingshirui/new/2018-11-26.csv","/home/ubuntu2/yingshirui/new/2018-11-27.csv","CfsSubsetEval+BestFirst","RandomForest");
//		System.out.println(flow.size());
//		System.out.println(showSelectFeatures("E:\\Project file\\data\\daily\\2018-11-26.csv","CfsSubsetEval+BestFirst"));
	}


}