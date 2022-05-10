package com.k2ct.fpgrowth;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToBinary;
import weka.core.converters.ArffSaver;

import java.io.File;

public class CsvToArff {
    public static void main(String[] args) throws Exception {
        Instances entire_data_train = DataSource.read("data/TransDataByCustomerID_train.csv");
        Instances entire_data_test = DataSource.read("data/TransDataByCustomerID_test.csv");
        Instances cluster1 = DataSource.read("data/cluster_1_trans_data.csv");
        Instances cluster2 = DataSource.read("data/cluster_2_trans_data.csv");
        Instances cluster3 = DataSource.read("data/cluster_3_trans_data.csv");

        entire_data_train.setClassIndex(-1);
        entire_data_test.setClassIndex(-1);
        cluster1.setClassIndex(-1);
        cluster2.setClassIndex(-1);
        cluster3.setClassIndex(-1);

        //Numeric to Binary
        NumericToBinary NumericToBinary = new NumericToBinary();
        NumericToBinary.setInputFormat(entire_data_train);

        NumericToBinary NumericToBinary0 = new NumericToBinary();
        NumericToBinary0.setInputFormat(entire_data_test);

        NumericToBinary NumericToBinary1 = new NumericToBinary();
        NumericToBinary1.setInputFormat(cluster1);

        NumericToBinary NumericToBinary2 = new NumericToBinary();
        NumericToBinary2.setInputFormat(cluster2);

        NumericToBinary NumericToBinary3 = new NumericToBinary();
        NumericToBinary3.setInputFormat(cluster3);


        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "first-last";


        NumericToBinary.setOptions(options);

        NumericToBinary0.setOptions(options);

        NumericToBinary1.setOptions(options);

        NumericToBinary2.setOptions(options);

        NumericToBinary3.setOptions(options);

        entire_data_train = Filter.useFilter(entire_data_train, NumericToBinary);
        entire_data_test = Filter.useFilter(entire_data_test, NumericToBinary0);
        cluster1 = Filter.useFilter(cluster1, NumericToBinary1);
        cluster2 = Filter.useFilter(cluster2, NumericToBinary2);
        cluster3 = Filter.useFilter(cluster3, NumericToBinary3);

        //Save data as .arff file
        ArffSaver saver = new ArffSaver();
        saver.setInstances(entire_data_train);
        saver.setFile(new File("data/TransDataByCustomerID_train.arff"));
        saver.writeBatch();

        ArffSaver saver0 = new ArffSaver();
        saver0.setInstances(entire_data_test);
        saver0.setFile(new File("data/TransDataByCustomerID_test.arff"));
        saver0.writeBatch();

        ArffSaver saver1 = new ArffSaver();
        saver1.setInstances(cluster1);
        saver1.setFile(new File("data/cluster_1_trans_data.arff"));
        saver1.writeBatch();

        ArffSaver saver2 = new ArffSaver();
        saver2.setInstances(cluster2);
        saver2.setFile(new File("data/cluster_2_trans_data.arff"));
        saver2.writeBatch();

        ArffSaver saver3 = new ArffSaver();
        saver3.setInstances(cluster3);
        saver3.setFile(new File("data/cluster_3_trans_data.arff"));
        saver3.writeBatch();


        System.out.println("All files have been converted into ARFF file!");
    }
}

