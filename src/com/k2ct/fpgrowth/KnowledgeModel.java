package com.k2ct.fpgrowth;


import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author K^2CT
 */
public class KnowledgeModel {
    DataSource source;
    Instances dataset;
    String[] model_options;
    String[] data_options;

    public KnowledgeModel() {
    }

    public KnowledgeModel(String filepath, String m_options, String d_options) throws Exception {
        this.source = new DataSource(filepath);
        this.dataset = source.getDataSet();
        this.model_options = weka.core.Utils.splitOptions(m_options);
        this.data_options = weka.core.Utils.splitOptions(d_options);
    }

    public Instances removeData(Instances originalData) throws Exception {
        Remove remove = new Remove();
        remove.setOptions(data_options);
        remove.setInputFormat(originalData);
        return Filter.useFilter(originalData, remove);
    }

    public void saveData(String filename) throws IOException {
        ArffSaver outData = new ArffSaver();
        outData.setInstances(this.dataset);
        outData.setFile(new File(filename));
        outData.writeBatch(); 
        System.out.println("Finished!");

    }

    public void saveData2CSV(String filename) throws IOException {
        CSVSaver outData = new CSVSaver();
        outData.setInstances(this.dataset);
        outData.setFile(new File(filename));
        outData.writeBatch();
        System.out.println("Converted");
    }

    @Override
    public String toString() {
        return dataset.toSummaryString();
    }
}
