package com.k2ct;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.k2ct.fpgrowth.FpGrowth;



public class FPGrowthDemo {
    public static void main(String[] args) throws Exception {
        FpGrowth model = new FpGrowth("data/TransDataByCustomerID_train.arff",
                "-P 2 -I -1 -N 10 -T 0 -C 0.9 -D 0.05 -U 1.0 -M 0.03 -S", "-R 1");
        model.mineAssociationRules();
        model.saveFrequentItemSet("data/frequent_itemset_entire_train_data.csv");
        System.out.println("Association Rules for Entire train data" + model);


        FpGrowth model_cluster1 = new FpGrowth("data/cluster_1_trans_data.arff",
                "-P 2 -I -1 -N 10 -T 0 -C 0.7 -D 0.05 -U 1.0 -M 0.02 -S", "-R 1");
        model_cluster1.mineAssociationRules();
        model_cluster1.saveFrequentItemSet("data/frequent_itemsets_cluster1.csv");
        System.out.println("Association Rules for cluster 1 data" + model_cluster1);


        FpGrowth model_cluster2 = new FpGrowth("data/cluster_2_trans_data.arff",
                "-P 2 -I -1 -N 10 -T 0 -C 0.7 -D 0.05 -U 1.0 -M 0.012 -S", "-R 1");
        model_cluster2.mineAssociationRules();
        model_cluster2.saveFrequentItemSet("data/frequent_itemsets_cluster2.csv");
        System.out.println("Association Rules for cluster 2 data" + model_cluster2);


        FpGrowth model_cluster3 = new FpGrowth("data/cluster_3_trans_data.arff",
                "-P 2 -I -1 -N 10 -T 0 -C 0.7 -D 0.05 -U 1.0 -M 0.012 -S", "-R 1");
        model_cluster3.mineAssociationRules();
        model_cluster3.saveFrequentItemSet("data/frequent_itemsets_cluster3.csv");
        System.out.println("Association Rules for cluster 3 data" + model_cluster3);
    }
}