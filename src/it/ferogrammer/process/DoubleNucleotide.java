/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ferogrammer.process;

import java.awt.Color;

/**
 *
 * @author enea
 */
public class DoubleNucleotide implements ColorableNucleotide {

    private Nucleotide nuc1, nuc2;

    public DoubleNucleotide(char nuc1, char nuc2) {
        this.nuc1 = whichNucleotide(nuc1) ;
        this.nuc2 = whichNucleotide(nuc2);
    }

    public Nucleotide getNuc1() {
        return nuc1;
    }

    public Nucleotide getNuc2() {
        return nuc2;
    }

    public DoubleNucleotide(Nucleotide nuc1, Nucleotide nuc2) {
        this.nuc1 = nuc1;
        this.nuc2 = nuc2;
    }

    private Nucleotide whichNucleotide(char nucX) {
        switch (nucX) {
            case 'a':
                return Nucleotide.A;
            case 'c':
                return Nucleotide.C;
            case 'g':
                return Nucleotide.G;
            case 't':
                return Nucleotide.T;
            default: throw new IllegalArgumentException("Sequence contains some unexpected characters");
        }
    }

    @Override
    public Color getColor() {
//        TODO: set color for N
        throw new UnsupportedOperationException("Set color for N label");
    }
}
