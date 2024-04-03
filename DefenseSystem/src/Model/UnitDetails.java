/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ygpra
 */
public class UnitDetails {
    private final String unitName;
    private final String message;
    private final int unitNumber;
    
    public UnitDetails(int unitNumber, String unitName, String message){
        this.unitNumber=unitNumber;
        this.unitName=unitName;
        this.message=message;
    }
    
    public String getUnitName(){
        return unitName;
    }
    
    public String getMessage(){
        return message;
    }
    
    public int getUnitNumber(){
        return unitNumber;
    }
}
