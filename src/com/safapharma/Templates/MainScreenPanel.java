/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Templates;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Helpers.IconConstants;
import java.awt.Cursor;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Natasha Malik
 */
public class MainScreenPanel extends javax.swing.JPanel {

    protected ToolbarButton addButton, removeButton, viewButton, updateButton;
//    protected JComboBox<String> comboBox;
    protected JTextField searchBox;
    protected ToolbarButton searchButton;

    /**
     * Creates new form NewJPanel
     */
    public MainScreenPanel() {
        initComponents();
        initUI();
    }

    private void initUI() {
        toolbarPanel.setLayout(new GridLayout(1, 5));
        toolbarPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        addButton = new ToolbarButton("Add");
        removeButton = new ToolbarButton("Remove");
        viewButton = new ToolbarButton("View");
        updateButton = new ToolbarButton("Update");
        toolbarPanel.add(addButton);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(removeButton);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(viewButton);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(updateButton);

        //disableButtons();
        disableRemoveButtons();
        disableUpdateButtons();
        disableViewButtons();

        statusLabel.setText("Bill Status");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

//        comboBox = new JComboBox<String>();
//        comboBox.addItem("Search By Name");
//        comboBox.addItem("Search By Contact No.");
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchBox = new JTextField();
        searchBox.setFont(DesignConstants.FONT_SIZE_16_CALIBRI);
        searchButton = new ToolbarButton("Search", new ImageIcon(getClass().getResource(IconConstants.SEARCH_ICON)));
        searchButton.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
//        searchPanel.add(comboBox);
        searchPanel.add(searchBox);
        searchPanel.add(searchButton);
    }

    protected void enableButtons() {
        addButton.setEnabled(true);
        updateButton.setEnabled(true);
        removeButton.setEnabled(true);
        viewButton.setEnabled(true);
    }

    protected void enableAddButtons() {
        addButton.setEnabled(true);
    }

    protected void enableUpdateButtons() {
        updateButton.setEnabled(true);
    }

    protected void enableRemoveButtons() {
        removeButton.setEnabled(true);
    }

    protected void enableViewButtons() {
        viewButton.setEnabled(true);
    }

    protected void disableAddButtons() {
        addButton.setEnabled(false);
    }

    protected void disableUpdateButtons() {
        updateButton.setEnabled(false);
    }

    protected void disableRemoveButtons() {
        removeButton.setEnabled(false);
    }

    protected void disableViewButtons() {
        viewButton.setEnabled(false);
    }

    protected void disableButtons() {
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        removeButton.setEnabled(false);
        viewButton.setEnabled(false);
    }

    protected JPanel getToolbar() {
        return toolbarPanel;
    }

    protected JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }
    protected JPanel getTotalPanel(){
        return TotalPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbarPanel = new javax.swing.JPanel();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        searchPanel = new javax.swing.JPanel();
        TotalPanel = new javax.swing.JPanel();

        toolbarPanel.setPreferredSize(new java.awt.Dimension(450, 40));

        javax.swing.GroupLayout toolbarPanelLayout = new javax.swing.GroupLayout(toolbarPanel);
        toolbarPanel.setLayout(toolbarPanelLayout);
        toolbarPanelLayout.setHorizontalGroup(
            toolbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 936, Short.MAX_VALUE)
        );
        toolbarPanelLayout.setVerticalGroup(
            toolbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        statusLabel.setText("jLabel1");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        searchPanel.setPreferredSize(new java.awt.Dimension(450, 40));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        TotalPanel.setMaximumSize(new java.awt.Dimension(588, 39));
        TotalPanel.setMinimumSize(new java.awt.Dimension(588, 39));

        javax.swing.GroupLayout TotalPanelLayout = new javax.swing.GroupLayout(TotalPanel);
        TotalPanel.setLayout(TotalPanelLayout);
        TotalPanelLayout.setHorizontalGroup(
            TotalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        TotalPanelLayout.setVerticalGroup(
            TotalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(toolbarPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TotalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TotalPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JPanel toolbarPanel;
    // End of variables declaration//GEN-END:variables

    protected class ToolbarButton extends JButton {

        public ToolbarButton(String text, ImageIcon icon) {
            super(text,icon);
            init();
        }

        public ToolbarButton(String text) {
            super(text);
            init();
        }

        void init() {
//            setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.yellow));
            setFont(DesignConstants.FONT_SIZE_16_CALIBRI_BOLD);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }

}
