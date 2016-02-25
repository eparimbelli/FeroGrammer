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
public enum Nucleotide {
    A(Color.GREEN), C(Color.BLUE), T(Color.RED), G(Color.BLACK);
    private Color color;
    
    Nucleotide(Color col){
        color = col;
    }

    public Color getColor() {
        return color;
    }
    
}
