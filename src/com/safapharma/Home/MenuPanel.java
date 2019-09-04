/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.ClockLabel;
import static com.safapharma.Helpers.Constants.DATE_LABEL;
import static com.safapharma.Helpers.Constants.DAY_LABEL;
import static com.safapharma.Helpers.Constants.TIME_LABEL;
import com.safapharma.Main.MainWindow;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Natasha Malik
 */
public class MenuPanel extends javax.swing.JPanel {

    private MainWindow manager;
    private final ClockLabel dateLabel;
    private final ClockLabel timeLabel;
//    private final ClockLabel dayLabel;

    /**
     * Creates new form MenuPanel
     */
    public MenuPanel(MainWindow manager) {
        initComponents();
        this.manager = manager;
        buttonLogOut.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLogOut.setVerticalAlignment(SwingConstants.CENTER);
        buttonLogOut.setIconTextGap(1);
        buttonLogOut.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        labelWelcomeMsg.setText("Welcome, " + manager.getCurrentUser().getName() + "!");
        jPanel1.setLayout(new BorderLayout());
        dateLabel = new ClockLabel(DATE_LABEL);
//        dayLabel = new ClockLabel(DAY_LABEL);
        timeLabel = new ClockLabel(TIME_LABEL);
        jPanel1.add(dateLabel, BorderLayout.CENTER);
//        jPanel1.add(dayLabel,BorderLayout.SOUTH);
        jPanel1.add(timeLabel, BorderLayout.NORTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonLogOut = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelWelcomeMsg = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();

        buttonLogOut.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        buttonLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        buttonLogOut.setText("Log Out");
        buttonLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonLogOut.setPreferredSize(new java.awt.Dimension(147, 60));
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 60));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        labelWelcomeMsg.setFont(new java.awt.Font("Arial Black", 1, 22)); // NOI18N
        labelWelcomeMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWelcomeMsg.setPreferredSize(new java.awt.Dimension(350, 60));

        buttonBack.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        buttonBack.setText("Back");
        buttonBack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonBack.setPreferredSize(new java.awt.Dimension(147, 60));
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(labelWelcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelWelcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        manager.deleteHomeScreen();
        manager.setVisible(false);
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        if (manager.isHome()) {
//            buttonLogOutActionPerformed(null);
        } else {
            manager.deleteCurrentPanel();
        }
    }//GEN-LAST:event_buttonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelWelcomeMsg;
    // End of variables declaration//GEN-END:variables
}