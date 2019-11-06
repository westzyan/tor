package com.tor.util;


import java.io.File;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassifyFeatures {
	/*
	 	featureSelect=CfsSubsetEval+BestFirst :flag=1
	 	featureSelect=Infogain+Ranker :flag=2
	 	其他 :flag=0

	 */
	public static String getClassifyFeature(String trainFilePath,String featureSelectAlgorithm)  {
		File file=new File(trainFilePath);
		String convertFile=file.getParent()+File.separator+"trainFilecsvToArff.arff";
		//从csv转换到arff的临时文件
//		String convertFile="D:\\test\\trainFilecsvToArff.arff";
		/*
		 	参数1：csv文件路径
		 	参数2：arff文件名及存储路径
		 */
		ArffUtil.csvToArff(trainFilePath,convertFile);
		int flag = 0;
		if(featureSelectAlgorithm.equals("CfsSubsetEval+BestFirst")) {
			flag=1;
		}
		else if (featureSelectAlgorithm.equals("Infogain+Ranker")) {
			flag=2;
		}
		DataSource sourceTrain = null;
		try {
			sourceTrain = new DataSource(convertFile);
			Instances train = sourceTrain.getDataSet();
			if (train.classIndex() == -1)
				// 设置关系为最后一列数据
				train.setClassIndex(train.numAttributes() - 1);
			//1.CfsSubsetEval+BestFirst
			if (flag == 1) {
				AttributeSelection attsel = new AttributeSelection();
				// attribute evaluator:CfsSubsetEval
				CfsSubsetEval eval = new CfsSubsetEval();
				// search method:BestFirst
				BestFirst search = new BestFirst();
				attsel.setEvaluator(eval);
				attsel.setSearch(search);
				attsel.SelectAttributes(train);
				// 显示特征选择的结果
				int[] attrIndex = search.search(eval, train);
				StringBuffer attrInfoGainInfo = new StringBuffer();

				for (int i = 0; i < attrIndex.length; i++) {

					attrInfoGainInfo.append((train.attribute(attrIndex[i]).name()));

					attrInfoGainInfo.append(",");

				}
				attrInfoGainInfo.deleteCharAt(attrInfoGainInfo.toString().length()-1);
				attrInfoGainInfo.append(",label");
				// 显示特征选择的结果
//			System.out.println(attrInfoGainInfo.toString());
				return attrInfoGainInfo.toString();
			}
			// 2:Infogain+Ranker
			else if (flag == 2) {
				// 特征选择
				AttributeSelection attsel = new AttributeSelection();
				// attribute evaluator:Infogain
				InfoGainAttributeEval eval = new InfoGainAttributeEval();
				// search method:Ranker
				Ranker search = new Ranker();
				attsel.setEvaluator(eval);
				attsel.setSearch(search);
				attsel.SelectAttributes(train);
				// 特征选择：从0开始计数
				int[] attrIndex = search.search(eval, train);

				StringBuffer attrInfoGainInfo = new StringBuffer();

				for (int i = 0; i < attrIndex.length; i++) {

					if(i>0) {
						if(eval.evaluateAttribute(attrIndex[i])-eval.evaluateAttribute(attrIndex[i-1])<-0.185)
							break;
					}

					attrInfoGainInfo.append((train.attribute(attrIndex[i]).name()));

					attrInfoGainInfo.append(",");

				}
				attrInfoGainInfo.deleteCharAt(attrInfoGainInfo.toString().length()-1);
				attrInfoGainInfo.append(",label");
				// 显示特征选择的结果
//			System.out.println(attrInfoGainInfo.toString());
				return attrInfoGainInfo.toString();
			}
			else
				return "false";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static void main(String[] args) throws Exception {
//		String s=getClassifyFeature("E:\\Project file\\data\\daily\\2018-11-26.csv"
//				,"CfsSubsetEval+BestFirst"
//				);
//		System.out.println(s);
//	}
}