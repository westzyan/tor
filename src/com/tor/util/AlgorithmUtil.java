package com.tor.util;

import weka.attributeSelection.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.meta.*;
import weka.classifiers.trees.*;
import java.util.*;


public class AlgorithmUtil {

	/*
	 * 特征选择算法 flat=1:CfsSubsetEval+BestFirst&10-fold cross-validation
	 * flat=2:Infogain+Ranker
	 */
	protected static String featureSelection(Instances data, int flag) throws Exception {
		// 1:CfsSubsetEval+BestFirst&10-fold cross-validation
		if (flag == 1) {
//			System.out.println("\nfeature selection algorithm:CfsSubsetEval+BestFirst&10-fold cross-validation");
			// 特征选择
			AttributeSelection attsel = new AttributeSelection();
			// attribute evaluator:CfsSubsetEval
			CfsSubsetEval eval = new CfsSubsetEval();
			// search method:BestFirst
			BestFirst search = new BestFirst();
			attsel.setEvaluator(eval);
			attsel.setSearch(search);
			attsel.SelectAttributes(data);
			// 显示特征选择的结果
			int[] attrIndex = search.search(eval, data);
			StringBuffer attrInfoGainInfo = new StringBuffer();

			for (int i = 0; i < attrIndex.length; i++) {

//				attrInfoGainInfo.append(eval.evaluateAttribute(attrIndex[i]));

//				attrInfoGainInfo.append("\t");

				attrInfoGainInfo.append((data.attribute(attrIndex[i]).name()));

				attrInfoGainInfo.append(",");

			}
			attrInfoGainInfo.deleteCharAt(attrInfoGainInfo.toString().length()-1);
			attrInfoGainInfo.append(",label");
			//显示特征选择的结果
			System.out.println(attrInfoGainInfo.toString());
			return attrInfoGainInfo.toString();
		}

		// 2:Infogain+Ranker
		else if (flag == 2) {
//			System.out.println("\nfeature selection algorithm:Infogain+Ranker");
			// 特征选择
			AttributeSelection attsel = new AttributeSelection();
			// attribute evaluator:Infogain
			InfoGainAttributeEval eval = new InfoGainAttributeEval();
			// search method:Ranker
			Ranker search = new Ranker();
			attsel.setEvaluator(eval);
			attsel.setSearch(search);
			attsel.SelectAttributes(data);
			// 特征选择：从0开始计数
			int[] attrIndex = search.search(eval, data);
			
			StringBuffer attrInfoGainInfo = new StringBuffer();

//			attrInfoGainInfo.append("Ranked attributes:\n");

			for (int i = 0; i < attrIndex.length; i++) {
				
				if(i>0) {
					if(eval.evaluateAttribute(attrIndex[i])-eval.evaluateAttribute(attrIndex[i-1])<-0.185)
						break;
				}
				//输出特征的权值，暂时不用
//				attrInfoGainInfo.append(eval.evaluateAttribute(attrIndex[i]));

//				attrInfoGainInfo.append("\t");
				
				attrInfoGainInfo.append((data.attribute(attrIndex[i]).name()));

				attrInfoGainInfo.append(",");

			}
			attrInfoGainInfo.deleteCharAt(attrInfoGainInfo.toString().length()-1);
			attrInfoGainInfo.append(",label");
			// 显示特征选择的结果
			System.out.println(attrInfoGainInfo.toString());
			return attrInfoGainInfo.toString();
		}
		return null;

	}

	/**
	 * 分类算法：将结果存入ArrayList flag=1:J48(C4.5) flag=2:RandomForest
	 * @param train
	 * @param test
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	protected static ArrayList<String> classify(Instances train, Instances test, int flag) throws Exception {
		// 创建ArrayList作为存储容器
		ArrayList<String> result = new ArrayList<String>();
		FilteredClassifier fc = new FilteredClassifier();
		// 1.J48(C4.5)
		if (flag == 1) {
			J48 j48 = new J48();
			fc.setClassifier(j48);
			fc.buildClassifier(train);
			// 实例总数
			int sum = test.numInstances();
			// 输出预测结果
			for (int i = 0; i < sum; i++) {

				double pred = fc.classifyInstance(test.instance(i));
				String predicted = test.classAttribute().value((int) pred);
				// 将分类结果加入ArrayList
				result.add(predicted);
//				System.out.println("No:" + i + "  predicted:" + predicted);

			}

		}
		// 2.RandomForest
		else if (flag == 2) {

			RandomForest rf = new RandomForest();
			fc.setClassifier(rf);
			fc.buildClassifier(train);
			// 实例总数
			int sum = test.numInstances();
			// 创建ArrayList作为存储容器
			// 将结果存入ArrayList
			for (int i = 0; i < sum; i++) {

				double pred = fc.classifyInstance(test.instance(i));
				String predicted = test.classAttribute().value((int) pred);
				// ������������ArrayList
				result.add(predicted);
//			    System.out.println("No:"+i+"  predicted:"+predicted);  

			}

		}
		// 返回ArrayList
		return result;

	}


}