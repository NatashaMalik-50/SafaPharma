/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Main;

import com.safapharma.Customer.CustomerPanel;
import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.*;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.Home.MenuPanel;
import com.safapharma.Home.NewStockViewForm;
import com.safapharma.Home.Sales.SalesPanel;
import com.safapharma.Home.SidePanel;
import com.safapharma.Home.Supplier.NewSupplierForm;
import com.safapharma.Home.Supplier.SupplierBackend;
import com.safapharma.Home.Supplier.SupplierPanel;
import com.safapharma.Login.LoginDialog;
import com.safapharma.MedicineLot.MedicineLotPanel;
import com.safapharma.ModelObjects.User;
import com.safapharma.Stock.NewStockForm;
import com.safapharma.Stock.StockPanel;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.util.Stack;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Natasha Malik
 */
public class MainWindow extends javax.swing.JFrame {

    private CardLayout c;
    private LoginDialog login;
    private static User currentUser;
    private JPanel mainPanel;
    private JPanel screenPanel;
    private MenuPanel menuPanel;
    private SidePanel sidePanel;
    private final Stack<Component> componentStack;
    private SupplierPanel supplierPanel;
    private CustomerPanel customerPanel;
    private StockPanel stockPanel;
    private SalesPanel salesPanel;
    private MedicineLotPanel medicinePanel;
    private NewStockViewForm newStockViewForm;
    private NewSupplierForm newSupplierForm;
    private NewStockForm newStockForm;
    private HomeScreenPanel home;

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
        mainPanel = new JPanel(new BorderLayout());
        menuPanel = new MenuPanel(this);
        screenPanel = new JPanel(new CardLayout());
        sidePanel = new SidePanel(this);
        screenPanel.setBackground(Color.CYAN);
        mainPanel.add(menuPanel, BorderLayout.NORTH);
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(screenPanel, BorderLayout.CENTER);
        add(mainPanel);
        c = (CardLayout) screenPanel.getLayout();
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
            screenPanel.add(HOMESCREEN_LABEL, home);
            componentStack.push(home);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showHomeScreen() {
        if (home != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.show(screenPanel, HOMESCREEN_LABEL);
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Homescreen object is null");
        }
    }

    public void deleteHomeScreen() {
        if (home != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.removeLayoutComponent(home);
            home = null;
            login = null;
            currentUser = null;
            c = null;
            screenPanel = null;
            new MainWindow();
        }
    }

    public void createSupplierPanel() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (supplierPanel != null) {
                deleteSupplierPanel();
            }
            supplierPanel = new SupplierPanel(this);
            screenPanel.add(SUPPLIER_LABEL, supplierPanel);
            componentStack.push(supplierPanel);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showSupplierPanel() {
        if (supplierPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.show(screenPanel, SUPPLIER_LABEL);
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Supplier panel object is null");
        }
    }

    public void deleteSupplierPanel() {
        if (supplierPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.removeLayoutComponent(supplierPanel);
            supplierPanel = null;
        }
    }

    public void createCustomerPanel() {
//        try {
//            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            if (customerPanel != null) {
//                deleteSupplierPanel();
//            }
//            customerPanel = new CustomerPanel(this);
//            screenPanel.add(CUSTOMER_LABEL, customerPanel);
//            componentStack.push(customerPanel);
//        } finally {
//            setCursor(Cursor.getDefaultCursor());
//        }
    }

    public void showCustomerPanel() {
//        if (customerPanel != null) {
//            c = (CardLayout) screenPanel.getLayout();
//            c.show(screenPanel, CUSTOMER_LABEL);
//            setCursor(Cursor.getDefaultCursor());
//        } else {
//            System.out.println("Customer object is null");
//        }
    }

    public void deleteCustomerPanel() {
//        if (customerPanel != null) {
//            c = (CardLayout) screenPanel.getLayout();
//            c.removeLayoutComponent(customerPanel);
//            customerPanel = null;
//        }
    }

    public void createSalesPanel() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (salesPanel != null) {
                deleteSalesPanel();
            }
            salesPanel = new SalesPanel(this);
            screenPanel.add(SALES_LABEL, salesPanel);
            componentStack.push(salesPanel);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showSalesPanel() {
        if (salesPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.show(screenPanel, SALES_LABEL);
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Sales object is null");
        }
    }

