/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CentralController;
import static Controller.ControllerUtilities.*;
import Controller.DefenseObserver;
import Model.UnitDetails;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ygpra
 */
public class Helicopter extends JFrame implements DefenseObserver {
    
    CentralController observable = CentralController.getInstance();

    private final JPanel helicopterClearance;
    private final JLabel areaCondition;
    private final JLabel checkBoxLabel;
    private final JCheckBox checkBox;
    private final JPanel checkPanel;
    private final JPanel areaHeader;
    private final JLabel areaHeaderLabel;
    private final JPanel guns;
    private final JPanel messages;
    private final JPanel helicopterUpdates;
    private final JButton btnShoot;
    private final JButton btnMissile;
    private final JButton btnLaser;
    private final JButton btnRocket;
    private final JPanel gunsHeaderPanel;
    private final JLabel gunsHeaderLabel;
    private final JPanel gunsPanel;
    private int positionCheck = 0;
    private final JPanel messageHeadPanel;
    private final JPanel receivedMessageDisplay;
    private final JPanel sendMessage;
    private final JLabel messageHeader;
    private final JTextArea showMessages;
    private final JScrollPane sp;
    private JButton btnSend;
    private JTextField sendMessageBox;
    private JSpinner soldierCount;
    private final JLabel soldierTag;
    private final JLabel ammoTag;
    private JSpinner ammoCount;
    private final JPanel updateHeadPanel;
    private final JLabel updateHeader;
    private final JPanel updateBody;
    private final JPanel soldierPanel;
    private final JPanel ammoPanel;

