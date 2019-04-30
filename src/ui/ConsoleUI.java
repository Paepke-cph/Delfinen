/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Scanner;

/**
 *
 * @author rando
 */
public class ConsoleUI implements UI {
    private Scanner scan = new Scanner(System.in);
            
            
    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void printf(String output, Object... format) {
        System.out.printf(output,format);
    }

    @Override
    public String getUserInput() {
        return scan.nextLine();
    }
    
}
