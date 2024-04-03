/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UnitDetails;
import java.util.ArrayList;

/**
 *
 * @author ygpra
 */
public class CentralController {

    // Controller
    private static final CentralController centralizedController = new CentralController();

    // unit list
    private static final ArrayList<DefenseObserver> defenseUnit = new ArrayList<>();

    // message list
    private static final ArrayList<UnitDetails> messagesList = new ArrayList<>();
    
    // default constructor is private
    private CentralController(){}
    
    // singleton design pattern
    public static CentralController getInstance(){
        return centralizedController;
    }

    // add unit
    public void addDefenseUnit(DefenseObserver unit) {
        defenseUnit.add(unit);
    }

    // notify units
    public void notifyUnits(int count) {
        defenseUnit.stream().forEach((unit) -> {
            unit.areaUpdate(count);
        });
    }

    // guns enable
    public void enableGun(int index) {
        defenseUnit.stream().forEach((unit) -> {
            unit.gunsEnable(index);
        });
    }

    // get maincontroller
    public DefenseObserver getMaincontroller() {
        return defenseUnit.get(0);
    }

    // add message
    public void addMessage(UnitDetails data) {
        messagesList.add(data);

        defenseUnit.stream().forEach((unit) -> {
            unit.textAreaClear();
        });

        getMessage();
    }

    // get message
    int count = 0;

    public void getMessage() {
        defenseUnit.stream().forEach((unit) -> {
            messagesList.stream().forEach((data) -> {
                if (count == 0) {
                    unit.updateMessage(data.getUnitNumber(), data.getUnitName(), data.getMessage());
                } else if (data.getUnitNumber() == 0 || data.getUnitNumber() == count) {
                    unit.updateMessage(data.getUnitNumber(), data.getUnitName(), data.getMessage());
                }
            });
            count++;
        });
        count = 0;
    }

    // check string
    public boolean checkString(String value, int length) {
        for (int i = 0; i < length; i++) {
            char ch = value.charAt(i);
            int cast = (int) ch;
            if (cast != 32) {
                return true;
            }
        }
        return false;
    }

    // get unit details
    public void getUnitDetails() {
        for (int i = 1; i < defenseUnit.size(); i++) {
            DefenseObserver obj = getMaincontroller();
            
            DefenseObserver unit = defenseUnit.get(i);
            
            int soldier = unit.getSoldier();
            int ammo = unit.getAmmo();
            
            obj.addTableRows(unit, soldier, ammo);
        }
    }
}
