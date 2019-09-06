/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natasha Malik
 */
public class HomeScreenPanel extends javax.swing.JPanel {

    /**
     * Creates new form HomeScreenPanel
     */
    private MainWindow manager;
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private HomeScreenBackend homeScreenBackend;
    private ToolbarButton addButton, removeButton, viewButton, updateButton;

    public HomeScreenPanel(MainWindow manager) {
        initComponents();
        this.manager = manager;
        homeScreenBackend = new HomeScreenBackend();
        initUI();
        setListeners();
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

        disableButtons();

        statusLabel.setText("Bill Status");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

        tableModel = new DefaultTableModel();
        stockTable = new JTable(tableModel);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        stockTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableScrollPane.setViewportView(stockTable);
    }

    private void loadData() throws Exception {
        DataWithColumn dataWithColumn = homeScreenBackend.setStockInfoIntoTable(stockTable, tableModel);
        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
    }

    private void setListeners() {
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableButtons();
            }

        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = stockTable.getSelectedRow();
                addToStatusPanel(selectedRowIndex);
            }
        });
    }

    private void enableButtons() {
        addButton.setEnabled(true);
        updateButton.setEnabled(true);
        removeButton.setEnabled(true);
        viewButton.setEnabled(true);
    }

    private void disableButtons() {
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        removeButton.setEnabled(false);
        viewButton.setEnabled(false);
    }

    private void addToStatusPanel(int selectedRowIndex) {
        String medName = (String) tableModel.getValueAt(selectedRowIndex, 2);
        statusPanel.add(new JLabel(medName));
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
                .addGap(0, 755, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableScrollPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tableScrollPane))
                    .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JPanel toolbarPanel;
    // End of variables declaration//GEN-END:variables

    private class ToolbarButton extends JButton {

        ToolbarButton(String text, ImageIcon icon) {
        }

        ToolbarButton(String text) {
            super(text);
            init();
        }

        void init() {
//            setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.yellow));
            setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }
}
