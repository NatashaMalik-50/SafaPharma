/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Main;

import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Home.MenuPanel;
import com.safapharma.Home.Supplier.SupplierPanel;
import com.safapharma.Login.LoginDialog;
import com.safapharma.ModelObjects.User;
import java.awt.CardLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Natasha Malik
 */
public class MainWindow extends javax.swing.JFrame {

    private CardLayout c;
    private LoginDialog login;
    private static User currentUser;
    private HomeScreenPanel home;
    private SupplierPanel supplierPanel;
    private JPanel rootPanel;
    private MenuPanel menuPanel;
    private Stack<Component> componentStack;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        componentStack = new Stack<Component>();
        createLoginDialog();
    }

    private void initPanels() {
        rootPanel = new JPanel(new CardLayout());
        menuPanel = new MenuPanel(this);
        add(menuPanel);
        add(rootPanel);
        c = (CardLayout) rootPanel.getLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createLoginDialog()//a dialog box cannot be added to a frame..hence we just make it visible or invisible
    {
        if (login == null) {
            login = new LoginDialog(this);
        }
        login.setVisible(true);
    }

    public void showLoginDialog() {

    }

    public void deleteLoginDialog(User user) {
        if (login != null) {
            currentUser = user;
            login.setVisible(false);
        }
        login = null;
        initPanels();
    }

//    public void deleteLoginDialog(User user, UserPrivilege userPrivilege) {
//        if (login != null) {
//            currentUser = user;
//            currentUserPrivilege = userPrivilege;
//            login.setVisible(false);
//            login = null;
//
//        }
//    }
    public void createHomeScreen() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (home != null) {
                deleteHomeScreen();
            }
            home = new HomeScreenPanel(this);
            rootPanel.add("Home", home);
            componentStack.push(home);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showHomeScreen() {
        if (home != null) {
            c = (CardLayout) rootPanel.getLayout();
            c.show(rootPanel, "Home");
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Homescreen object is null");
        }
    }

    public void deleteHomeScreen() {
        if (home != null) {
            c = (CardLayout) rootPanel.getLayout();
            c.removeLayoutComponent(home);
            home = null;
            login = null;
            currentUser = null;
            c = null;
            rootPanel = null;
            new MainWindow();
        }
    }

    public void createSupplierPanel() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (supplierPanel != null) {
//                deleteHomeScreen();
            }
            supplierPanel = new SupplierPanel();
            rootPanel.add("Supplier", supplierPanel);
            componentStack.push(supplierPanel);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showSupplierPanel() {
        if (supplierPanel != null) {
            c = (CardLayout) rootPanel.getLayout();
            c.show(rootPanel, "Supplier");
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Supplier object is null");
        }
    }

    public void deleteSupplierPanel() {
        if (supplierPanel != null) {
            c = (CardLayout) rootPanel.getLayout();
            c.removeLayoutComponent(supplierPanel);
            supplierPanel = null;
        }
    }

    public void deleteCurrentPanel() {
        Component component = componentStack.pop();
        if (component == home) {
            deleteHomeScreen();
        } else if (component == supplierPanel) {
            deleteSupplierPanel();
        }
    }

    public boolean isHome() {
        return (componentStack.peek() == home);
    }

    static public User getCurrentUser() {

        return currentUser;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
//        try {
//            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
//        } catch (Exception e) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow m = new MainWindow();
                m.setExtendedState(MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
