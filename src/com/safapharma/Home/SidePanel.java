/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.BUTTON_CUSTOMER_LABEL;
import static com.safapharma.Helpers.Constants.BUTTON_SALES_LABEL;
import static com.safapharma.Helpers.Constants.BUTTON_SUPPLIER_LABEL;
import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Helpers.IconConstants;
import com.safapharma.Main.MainWindow;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Natasha Malik
 */
public class SidePanel extends javax.swing.JPanel {

    private MainWindow manager;
    private SidePaneLabel buttonCustomer;
    private SidePaneLabel buttonSales;
    private SidePaneLabel buttonSupplier;
    private SidePaneLabel buttonStock;
    private SidePaneLabel buttonMedicineLot;
    private SidePaneLabel buttonExpiredMedicines;
    private final static int WIDTH_PANEL = 130;
    private final static int HEIGHT_PANEL = 80;
    private SidePaneLabel lastActiveLabel = null;

    /**
     * Creates new form SidePanel
     *
     * @param manager
     */
    public SidePanel(MainWindow manager) {
        initComponents();
        this.manager = manager;
        initUI();
        setListeners();
    }

    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setBackground(Color.gray);
        setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, DesignConstants.BORDER_COLOR));

        buttonCustomer = new SidePaneLabel(BUTTON_CUSTOMER_LABEL, new ImageIcon(getClass().getResource(IconConstants.CUSTOMER_ICON)));
        buttonSales = new SidePaneLabel(BUTTON_SALES_LABEL, new ImageIcon(getClass().getResource(IconConstants.SALES_ICON)));
        buttonSupplier = new SidePaneLabel(BUTTON_SUPPLIER_LABEL, new ImageIcon(getClass().getResource(IconConstants.SUPPLIER_ICON)));
        buttonStock = new SidePaneLabel(Constants.BUTTON_STOCK_LABEL, new ImageIcon(getClass().getResource(IconConstants.STOCK_ICON)));
        buttonMedicineLot = new SidePaneLabel(Constants.BUTTON_MEDICINE_LOT_LABEL, new ImageIcon(getClass().getResource(IconConstants.MEDICINE_ICON)));
        buttonExpiredMedicines = new SidePaneLabel(Constants.BUTTON_EXPIRED_MEDICINE_LABEL, new ImageIcon(getClass().getResource(IconConstants.EXPIRED_MEDICINES_ICON)));
        add(buttonCustomer);
        add(buttonSales);
        add(buttonStock);
        add(buttonExpiredMedicines);
        add(buttonMedicineLot);
        add(buttonSupplier);
    }

    private void setListeners() {
        buttonSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.createSupplierPanel();
                manager.showSupplierPanel();
                if (lastActiveLabel != null) {
                    lastActiveLabel.makeLabelInactive();
                }
                buttonSupplier.makeLabelActive();
                lastActiveLabel = buttonSupplier;
                buttonSupplier.setIcon(new ImageIcon(getClass().getResource(IconConstants.SUPPLIER_RED_ICON)));
            }
        });
        buttonCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerButton(false);
            }
        });
        buttonMedicineLot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.createMedicinePanel();
                manager.showMedicinePanel();
                if (lastActiveLabel != null) {
                    lastActiveLabel.makeLabelInactive();
                }
                buttonMedicineLot.makeLabelActive();
                lastActiveLabel = buttonMedicineLot;
                buttonMedicineLot.setIcon(new ImageIcon(getClass().getResource(IconConstants.MEDICINE_RED_ICON)));
            }
        });
        buttonSales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.createSalesPanel();
                manager.showSalesPanel();
                if (lastActiveLabel != null) {
                    lastActiveLabel.makeLabelInactive();
                }
                buttonSales.makeLabelActive();
                lastActiveLabel = buttonSales;
                buttonSales.setIcon(new ImageIcon(getClass().getResource(IconConstants.SALES_RED_ICON)));
            }
        });
        buttonStock.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                manager.createStockPanel();
                manager.showStockPanel();
                if (lastActiveLabel != null) {
                    lastActiveLabel.makeLabelInactive();
                }
                buttonStock.makeLabelActive();
                lastActiveLabel = buttonStock;
                buttonStock.setIcon(new ImageIcon(getClass().getResource(IconConstants.STOCK_RED_ICON)));
            }
        });
        buttonExpiredMedicines.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expiredMedicineButton();
            }
        });
    }

    public void expiredMedicineButton() {
        manager.createExpiredMedicinesPanel();
        manager.showExpiredMedicinesPanel();
        if (lastActiveLabel != null) {
            lastActiveLabel.makeLabelInactive();
        }
        buttonExpiredMedicines.makeLabelActive();
        lastActiveLabel = buttonExpiredMedicines;
        buttonExpiredMedicines.setIcon(new ImageIcon(getClass().getResource(IconConstants.EXPIRED_MEDICINES_RED_ICON)));
    }

    public void customerButton(boolean viaBilling) {
        manager.createCustomerPanel(viaBilling);
        manager.showCustomerPanel();
        if (lastActiveLabel != null) {
            lastActiveLabel.makeLabelInactive();
        }
        buttonCustomer.makeLabelActive();
        lastActiveLabel = buttonCustomer;
        buttonCustomer.setIcon(new ImageIcon(getClass().getResource(IconConstants.CUSTOMER_RED_ICON)));
    }

    public void backPressedChangeActive() {
        if (lastActiveLabel != null) {
            lastActiveLabel.makeLabelInactive();
        }
        lastActiveLabel = null;
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private class SidePaneLabel extends JLabel {

        ImageIcon iconForLabel;

        public SidePaneLabel(String text, ImageIcon icon) {
            super(text, icon, JLabel.HORIZONTAL);
            this.iconForLabel = icon;
            setMinimumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
            setMaximumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));

            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));
            setHorizontalAlignment(JLabel.CENTER);
            setHorizontalTextPosition(JLabel.CENTER);
            setVerticalTextPosition(JLabel.BOTTOM);
            setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public void makeLabelActive() {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
            setBackground(Color.red);
            setForeground(Color.red);
        }

        public void makeLabelInactive() {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));
            setBackground(null);
            setForeground(null);
            setIcon(iconForLabel);
        }

    }
}
