/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author ygpra
 */
public class ControllerUtilities {

    // colors
    public static final Color SecondColor = new Color(121, 172, 120);
    public static final Color MainColor = new Color(180, 132, 108);
    public static final Color OverSecond = new Color(97, 130, 100);
    public static final Color OverMain = new Color(125, 90, 80);
    public static final Color whiteBack = new Color(245, 247, 248);
    public static final Color typography = new Color(25, 25, 25);
    public static final Color secondBGC = new Color(227, 225, 217);
    public static final Color headerColor = new Color(199, 200, 204);
    
    // strength level
    public enum strengthLevels {
        LOW(20),
        MEDIUM(40),
        HIGH(60),
        STRONG(80),
        CLOSED(100);

        private final int value;

        // Constructor to set the initial value for each strength level
        strengthLevels(int value) {
            this.value = value;
        }

        // Getter method to retrieve the value of each strength level
        public int getValue() {
            return value;
        }
    }
    
    // favicon
    public static final Image favicon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ygpra\\Desktop\\iCET Assignments and Coursework\\OOP\\OOP Final Coursework\\DefenseSystem\\DefenseSystem\\src\\Resources\\favicon.png");
}
