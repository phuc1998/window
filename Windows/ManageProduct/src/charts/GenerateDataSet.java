/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charts;

import java.util.List;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author PHUC
 */
public class GenerateDataSet {

    public static DefaultCategoryDataset generateDatasetBarChart(int data, String name, String label) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.addValue(data, name, label);
        return dataSet;
    }
    
    public static PieDataset generateDatasetPieChart(List<String> ingredients, List<Long> data){
        DefaultPieDataset dataSet = new DefaultPieDataset();
        int size = ingredients.size();
        for(int i=0;i<size;i++){
            dataSet.setValue(ingredients.get(i), data.get(i));
        }
        return dataSet;
    }
}
