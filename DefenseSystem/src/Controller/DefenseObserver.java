/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ygpra
 */
public interface DefenseObserver {
    // update area condition
    public void areaUpdate(int count);
    
    // guns ready to shoot
    public void gunsEnable(int index);
    
    // get area strength levels
    public int getAreaStrengthLevels();
    
    // update messages to units
    public void updateMessage(int unitNumber, String unitName, String message);
    
    // text area clear
    public void textAreaClear();
    
    // get unit details
    public void addTableRows(DefenseObserver data, int soldier, int ammo);
    
    // add unit info to main
    public void addUnitInfoMain(String unitName, String infoName, int data); 
    
    // get unit soldier info
    public int getSoldier();
    
    // get unit ammo info
    public int getAmmo();
}
