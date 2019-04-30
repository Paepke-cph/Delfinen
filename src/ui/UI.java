/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author rando
 */
public interface UI {
    public void println(String output);
    public void printf(String output, Object... format);
    public String getUserInput();
}
