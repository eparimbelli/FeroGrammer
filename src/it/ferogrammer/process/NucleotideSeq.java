/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ferogrammer.process;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author enea
 */
public class NucleotideSeq {

    private List<ColorableNucleotide> seq;
    private int startPos;

    public NucleotideSeq(String sequence) {
        startPos = (int) Math.floor(Math.random()*1000);
        seq = new ArrayList<>();
        ColorableNucleotide n = null;
        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.toLowerCase().charAt(i)) {
                case 'a':
                    n = Nucleotide.A;
                    break;
                case 'c':
                    n = Nucleotide.C;
                    break;
                case 'g':
                    n = Nucleotide.G;
                    break;
                case 't':
                    n = Nucleotide.T;
                    break;
                case 'n':
                    n = new DoubleNucleotide(sequence.toLowerCase().charAt(i+2), sequence.toLowerCase().charAt(i+3));
                    i=i+4;
                    break;
                default:
                    throw new IllegalArgumentException("Sequence contains some unexpected characters");
            }
            seq.add(n);
        }
    }

    public NucleotideSeq(String sequence, int start) {
        this(sequence);
        startPos=start;        
    }

    public List<ColorableNucleotide> getList() {
        return seq;
    }

    public int getStartPos() {
        return startPos;
    }
    

    public int size() {
        return seq.size();
    }
    
    
}
