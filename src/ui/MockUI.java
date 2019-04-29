/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;

/**
 *
 * @author rando
 */
public class MockUI implements UI{
    
    private String[] input;
    private int inputIndex = 0;
    private ArrayList<String> output = new ArrayList<>();

    public MockUI(String[] input) {
        this.input = input;
    }
        
    @Override
    public void println(String output) {
        this.output.add(output);
    }

    @Override
    public void printf(String output, Object... format) {
        String temp = String.format(output, format);
        this.output.add(temp);
    }

    @Override
    public String getUserInput() {
        return input[inputIndex++];
    }
    
}
