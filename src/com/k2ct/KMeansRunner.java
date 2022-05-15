package com.k2ct;

import com.k2ct.clusterer.SimpleKMeansCluster;

public class KMeansRunner {
    public static void main(String[] args) throws Exception {
        SimpleKMeansCluster simpleKMean = new SimpleKMeansCluster();
        simpleKMean.buildSimpleKMeans(3);
        simpleKMean.getSummary();
        simpleKMean.exportDataWithCluster();
        simpleKMean.saveModel();
    }
}
