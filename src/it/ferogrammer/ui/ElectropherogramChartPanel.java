/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ferogrammer.ui;

import it.ferogrammer.process.DoubleNucleotide;
import it.ferogrammer.process.Nucleotide;
import it.ferogrammer.process.NucleotideSeq;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author enea
 */
class ElectropherogramChartPanel extends ChartPanel {
//TODO: missing manegement of 'N' in labels

    private NucleotideSeq seq;
    private final int PERTURB = 1000, PEAK = 2500, MEDIUM = 700;
    XYSeries a, c, t, g;

    public ElectropherogramChartPanel(NucleotideSeq seq) {
        super(null);
        this.seq = seq;
        this.setChart(createChart());
    }

    private JFreeChart createChart() {
        NumberAxis positionAxis = new NumberAxis("Position");
        positionAxis.setAutoRangeIncludesZero(false);
        positionAxis.setTickUnit(new NumberTickUnit(1));
        NumberAxis intensityAxis = new NumberAxis("Intensity");
        intensityAxis.setAutoRangeIncludesZero(true);
        XYSplineRenderer xysplinerenderer = new XYSplineRenderer();
        xysplinerenderer = setColorsAndLabels(xysplinerenderer);
        XYPlot xyplot = new XYPlot(createGraphData(), positionAxis, intensityAxis, xysplinerenderer);
        xyplot.setBackgroundPaint(Color.WHITE);
        xyplot.setDomainGridlinePaint(Color.GRAY);
        xyplot.setRangeGridlinePaint(Color.GRAY);
        xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
        JFreeChart jfreechart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
//        ChartUtilities.applyCurrentTheme(jfreechart);
        return jfreechart;

    }

    private XYSplineRenderer setColorsAndLabels(XYSplineRenderer xysplinerenderer) {
        Nucleotide[] nucs = Nucleotide.values();
        for (int i = 0; i < nucs.length; i++) {
            xysplinerenderer.setSeriesPaint(i, nucs[i].getColor());
            xysplinerenderer.setSeriesItemLabelPaint(i, nucs[i].getColor());
        }
        xysplinerenderer.setBaseShapesVisible(false);
        xysplinerenderer.setBaseItemLabelGenerator(new NucleotideItemLabelGenerator());
        xysplinerenderer.setBaseItemLabelsVisible(true);
        return xysplinerenderer;
    }

    private XYDataset createGraphData() {
        a = new XYSeries("A");
        initializeSeries(a, seq);
        c = new XYSeries("C");
        initializeSeries(c, seq);
        t = new XYSeries("T");
        initializeSeries(t, seq);
        g = new XYSeries("G");
        initializeSeries(g, seq);
        for (int i = 0; i < seq.size(); i++) {
            if (seq.getList().get(i) instanceof Nucleotide) {
                Nucleotide n = (Nucleotide) seq.getList().get(i);
                boostSeriesAmplitude(n, i);
                //in case the previous nucleotide is the same as the current one interpolate with a non 0 datapoint
                if (i > 0 && seq.getList().get(i - 1) == n) {
                    switch (n) {
                        case A:
                            a.update(new Double(i + seq.getStartPos() - 0.5), MEDIUM - Math.random() * (PERTURB / 3));
                            break;
                        case C:
                            c.update(new Double(i + seq.getStartPos() - 0.5), MEDIUM - Math.random() * (PERTURB / 3));
                            break;
                        case T:
                            t.update(new Double(i + seq.getStartPos() - 0.5), MEDIUM - Math.random() * (PERTURB / 3));
                            break;
                        case G:
                            g.update(new Double(i + seq.getStartPos() - 0.5), MEDIUM - Math.random() * (PERTURB / 3));
                            break;
                    }
                }
            } else {
                DoubleNucleotide dn = (DoubleNucleotide) seq.getList().get(i);
                boostSeriesAmplitude(dn.getNuc1(), i);
                boostSeriesAmplitude(dn.getNuc2(), i);
            }
        }

        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(a);
        xyseriescollection.addSeries(c);
        xyseriescollection.addSeries(t);
        xyseriescollection.addSeries(g);
        return xyseriescollection;


//            XYSeries xyseries1 = new XYSeries("Series 2");
//            xyseries1.add(11D, 56.270000000000003D);
//            xyseries1.add(10D, 41.32D);
//            xyseries1.add(9D, 31.449999999999999D);
//            xyseries1.add(8D, 30.050000000000001D);
//            xyseries1.add(7D, 24.690000000000001D);
//            xyseries1.add(6D, 19.780000000000001D);
//            xyseries1.add(5D, 20.940000000000001D);
//            xyseries1.add(4D, 16.73D);
//            xyseries1.add(3D, 14.210000000000001D);
//            xyseries1.add(2D, 12.44D);
//            xyseriescollection.addSeries(xyseries1);
//        return null;

    }

    private void initializeSeries(XYSeries series, NucleotideSeq seq) {
        int size = seq.size();
        int startPos = seq.getStartPos();
        for (int i = startPos; i < startPos + size; i++) {
            series.add(i + 0.5D, Math.random() * 3);
            series.add(i, Math.random() * 250);
        }
    }

    private void boostSeriesAmplitude(Nucleotide nuc, int position) {
        switch (nuc) {
            case A:
                a.update(new Double(position + seq.getStartPos()), PEAK - Math.random() * PERTURB);
                break;
            case C:
                c.update(new Double(position + seq.getStartPos()), PEAK - Math.random() * PERTURB);
                break;
            case T:
                t.update(new Double(position + seq.getStartPos()), PEAK - Math.random() * PERTURB);
                break;
            case G:
                g.update(new Double(position + seq.getStartPos()), PEAK - Math.random() * PERTURB);
                break;
        }
    }
}
