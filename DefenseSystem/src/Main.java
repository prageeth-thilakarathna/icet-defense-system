
import Controller.CentralController;
import View.Helicopter;
import View.MainController;
import View.Submarine;
import View.Tank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ygpra
 */
public class Main {

    public static void main(String[] args) {
        CentralController observable = CentralController.getInstance();

        Helicopter helicopter = new Helicopter();
        MainController mainController = new MainController();
        Submarine submarine = new Submarine();
        Tank tank = new Tank();

        observable.addDefenseUnit(mainController);
        observable.addDefenseUnit(helicopter);
        observable.addDefenseUnit(tank);
        observable.addDefenseUnit(submarine);

        submarine.setVisible(true);
        tank.setVisible(true);
        helicopter.setVisible(true);
        mainController.setVisible(true);

        // add unit table details in main
        observable.getUnitDetails();
    }
}