    public Helicopter() {
        setSize(698, 368);
        setTitle("Helicopter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(true);
        setLayout(new GridLayout(2, 2));
        
        // set image icon
        setIconImage(favicon);

        // helicopter Clearance
        helicopterClearance = new JPanel();
        helicopterClearance.setBackground(whiteBack);
        helicopterClearance.setLayout(new BorderLayout());

        // area header panel
        areaHeader = new JPanel();
        areaHeader.setLayout(new BorderLayout());
        areaHeader.setBackground(MainColor);
        helicopterClearance.add(areaHeader, BorderLayout.NORTH);

        // area header
        areaHeaderLabel = new JLabel("Area Clearance & Position", SwingConstants.CENTER);
        areaHeaderLabel.setForeground(Color.white);
        areaHeaderLabel.setFont(new Font("", 1, 14));
        areaHeader.add(areaHeaderLabel, BorderLayout.CENTER);

        // area condition
        areaCondition = new JLabel("Area Not Cleared");
        areaCondition.setForeground(typography);
        areaCondition.setFont(new Font("", 1, 14));
        areaCondition.setBorder(BorderFactory.createMatteBorder(0, 35, 0, 0, whiteBack));
        helicopterClearance.add(areaCondition, BorderLayout.WEST);

        // check panel
        checkPanel = new JPanel();
        checkPanel.setLayout(new BorderLayout());
        checkPanel.setBackground(whiteBack);
        checkPanel.setBorder(BorderFactory.createMatteBorder(10, 35, 35, 10, whiteBack));
        helicopterClearance.add(checkPanel, BorderLayout.SOUTH);

        // check box
        checkBox = new JCheckBox();
        checkBox.setBackground(whiteBack);
        checkPanel.add(checkBox, BorderLayout.WEST);

        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                positionCheck++;

                DefenseObserver strengthLevel = observable.getMaincontroller();

                int levelValue = strengthLevel.getAreaStrengthLevels();

                if (levelValue >= strengthLevels.LOW.getValue() && levelValue < strengthLevels.MEDIUM.getValue()) {
                    observable.enableGun(strengthLevels.LOW.getValue());
                } else if (levelValue >= strengthLevels.MEDIUM.getValue() && levelValue < strengthLevels.HIGH.getValue()) {
                    observable.enableGun(strengthLevels.MEDIUM.getValue());
                } else if (levelValue >= strengthLevels.HIGH.getValue() && levelValue < strengthLevels.STRONG.getValue()) {
                    observable.enableGun(strengthLevels.HIGH.getValue());
                } else if (levelValue >= strengthLevels.STRONG.getValue() && levelValue < strengthLevels.CLOSED.getValue()) {
                    observable.enableGun(strengthLevels.STRONG.getValue());
                } else if (levelValue == strengthLevels.CLOSED.getValue()) {
                    observable.enableGun(strengthLevels.CLOSED.getValue());
                } else {
                    observable.enableGun(0);
                }
            }
        });

        // check box label
        checkBoxLabel = new JLabel("Position");
        checkBoxLabel.setForeground(typography);
        checkBoxLabel.setFont(new Font("", 1, 14));
        checkPanel.add(checkBoxLabel, BorderLayout.CENTER);

        // guns
        guns = new JPanel();
        guns.setLayout(new BorderLayout());

        // guns header panel
        gunsHeaderPanel = new JPanel();
        gunsHeaderPanel.setLayout(new BorderLayout());
        gunsHeaderPanel.setBackground(SecondColor);
        guns.add(gunsHeaderPanel, BorderLayout.NORTH);

        // guns header label
        gunsHeaderLabel = new JLabel("Guns Enable", SwingConstants.CENTER);
        gunsHeaderLabel.setForeground(Color.white);
        gunsHeaderLabel.setFont(new Font("", 1, 14));
        gunsHeaderPanel.add(gunsHeaderLabel, BorderLayout.CENTER);

        // guns panel
        gunsPanel = new JPanel();
        gunsPanel.setLayout(null);
        gunsPanel.setBackground(whiteBack);
        guns.add(gunsPanel, BorderLayout.CENTER);

        // guns add
        // shoot
        btnShoot = new JButton("Shoot");
        btnShoot.setBounds(23, 25, 130, 35);
        btnShoot.setFont(new Font("", 1, 16));
        btnShoot.setBackground(OverMain);
        btnShoot.setForeground(Color.white);
        btnShoot.setBorderPainted(false);
        btnShoot.setFocusPainted(false);
        btnShoot.setEnabled(false);
        gunsPanel.add(btnShoot);

        // missile
        btnMissile = new JButton("Missile");
        btnMissile.setBounds(185, 25, 130, 35);
        btnMissile.setFont(new Font("", 1, 16));
        btnMissile.setBackground(OverMain);
        btnMissile.setForeground(Color.white);
        btnMissile.setBorderPainted(false);
        btnMissile.setFocusPainted(false);
        btnMissile.setEnabled(false);
        gunsPanel.add(btnMissile);

        // laser
        btnLaser = new JButton("Laser");
        btnLaser.setBounds(23, 85, 130, 35);
        btnLaser.setFont(new Font("", 1, 16));
        btnLaser.setBackground(OverMain);
        btnLaser.setForeground(Color.white);
        btnLaser.setBorderPainted(false);
        btnLaser.setFocusPainted(false);
        btnLaser.setEnabled(false);
        gunsPanel.add(btnLaser);

        // rocket
        btnRocket = new JButton("Rocket");
        btnRocket.setBounds(185, 85, 130, 35);
        btnRocket.setFont(new Font("", 1, 16));
        btnRocket.setBackground(OverMain);
        btnRocket.setForeground(Color.white);
        btnRocket.setBorderPainted(false);
        btnRocket.setFocusPainted(false);
        btnRocket.setEnabled(false);
        gunsPanel.add(btnRocket);

        // messages
        messages = new JPanel();
        messages.setLayout(new BorderLayout());

        // message head panel
        messageHeadPanel = new JPanel();
        messageHeadPanel.setLayout(new BorderLayout());
        messageHeadPanel.setBackground(SecondColor);
        messages.add(messageHeadPanel, BorderLayout.NORTH);

        // message header
        messageHeader = new JLabel("Messages", SwingConstants.CENTER);
        messageHeader.setForeground(Color.white);
        messageHeader.setFont(new Font("", 1, 14));
        messageHeadPanel.add(messageHeader, BorderLayout.CENTER);

        // text area panel
        receivedMessageDisplay = new JPanel();
        receivedMessageDisplay.setLayout(new BorderLayout());
        receivedMessageDisplay.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, whiteBack));
        messages.add(receivedMessageDisplay, BorderLayout.CENTER);

        // text area
        showMessages = new JTextArea();
        showMessages.setBackground(secondBGC);
        showMessages.setForeground(typography);
        showMessages.setFont(new Font("", 4, 12));
        showMessages.setLineWrap(true);
        showMessages.setEditable(false);

        // scroll pane
        sp = new JScrollPane(showMessages);
        receivedMessageDisplay.add(sp, BorderLayout.CENTER);

        // send message panel
        sendMessage = new JPanel();
        sendMessage.setLayout(new BorderLayout());
        sendMessage.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, whiteBack));
        messages.add(sendMessage, BorderLayout.SOUTH);

        // text field
        sendMessageBox = new JTextField();
        sendMessageBox.setBackground(headerColor);
        sendMessageBox.setForeground(typography);
        sendMessageBox.setFont(new Font("", 1, 12));
        sendMessage.add(sendMessageBox, BorderLayout.CENTER);

        // send button
        btnSend = new JButton("Send");
        btnSend.setFont(new Font("", 1, 16));
        btnSend.setBackground(OverMain);
        btnSend.setForeground(Color.white);
        btnSend.setBorderPainted(false);
        btnSend.setFocusPainted(false);
        btnSend.setEnabled(false);
        sendMessage.add(btnSend, BorderLayout.EAST);

        sendMessageBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                String value = sendMessageBox.getText();
                int length = value.length();
                
                boolean condition=observable.checkString(value, length);

                if (length > 0 & condition) {
                    btnSend.setEnabled(true);
                    btnSendHover(true);
                } else {
                    btnSend.setEnabled(false);
                    btnSendHover(false);
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                observable.addMessage(new UnitDetails(0, "Helicopter", sendMessageBox.getText()));
                sendMessageBox.setText("");
                btnSend.setEnabled(false);
                btnSendHover(false);
            }
        });

        // helicopter updates
        helicopterUpdates = new JPanel();
        helicopterUpdates.setLayout(new BorderLayout());
        
        // update head panel
        updateHeadPanel=new JPanel();
        updateHeadPanel.setLayout(new BorderLayout());
        updateHeadPanel.setBackground(MainColor);
        helicopterUpdates.add(updateHeadPanel, BorderLayout.NORTH);
        
        // update header
        updateHeader=new JLabel("Helicopter Updates", SwingConstants.CENTER);
        updateHeader.setForeground(Color.white);
        updateHeader.setFont(new Font("", 1, 14));
        updateHeadPanel.add(updateHeader, BorderLayout.CENTER);
        
        // update body
        updateBody=new JPanel();
        updateBody.setLayout(new GridLayout(2,1));
        helicopterUpdates.add(updateBody, BorderLayout.CENTER);
        
        // soldier panel
        soldierPanel=new JPanel();
        soldierPanel.setLayout(new BorderLayout());
        soldierPanel.setBackground(whiteBack);
        soldierPanel.setBorder(BorderFactory.createMatteBorder(20, 10, 20, 80, whiteBack));
        updateBody.add(soldierPanel);
        
        // soldier tag
        soldierTag=new JLabel("Soldier Count");
        soldierTag.setFont(new Font("", 1, 14));
        soldierTag.setForeground(typography);
        soldierTag.setBorder(BorderFactory.createMatteBorder(0, 60, 0, 20, whiteBack));
        soldierPanel.add(soldierTag, BorderLayout.WEST);
        
        // soldier spinner
        soldierCount=new JSpinner();
        soldierCount.setValue(36);
        soldierCount.setFont(new Font("", 1, 12));
        soldierPanel.add(soldierCount, BorderLayout.CENTER);
        
        soldierCount.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent evt){
                DefenseObserver mainController = observable.getMaincontroller();
                int data = Integer.parseInt(soldierCount.getValue().toString());
                mainController.addUnitInfoMain("Helicopter", "Soldier", data);
            }
        });
        
        // ammo panel
        ammoPanel=new JPanel();
        ammoPanel.setLayout(new BorderLayout());
        ammoPanel.setBackground(whiteBack);
        ammoPanel.setBorder(BorderFactory.createMatteBorder(20, 18, 20, 80, whiteBack));
        updateBody.add(ammoPanel);
        
        // ammo tag
        ammoTag=new JLabel("Ammo Count");
        ammoTag.setFont(new Font("", 1, 14));
        ammoTag.setForeground(typography);
        ammoTag.setBorder(BorderFactory.createMatteBorder(0, 53, 0, 27, whiteBack));
        ammoPanel.add(ammoTag, BorderLayout.WEST);
        
        // ammo spinner
        ammoCount=new JSpinner();
        ammoCount.setValue(2800);
        ammoCount.setFont(new Font("", 1, 12));
        ammoPanel.add(ammoCount, BorderLayout.CENTER);
        
        ammoCount.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent evt){
                DefenseObserver mainController = observable.getMaincontroller();
                int data = Integer.parseInt(ammoCount.getValue().toString());
                mainController.addUnitInfoMain("Helicopter", "Ammo", data);
            }
        });
        

        add(helicopterClearance);
        add(guns);
        add(messages);
        add(helicopterUpdates);
    }

    @Override
    public void areaUpdate(int count) {
        if (count % 2 == 0) {
            areaCondition.setText("Area Cleared");
        } else {
            areaCondition.setText("Area Not Cleared");
        }
    }

    // guns enable
    @Override
    public void gunsEnable(int index) {
        if (index == strengthLevels.LOW.getValue() & (positionCheck % 2 != 0)) {
            btnShoot.setEnabled(true);
            btnShootHover(true);
            btnMissile.setEnabled(false);
            btnMissileHover(false);
            btnLaser.setEnabled(false);
            btnLaserHover(false);
            btnRocket.setEnabled(false);
            btnRocketHover(false);
        } else if (index == strengthLevels.MEDIUM.getValue() & (positionCheck % 2 != 0)) {
            btnShoot.setEnabled(true);
            btnShootHover(true);
            btnMissile.setEnabled(true);
            btnMissileHover(true);
            btnLaser.setEnabled(false);
            btnLaserHover(false);
            btnRocket.setEnabled(false);
            btnRocketHover(false);
        } else if (index == strengthLevels.HIGH.getValue() & (positionCheck % 2 != 0)) {
            btnShoot.setEnabled(true);
            btnShootHover(true);
            btnMissile.setEnabled(true);
            btnMissileHover(true);
            btnLaser.setEnabled(true);
            btnLaserHover(true);
            btnRocket.setEnabled(false);
            btnRocketHover(false);
        } else if ((index == strengthLevels.STRONG.getValue() || index == strengthLevels.CLOSED.getValue()) & (positionCheck % 2 != 0)) {
            btnShoot.setEnabled(true);
            btnShootHover(true);
            btnMissile.setEnabled(true);
            btnMissileHover(true);
            btnLaser.setEnabled(true);
            btnLaserHover(true);
            btnRocket.setEnabled(true);
            btnRocketHover(true);
        } else if (index == 0 & (positionCheck % 2 != 0)) {
            btnShoot.setEnabled(false);
            btnShootHover(false);
            btnMissile.setEnabled(false);
            btnMissileHover(false);
            btnLaser.setEnabled(false);
            btnLaserHover(false);
            btnRocket.setEnabled(false);
            btnRocketHover(false);
        } else {
            btnShoot.setEnabled(false);
            btnShootHover(false);
            btnMissile.setEnabled(false);
            btnMissileHover(false);
            btnLaser.setEnabled(false);
            btnLaserHover(false);
            btnRocket.setEnabled(false);
            btnRocketHover(false);
        }
    }

    public void btnShootHover(boolean condition) {
        if (condition) {
            btnShoot.setBackground(MainColor);
            btnShoot.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnShoot.setBackground(OverMain);
                    btnShoot.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnShoot.setBackground(MainColor);
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    btnShoot.setForeground(Color.black);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    btnShoot.setForeground(Color.white);
                }
            });
        } else {
            btnShoot.setBackground(OverMain);
            btnShoot.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnShoot.setBackground(OverMain);
                    btnShoot.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnShoot.setBackground(OverMain);
                }
            });
        }
    }

    public void btnMissileHover(boolean condition) {
        if (condition) {
            btnMissile.setBackground(MainColor);
            btnMissile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnMissile.setBackground(OverMain);
                    btnMissile.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnMissile.setBackground(MainColor);
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    btnMissile.setForeground(Color.black);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    btnMissile.setForeground(Color.white);
                }
            });
        } else {
            btnMissile.setBackground(OverMain);
            btnMissile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnMissile.setBackground(OverMain);
                    btnMissile.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnMissile.setBackground(OverMain);
                }
            });
        }
    }

    public void btnLaserHover(boolean condition) {
        if (condition) {
            btnLaser.setBackground(MainColor);
            btnLaser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnLaser.setBackground(OverMain);
                    btnLaser.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnLaser.setBackground(MainColor);
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    btnLaser.setForeground(Color.black);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    btnLaser.setForeground(Color.white);
                }
            });
        } else {
            btnLaser.setBackground(OverMain);
            btnLaser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnLaser.setBackground(OverMain);
                    btnLaser.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnLaser.setBackground(OverMain);
                }
            });
        }
    }

    public void btnRocketHover(boolean condition) {
        if (condition) {
            btnRocket.setBackground(MainColor);
            btnRocket.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnRocket.setBackground(OverMain);
                    btnRocket.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnRocket.setBackground(MainColor);
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    btnRocket.setForeground(Color.black);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    btnRocket.setForeground(Color.white);
                }
            });
        } else {
            btnRocket.setBackground(OverMain);
            btnRocket.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnRocket.setBackground(OverMain);
                    btnRocket.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnRocket.setBackground(OverMain);
                }
            });
        }
    }

    public void btnSendHover(boolean condition) {
        if (condition) {
            btnSend.setBackground(MainColor);
            btnSend.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnSend.setBackground(OverMain);
                    btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnSend.setBackground(MainColor);
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    btnSend.setForeground(Color.black);
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    btnSend.setForeground(Color.white);
                }
            });
        } else {
            btnSend.setBackground(OverMain);
            btnSend.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    btnSend.setBackground(OverMain);
                    btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btnSend.setBackground(OverMain);
                }
            });
        }
    }

    @Override
    public int getAreaStrengthLevels() {
        return -1;
    }
    
    // text area clear
    @Override
    public void textAreaClear(){
        showMessages.setText("");
    }
    
    // update message
    @Override
    public void updateMessage(int unitNumber, String unitName, String message){
        if(unitNumber==0){
            showMessages.append(unitName+" : "+message+"\n");
        } else if(unitNumber==1){
            showMessages.append(unitName+" (P)"+" : "+message+"\n");
        }
    }
    
    @Override
    public void addUnitInfoMain(String unitName, String infoName, int data){}
    
    @Override
    public void addTableRows(DefenseObserver data, int soldier, int ammo){}
    
    // get unit soldier info
    @Override
    public int getSoldier(){
        return Integer.parseInt(soldierCount.getValue().toString());
    }
    
    // get unit ammo info
    @Override
    public int getAmmo(){
        return Integer.parseInt(ammoCount.getValue().toString());
    }
;

}