    public void deleteSalesPanel() {
        if (supplierPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.removeLayoutComponent(supplierPanel);
            supplierPanel = null;
        }
    }

    public void createStockPanel() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (stockPanel != null) {
                deleteStockPanel();
            }
            stockPanel = new StockPanel(this);
            screenPanel.add(STOCK_LABEL,stockPanel);
            componentStack.push(stockPanel);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public void showStockPanel() {
        if (stockPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.show(screenPanel, STOCK_LABEL);
            setCursor(Cursor.getDefaultCursor());
        } else {
            System.out.println("Stock object is null");
        }
    }

    public void deleteStockPanel() {
        if (stockPanel != null) {
            c = (CardLayout) screenPanel.getLayout();
            c.removeLayoutComponent(stockPanel);
            stockPanel = null;
        }
    }

    public void createMedicinePanel() {
//        try {
//            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            if (salesPanel != null) {
//                deleteSalesPanel();
//            }
//            salesPanel = new SalesPanel(this);
//            screenPanel.add(SALES_LABEL, salesPanel);
//            componentStack.push(salesPanel);
//        } finally {
//            setCursor(Cursor.getDefaultCursor());
//        }
    }

    public void showMedicinePanel() {
//        if (salesPanel != null) {
//            c = (CardLayout) screenPanel.getLayout();
//            c.show(screenPanel, SALES_LABEL);
//            setCursor(Cursor.getDefaultCursor());
//        } else {
//            System.out.println("Sales object is null");
//        }
    }

    public void deleteMedicinePanel() {
//        if (supplierPanel != null) {
//            c = (CardLayout) screenPanel.getLayout();
//            c.removeLayoutComponent(supplierPanel);
//            supplierPanel = null;
//        }
    }
    
    public void createNewStockViewForm() throws Exception {
        if (newStockViewForm != null) {
            deleteNewStockViewForm();
        }
        newStockViewForm = new NewStockViewForm(this);        
    }

    public void showNewStockViewForm() {
        if (newStockViewForm != null) {
            newStockViewForm.setVisible(true);
        }
    }

    public void deleteNewStockViewForm() {
        if (newStockViewForm != null) {
            newStockViewForm.setVisible(false);
            newStockViewForm = null;
        }
    }
    public void createNewStockForm() throws Exception {
        if (newStockForm != null) {
            deleteNewStockForm();
        }
        newStockForm = new NewStockForm(this);        
    }

    public void showNewStockForm() {
        if (newStockForm != null) {
            newStockForm.setVisible(true);
        }
    }

    public void deleteNewStockForm() {
        if (newStockForm != null) {
            newStockForm.setVisible(false);
            newStockForm = null;
        }
    }
     public void createNewSupplierForm(SupplierBackend supplierBackend) throws Exception {
        if (newSupplierForm != null) {
            deleteNewSupplierForm();
        }
        newSupplierForm = new NewSupplierForm(this,supplierBackend);
    }

    public void showNewSupplierForm() {
        if (newSupplierForm != null) {
            newSupplierForm.setVisible(true);
        }
    }

    public void deleteNewSupplierForm() {
        if (newSupplierForm != null) {
            newSupplierForm.setVisible(false);
            newSupplierForm = null;
        }
    }
    public void createNewSalesForm() throws Exception {
//        if (newSaleForm != null) {
//            deleteNewRoleForm();
//        }
//        newSaleForm = new NewSaleForm(this);        
    }

    public void showNewSaleForm() {
//        if (newSaleForm != null) {
//            newSaleForm.setVisible(true);
//        }
    }

    public void deleteNewSaleForm() {
//        if (newSaleForm != null) {
//            newSaleForm.setVisible(false);
//            newSaleForm = null;
//        }
    }

    public void deleteCurrentPanel() {
        Component component = componentStack.pop();
        if (component == home) {
            deleteHomeScreen();
        } else if (component == supplierPanel) {
            deleteSupplierPanel();
        }
        sidePanel.backPressedChangeActive();
    }

    public boolean isHome() {
        return (componentStack.peek() == home);
    }

    static public User getCurrentUser() {

        return currentUser;

    }

    public static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
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

    public Icon resizeIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
