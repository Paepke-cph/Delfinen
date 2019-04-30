/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author rando
 */
public class JuniorMember extends Member {
    
    public JuniorMember(String name, int age, int id) {
        super(name, age, id);
    }
    
    @Override
    public double calculatePrice(){
        return 1000;
    }
}
