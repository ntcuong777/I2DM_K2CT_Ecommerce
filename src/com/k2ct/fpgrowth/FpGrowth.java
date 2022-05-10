package com.k2ct.fpgrowth;

import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.FPGrowth;
import weka.associations.Item;
import weka.core.Instances;

import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author K^2CT
 */
public class FpGrowth extends KnowledgeModel {
    Instances newData;
    FPGrowth fp;

    public FpGrowth(String filename, String m_options, String d_options) throws Exception {
        super(filename, m_options, d_options);
        this.fp = new FPGrowth();
    }

    public void mineAssociationRules() throws Exception {
        this.newData = removeData(this.dataset);

        fp.setOptions(this.model_options);

        fp.buildAssociations(this.newData);
    }

    // get association rules
    public List<AssociationRule> getAssociationRules() {
        return fp.getAssociationRules().getRules();
    }

    @Override
    public String toString() {
        return fp.toString();
    }

    public void saveFrequentItemSet(String path) {
        AssociationRules associationRules = this.fp.getAssociationRules();
        List<AssociationRule> listAssociationRule = associationRules.getRules();

        HashMap<Collection<Item>, Integer> frequentItemSets = new HashMap<>();
        for (AssociationRule ar : listAssociationRule) {
            Collection<Item> itemSet = ar.getPremise();
            itemSet.addAll(ar.getConsequence());
            if (! frequentItemSets.containsKey(itemSet)) {
                frequentItemSets.put(itemSet, ar.getTotalSupport());
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Collection<Item> fi : frequentItemSets.keySet()) {
                ArrayList<String> items = new ArrayList<>();
                for (Item i : fi) {
                    items.add(i.toString().split("=")[0]);
                }
                fileWriter.write(String.join(";", items));
                fileWriter.write("," + frequentItemSets.get(fi).toString() + "\n");
            }
            fileWriter.close();
            System.out.println("write successfully");
        } catch (IOException e) {
            System.out.println("Write file error");
        }
    }
}
