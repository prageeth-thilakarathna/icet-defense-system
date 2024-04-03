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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ygpra
 */
public class MainController extends JFrame implements DefenseObserver {

    CentralController observable = CentralController.getInstance();

    private final JLabel areaLabel;
    private final JLabel areaHeader;
    private final JPanel areaHeaderPanel;
    private final JPanel areaPanel;
    private final JCheckBox areaCheck;
    private final JPanel messageReceived;
    private final JPanel sendMessage;
    private final JPanel defenseUnitInfo;
    private final JPanel checkBoxPanel;
    private JSlider areaStrengthLevels;
    private final JPanel sliderPanel;
    private int count = 0;
    private final JTextArea receivedMessageDisplay;
    private final JPanel receivedPanel;
    private final JLabel receivedHeader;
    private final JPanel receivedHeadPanel;
    private final JScrollPane sp;
    private final JPanel sendMessageHeadPanel;
    private final JPanel sendMessageBody;
    private final JLabel sendMessageHeader;
    private final JPanel selectUnitPanel;
    private final JPanel sendMessageArea;
    private JButton btnSend;
    private JTextField sendMessageBox;
    private JComboBox<String> selectUnit;
    private final JTable unitInfo;
    private final DefaultTableModel dtm;
    private final JScrollPane tableSP;
    private final JPanel unitInfoHeadPanel;
    private final JLabel unitInfoHeader;
    private final JPanel unitInfoBody;
    private final DefaultTableCellRenderer centerRenderer;

