/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.ClockLabel;
import static com.safapharma.Helpers.Constants.BACK_LABEL;
import static com.safapharma.Helpers.Constants.DATE_LABEL;
import static com.safapharma.Helpers.Constants.LOG_OUT_LABEL;
import static com.safapharma.Helpers.Constants.TIME_LABEL;
import com.safapharma.Helpers.DesignConstants;
import static com.safapharma.Helpers.IconConstants.BACK_BUTTON_ICON;
import static com.safapharma.Helpers.IconConstants.LOG_OUT_ICON;
import com.safapharma.Main.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Natasha Malik
 */
public class MenuPanel extends javax.swing.JPanel {

    private MainWindow manager;
    private ClockLabel dateLabel;
    private ClockLabel timeLabel;
    private JPanel timeAndInfoPanel;
    private JLabel welcomeMsgLabel;
    private JButton backButton;
    private JButton logOutButton;
//    private final ClockLabel dayLabel;

    /**
     * Creates new form MenuPanel
     */
    public MenuPanel(MainWindow manager) {
        initComponents();
        this.manager = manager;
        initUI();
        addListeners();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, 50));
//        setBackground(Color.gray);
//        backButton = new JButton(BACK_LABEL, manager.resizeIcon(new javax.swing.ImageIcon(getClass().getResource(BACK_BUTTON_ICON)), 35, 40));
        backButton = new JButton(BACK_LABEL, new javax.swing.ImageIcon(getClass().getResource(BACK_BUTTON_ICON)));
        logOutButton = new JButton(LOG_OUT_LABEL, new javax.swing.ImageIcon(getClass().getResource(LOG_OUT_ICON)));
//        logOutButton.setHorizontalAlignment(SwingConstants.CENTER);
//        logOutButton.setVerticalAlignment(SwingConstants.CENTER);
//        logOutButton.setIconTextGap(1);
//        logOutButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        backButton.setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        logOutButton.setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        welcomeMsgLabel = new JLabel("Welcome, " + manager.getCurrentUser().getName() + "!");
        welcomeMsgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMsgLabel.setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);
        dateLabel = new ClockLabel(DATE_LABEL);
        timeLabel = new ClockLabel(TIME_LABEL);
        timeAndInfoPanel = new JPanel();
        timeAndInfoPanel.setLayout(new BorderLayout());
        timeAndInfoPanel.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
//        timeAndInfoPanel.setBackground(Color.gray);
        timeAndInfoPanel.add(dateLabel, BorderLayout.WEST);
        timeAndInfoPanel.add(welcomeMsgLabel, BorderLayout.CENTER);
        timeAndInfoPanel.add(timeLabel, BorderLayout.EAST);

        setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, DesignConstants.BORDER_COLOR), BorderFactory.createEmptyBorder(2, 6, 2, 6)));
        add(backButton, BorderLayout.WEST);
        add(timeAndInfoPanel, BorderLayout.CENTER);
        add(logOutButton, BorderLayout.EAST);
    }

    private void addListeners() {
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

    }

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        manager.deleteHomeScreen();
        manager.setVisible(false);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (manager.isHome()) {
            //            buttonLogOutActionPerformed(null);
        } else {
            manager.deleteCurrentPanel();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
