package com.tor.util;


import com.tor.common.ServerResponse;
import com.tor.pojo.Flow;
import weka.attributeSelection.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.meta.*;
import weka.classifiers.trees.*;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ClassifyUtil {
    /*
     * 特征选择算法
     * flat=1:CfsSubsetEval+BestFirst&10-fold cross-validation
     * flat=2:Infogain+Ranker
     */
    protected static void featureSelection(Instances data, int flag) throws Exception {
        // 1:CfsSubsetEval+BestFirst&10-fold cross-validation
        if (flag == 1) {
            System.out.println("\nfeature selection algorithm:CfsSubsetEval+BestFirst&10-fold cross-validation");
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
            System.out.println(attsel.toResultsString());
        }
        // 2:Infogain+Ranker
        else if (flag == 2) {
            System.out.println("\nfeature selection algorithm:Infogain+Ranker");
            // 特征选择
            AttributeSelection attsel = new AttributeSelection();
            // attribute evaluator:CfsSubsetEval
            InfoGainAttributeEval eval = new InfoGainAttributeEval();
            // search method:BestFirst
            Ranker search = new Ranker();
            attsel.setEvaluator(eval);
            attsel.setSearch(search);
            attsel.SelectAttributes(data);

            // 特征选择：从0开始计数
            int[] attrIndex = search.search(eval, data);

            StringBuffer attrIndexInfo = new StringBuffer();

            StringBuffer attrInfoGainInfo = new StringBuffer();

            attrInfoGainInfo.append("Ranked attributes:\n");

            for (int i = 0; i < attrIndex.length; i++) {

                attrInfoGainInfo.append(eval.evaluateAttribute(attrIndex[i]));

                attrInfoGainInfo.append("\t");

                attrInfoGainInfo.append((data.attribute(attrIndex[i]).name()));

                attrInfoGainInfo.append("\n");

            }
            // 显示特征选择的结果
            System.out.println(attrIndexInfo.toString());

            System.out.println(attrInfoGainInfo.toString());
        }

    }

    /*
     * 分类算法：将结果存入ArrayList
     * flag=1:J48(C4.5)
     * flag=2:RandomForest
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
                // 将分类结果加入ArrayList
                result.add(predicted);
//			    System.out.println("No:"+i+"  predicted:"+predicted);
            }
        }
        // 返回ArrayList
        return result;

    }

    /*
     * 将结果输出到Excel表格
     */
    public static String createExcel(List<String> list, String path) {

        String result = "";
        if (list.size() == 0 || list == null) {
            result = "没有对象信息";
        } else {
            Object o = list.get(0);
            Class<? extends Object> clazz = o.getClass();
            String className = clazz.getSimpleName();
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // 获取当前时间，作为文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String date = sdf.format(new Date());
            String fileName = "Result";
            String name = fileName.concat(date).concat(".xls");

            WritableWorkbook book = null;
            File file = null;
            try {
                file = new File(path.concat(File.separator).concat(name));
                book = Workbook.createWorkbook(file);// 创建xls文件
                WritableSheet sheet = book.createSheet(className, 0);
                int i = 0;// 列
                int j = 0;// 行

                // 输出结果，列名为Results
                sheet.addCell(new Label(0, 0, "Results"));
                // 从第二行开始输出分类结果
                j = 1;
                // 迭代存储结果的ArrayList，输出到Excel中
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    sheet.addCell(new Label(i, j, it.next()));
                    j++;
                }

                book.write();
                result = file.getPath();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                result = "SystemException";
                e.printStackTrace();

            } finally {
                fileName = null;
                name = null;
                folder = null;
                file = null;
                if (book != null) {
                    try {
                        book.close();
                    } catch (WriteException e) {
                        // TODO Auto-generated catch block
                        result = "WriteException";
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        result = "IOException";
                        e.printStackTrace();
                    }
                }
            }

        }
        System.out.println("文件路径 " + result);
        // 返回文件名或者异常类型
        return result;

    }

    // 主函数
    public static void main(String[] args) throws Exception {

        // load training data
        String filenameTrain = "/home/ubuntu2/desktop/tor/data/MTimeBasedFeatures-15s-TOR-NonTOR-85.arff";
        DataSource sourceTrain = new DataSource(filenameTrain);
        Instances train = sourceTrain.getDataSet();
        if (train.classIndex() == -1)
            // 设置关系为最后一列数据
            train.setClassIndex(train.numAttributes() - 1);

        // load testing data
        String filenameTest = "/home/ubuntu2/desktop/tor/data/MTimeBasedFeatures-15s-TOR-NonTOR-15.arff";
        DataSource sourceTest = new DataSource(filenameTest);
        Instances test = sourceTest.getDataSet();
        if (test.classIndex() == -1)
            // 设置关系为最后一列数据
            test.setClassIndex(test.numAttributes() - 1);

        System.out.println("\n1.Loading data");
        System.out.println("  done");

        /*
         * feature selection algorithm:CfsSubsetEval+BestFirst
         */

        // 显示出筛选出来的特征
        featureSelection(train, 1);

        // 输出为Excel文件,第二项为Excel文件存储路径
        createExcel(classify(train, test, 1), "/home/ubuntu2/desktop/tor/data");

        System.out.println("=============================================================================");

        /*
         * feature selection algorithm:Infogain+Ranker
         */
//        // 显示出筛选出来的特征
//        featureSelection(train, 2);
//
//        // 输出为Excel文件,第二项为Excel文件存储路径
//        createExcel(classify(train, test, 2), "/home/ubuntu2/desktop/tor/data");

    }

    public ServerResponse<List<Flow>> getClassifyList(String trainFilePath,String testFilePath, String feature, String algorathm) {
        return null;
    }

}
