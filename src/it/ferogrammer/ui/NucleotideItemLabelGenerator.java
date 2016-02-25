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
        String label = "";
        if ((dataset.getXValue(series, item) % 1 == 0) && dataset.getYValue(series, item) >= 1500) {
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
        }
        return label;
    }
}
