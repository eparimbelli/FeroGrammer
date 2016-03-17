/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ferogrammer.ui;

import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author enea
 */
public class NucleotideItemLabelGenerator implements XYItemLabelGenerator {

    @Override
    public String generateLabel(XYDataset dataset, int series, int item) {
        //This can be improved by including a dependency on the Nucleotide Enum.values()
        //see setColorsAndLabels() in ElectropherogramChartPanel for an example of the approach
        
        //TODO fix here because now only N label appears
        String label = "";
        if ((dataset.getXValue(series, item) % 1 == 0) && dataset.getYValue(series, item) >= 1500) {
            if (onlyOnePeak(dataset, series, item)) {
                switch (series) {
                    case 0:
                        label = "A";
                        break;
                    case 1:
                        label = "C";
                        break;
                    case 2:
                        label = "T";
                        break;
                    case 3:
                        label = "G";
                        break;
                }
            } else {
                //means there are 2 peaks
                //set the label of only 'N' series to N, leave others blank
                if(series==4){
                  label = "N";
                }  
            }
        }
        return label;
    }

    private boolean onlyOnePeak(XYDataset dataset, int series, int item) {
        boolean only1 = true;
        //NOTE: used dataset.getSeriesCount()-1 to exclude last series which is the accessory 'N' series
        for (int i = 0; i < dataset.getSeriesCount()-1; i++) {
            if(i==series) continue; //do not consider the current series
            if(dataset.getYValue(series, item)>=1500) only1=false;           
        }
        return only1;
    }
}