    public MainController() {
        setSize(698, 368);
        setTitle("Main Controller");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(true);
        setLayout(new GridLayout(2, 2));

        // set image icon
        setIconImage(favicon);

        // area panel
        areaPanel = new JPanel();
        areaPanel.setLayout(new BorderLayout());
        areaPanel.setBackground(whiteBack);

        // area header
        areaHeader = new JLabel("Area & Guns enable", SwingConstants.CENTER);
        areaHeader.setForeground(Color.white);
        areaHeader.setFont(new Font("", 1, 14));

        areaHeaderPanel = new JPanel();
        areaHeaderPanel.setLayout(new BorderLayout());
        areaHeaderPanel.setBackground(MainColor);

        areaHeaderPanel.add(areaHeader, BorderLayout.CENTER);
        areaPanel.add(areaHeaderPanel, BorderLayout.NORTH);

        // check box panel
        checkBoxPanel = new JPanel();
        checkBoxPanel.setBorder(BorderFactory.createMatteBorder(30, 50, 10, 30, whiteBack));
        checkBoxPanel.setBackground(whiteBack);
        checkBoxPanel.setLayout(new BorderLayout());

        // area check label
        areaLabel = new JLabel("Area Clear");
        areaLabel.setForeground(typography);
        areaLabel.setFont(new Font("", 1, 14));
        checkBoxPanel.add(areaLabel, BorderLayout.CENTER);

        // area check box
        areaCheck = new JCheckBox();
        areaCheck.setBackground(whiteBack);
        checkBoxPanel.add(areaCheck, BorderLayout.WEST);

        areaPanel.add(checkBoxPanel, BorderLayout.CENTER);

        areaCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                observable.notifyUnits(count++);
            }
        });

        // slider panel
        sliderPanel = new JPanel();
        sliderPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 30, 10, whiteBack));
        sliderPanel.setBackground(whiteBack);
        sliderPanel.setLayout(new BorderLayout());

        // slider
        areaStrengthLevels = new JSlider(0, 100, 0);
        areaStrengthLevels.setPaintTrack(true);
        areaStrengthLevels.setPaintTicks(true);
        areaStrengthLevels.setPaintLabels(true);
        areaStrengthLevels.setMajorTickSpacing(10);
        areaStrengthLevels.setMinorTickSpacing(2);
        areaStrengthLevels.setForeground(typography);
        areaStrengthLevels.setBackground(whiteBack);
        areaStrengthLevels.setFont(new Font("", 1, 12));

        sliderPanel.add(areaStrengthLevels, BorderLayout.CENTER);
        areaPanel.add(sliderPanel, BorderLayout.SOUTH);

        areaStrengthLevels.addChangeListener((javax.swing.event.ChangeListener) new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent evt) {
                if (areaStrengthLevels.getValue() >= strengthLevels.LOW.getValue() && areaStrengthLevels.getValue() < strengthLevels.MEDIUM.getValue()) {
                    observable.enableGun(strengthLevels.LOW.getValue());
                } else if (areaStrengthLevels.getValue() >= strengthLevels.MEDIUM.getValue() && areaStrengthLevels.getValue() < strengthLevels.HIGH.getValue()) {
                    observable.enableGun(strengthLevels.MEDIUM.getValue());
                } else if (areaStrengthLevels.getValue() >= strengthLevels.HIGH.getValue() && areaStrengthLevels.getValue() < strengthLevels.STRONG.getValue()) {
                    observable.enableGun(strengthLevels.HIGH.getValue());
                } else if (areaStrengthLevels.getValue() >= strengthLevels.STRONG.getValue() && areaStrengthLevels.getValue() < strengthLevels.CLOSED.getValue()) {
                    observable.enableGun(strengthLevels.STRONG.getValue());
                } else if (areaStrengthLevels.getValue() == strengthLevels.CLOSED.getValue()) {
                    observable.enableGun(strengthLevels.CLOSED.getValue());
                } else {
                    observable.enableGun(0);
                }
            }

        });

        // defense unit informations
        defenseUnitInfo = new JPanel();
        defenseUnitInfo.setLayout(new BorderLayout());

        // unit info head panel
        unitInfoHeadPanel = new JPanel();
        unitInfoHeadPanel.setLayout(new BorderLayout());
        unitInfoHeadPanel.setBackground(SecondColor);
        defenseUnitInfo.add(unitInfoHeadPanel, BorderLayout.NORTH);

        // unit info header
        unitInfoHeader = new JLabel("Unit Informations", SwingConstants.CENTER);
        unitInfoHeader.setForeground(Color.white);
        unitInfoHeader.setFont(new Font("", 1, 14));
        unitInfoHeadPanel.add(unitInfoHeader, BorderLayout.CENTER);

        // unit info body
        unitInfoBody = new JPanel();
        unitInfoBody.setLayout(new BorderLayout());
        unitInfoBody.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, whiteBack));
        defenseUnitInfo.add(unitInfoBody, BorderLayout.CENTER);

        // info tabel
        String[] columnName = {"Unit Name", "Soldier Count", "Ammo Count"};
        dtm = new DefaultTableModel(columnName, 0);
        unitInfo = new JTable(dtm);
        unitInfo.setFont(new Font("", 1, 12));
        unitInfo.setForeground(typography);
        unitInfo.setRowHeight(30);
        unitInfo.setBackground(whiteBack);
        unitInfo.getTableHeader().setBackground(headerColor);
        unitInfo.getTableHeader().setForeground(typography);
        unitInfo.getTableHeader().setFont(new Font("", 1, 14));
        unitInfo.getTableHeader().setPreferredSize(new Dimension(30, 30));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        unitInfo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // cell value center
        unitInfo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        unitInfo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // scroll 
        tableSP = new JScrollPane(unitInfo);
        tableSP.getVerticalScrollBar().setBackground(secondBGC);
        tableSP.getViewport().setBackground(whiteBack);
        tableSP.setBorder(createEmptyBorder());
        unitInfoBody.add(tableSP, BorderLayout.CENTER);

        // message received
        messageReceived = new JPanel();
        messageReceived.setLayout(new BorderLayout());

        // received head panel
        receivedHeadPanel = new JPanel();
        receivedHeadPanel.setLayout(new BorderLayout());
        receivedHeadPanel.setBackground(SecondColor);
        messageReceived.add(receivedHeadPanel, BorderLayout.NORTH);

        // received header
        receivedHeader = new JLabel("Received Messages", SwingConstants.CENTER);
        receivedHeader.setForeground(Color.white);
        receivedHeader.setFont(new Font("", 1, 14));
        receivedHeadPanel.add(receivedHeader, BorderLayout.CENTER);

        // text area panel
        receivedPanel = new JPanel();
        receivedPanel.setLayout(new BorderLayout());
        receivedPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, whiteBack));
        messageReceived.add(receivedPanel, BorderLayout.CENTER);

        // text area
        receivedMessageDisplay = new JTextArea();
        receivedMessageDisplay.setBackground(secondBGC);
        receivedMessageDisplay.setForeground(typography);
        receivedMessageDisplay.setFont(new Font("", 4, 12));
        receivedMessageDisplay.setLineWrap(true);
        receivedMessageDisplay.setEditable(false);

        // scroll pane
        sp = new JScrollPane(receivedMessageDisplay);
        receivedPanel.add(sp, BorderLayout.CENTER);

        // send message
        sendMessage = new JPanel();
        sendMessage.setLayout(new BorderLayout());

        // send message head panel
        sendMessageHeadPanel = new JPanel();
        sendMessageHeadPanel.setLayout(new BorderLayout());
        sendMessageHeadPanel.setBackground(MainColor);
        sendMessage.add(sendMessageHeadPanel, BorderLayout.NORTH);

        // send message header
        sendMessageHeader = new JLabel("Send Message", SwingConstants.CENTER);
        sendMessageHeader.setForeground(Color.white);
        sendMessageHeader.setFont(new Font("", 1, 14));
        sendMessageHeadPanel.add(sendMessageHeader, BorderLayout.CENTER);

        // send message body
        sendMessageBody = new JPanel();
        sendMessageBody.setLayout(new BorderLayout());
        sendMessage.add(sendMessageBody, BorderLayout.CENTER);

        // select unit panel
        selectUnitPanel = new JPanel();
        selectUnitPanel.setLayout(new BorderLayout());
        selectUnitPanel.setBorder(BorderFactory.createMatteBorder(30, 10, 0, 200, whiteBack));
        sendMessageBody.add(selectUnitPanel, BorderLayout.NORTH);

        // select unit
        String[] unitNames = {"Public", "Helicopter", "Tank", "Submarine"};
        selectUnit = new JComboBox<>(unitNames);
        selectUnit.setFont(new Font("", 1, 12));
        selectUnit.setForeground(typography);
        selectUnit.setBackground(secondBGC);
        selectUnitPanel.add(selectUnit, BorderLayout.CENTER);

        // send message area
        sendMessageArea = new JPanel();
        sendMessageArea.setLayout(new BorderLayout());
        sendMessageArea.setBorder(BorderFactory.createMatteBorder(25, 10, 35, 10, whiteBack));
        sendMessageBody.add(sendMessageArea, BorderLayout.CENTER);

        // send message text field
        sendMessageBox = new JTextField();
        sendMessageBox.setBackground(headerColor);
        sendMessageBox.setForeground(typography);
        sendMessageBox.setFont(new Font("", 1, 12));
        sendMessageArea.add(sendMessageBox, BorderLayout.CENTER);

        // send button
        btnSend = new JButton("Send");
        btnSend.setFont(new Font("", 1, 16));
        btnSend.setBackground(OverMain);
        btnSend.setForeground(Color.white);
        btnSend.setBorderPainted(false);
        btnSend.setFocusPainted(false);
        btnSend.setEnabled(false);
        sendMessageArea.add(btnSend, BorderLayout.EAST);

        sendMessageBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                String value = sendMessageBox.getText();
                int length = value.length();

                boolean condition = observable.checkString(value, length);

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
                observable.addMessage(new UnitDetails(selectUnit.getSelectedIndex(), "Head Quart", sendMessageBox.getText()));
                sendMessageBox.setText("");
                btnSend.setEnabled(false);
                btnSendHover(false);
            }
        });

        add(areaPanel);
        add(defenseUnitInfo);
        add(messageReceived);
        add(sendMessage);
    }

    @Override
    public void areaUpdate(int count) {
    }

    @Override
    public void gunsEnable(int index) {
    }

    @Override
    public int getAreaStrengthLevels() {
        return areaStrengthLevels.getValue();
    }

    // text area clear
    @Override
    public void textAreaClear() {
        receivedMessageDisplay.setText("");
    }

    // update message
    @Override
    public void updateMessage(int unitNumber, String unitName, String message) {
        if (unitNumber == 0) {
            receivedMessageDisplay.append(unitName + " : " + message + "\n");
        } else if (unitNumber == 1) {
            receivedMessageDisplay.append(unitName + " to P(H)" + " : " + message + "\n");
        } else if (unitNumber == 2) {
            receivedMessageDisplay.append(unitName + " to P(T)" + " : " + message + "\n");
        } else if (unitNumber == 3) {
            receivedMessageDisplay.append(unitName + " to P(S)" + " : " + message + "\n");
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

    // add unit info table rows
    @Override
    public void addTableRows(DefenseObserver data, int soldier, int ammo) {
        String name = data.getClass().getName();
        String[] str = name.split(".", 6);

        String word = "";

        for (String a : str) {
            word += a;
        }

        Object[] rowData = {word, soldier, ammo};
        dtm.addRow(rowData);
    }

    // add info to table
    @Override
    public void addUnitInfoMain(String unitName, String infoName, int data) {
        switch (unitName) {
            case "Helicopter":
                switch (infoName) {
                    case "Soldier":
                        unitInfo.setValueAt(data, 0, 1);
                        break;
                    case "Ammo":
                        unitInfo.setValueAt(data, 0, 2);
                        break;
                }
                break;
            case "Tank":
                switch (infoName) {
                    case "Soldier":
                        unitInfo.setValueAt(data, 1, 1);
                        break;
                    case "Ammo":
                        unitInfo.setValueAt(data, 1, 2);
                        break;
                }
                break;
            case "Submarine":
                switch (infoName) {
                    case "Soldier":
                        unitInfo.setValueAt(data, 2, 1);
                        break;
                    case "Ammo":
                        unitInfo.setValueAt(data, 2, 2);
                        break;
                }
                break;
        }
    }

    // get unit soldier info
    @Override
    public int getSoldier() {
        return 0;
    }

    ;
    
    // get unit ammo info
    @Override
    public int getAmmo() {
        return 0;
    }
}
