/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import com.safapharma.Helpers.ClockLabel;
import static com.safapharma.Helpers.Constants.DATE_LABEL;
import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.safapharma.ModelObjects.Customer;
import java.util.Date;


/**
 *
 * @author shiva
 */
public class GenerateBillPanel extends javax.swing.JDialog {
    
    private final MainWindow manager;
    private HomeScreenPanel homeScreenPanel;
    private GenerateBillPanelBackend generateBillPanelBackend;
    private DefaultTableModel tableModel;
    private TableRowSorter sorter;
    private JTable billTable;
    //private JScrollPane scrollPane;
    
    /**
     * Creates new form BillDesignForm
     */
    public GenerateBillPanel(MainWindow manager,HomeScreenPanel homeScreenPanel,DefaultTableModel billData,float totalValue,Customer customer) {
        
//        super(parent, modal);
        initComponents();
           this.homeScreenPanel = homeScreenPanel;
           
        this.manager = manager;
        billTable = new JTable(billData);
        //billTable = (this.homeScreenPanel != null) ? billData : null;
        jScrollPane1.setViewportView(billTable);
        if (jScrollPane1 == null) {
            JOptionPane optionPane = new JOptionPane("Add Some Medicines First !!!");
            JDialog dialog = optionPane.createDialog("Bill Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            //manager.deleteGenerateBillPanel();
            dispose();
            
        }
         else
        {
           generateBillPanelBackend = new GenerateBillPanelBackend(customer,billData);
            initUI();
            setValues(totalValue);
        }
    }
    private void initUI() 
    {
        lblName.setText("SafaPharma Bill");
        //lblInvoice = new JLabel();
        lblInvoice.setText("Invoice No. :");
        
        //lblInvoiceNumber = new JLabel();
        lblInvoiceNumber.setText("0001");
        
        //lblDate = new JLabel();
        lblDate.setText("Date of Issue :");
        Date date = new Date();
        String d = date.toString();
        lblDateNow.setText(d);
        lblBilledTo.setText("Biller To");
        //System.out.println(generateBillPanelBackend.getCustomer().getName());
        lblCustName.setText(generateBillPanelBackend.getCustomer().getName());
        lblAddress.setText("Address");
        lblAddressValue.setText(generateBillPanelBackend.getCustomer().getAddress());
        lblTotal.setText("Total");
        //lblDateNow.setText("12-12-12");
        
        /*PanelInvoice = new JPanel();
        
        PanelDate = new JPanel();
        getFormPanel().setLayout(new GridLayout(0,2));
        PanelInvoice.setLayout(new BoxLayout(PanelInvoice, BoxLayout.Y_AXIS));
        PanelDate.setLayout(new BoxLayout(PanelDate, BoxLayout.Y_AXIS));
        PanelInvoice.add(lblInvoice);
        PanelInvoice.add(lblInvoiceNumber);
        PanelDate.add(lblDate);
        PanelDate.add(lblDateNow);
        getFormPanel().add(PanelInvoice);
        getFormPanel().add(PanelDate);
        */
        //lblCustInfo = new JLabel();
        //lblCustName = new JLabel();
        //lblCustInfo.setText("Billed To");
        //lblCustName.setText("Shivam Saxena");
       /* PanelCustDetails = new JPanel();
        PanelCustDetails.setLayout(new BoxLayout(PanelCustDetails, BoxLayout.Y_AXIS));
        PanelCustDetails.add(lblCustInfo);
        PanelCustDetails.add(lblCustName);
        getFormPanel().add(PanelCustDetails);
        
        billTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        billTable.setRowSorter(sorter);
        
        billTable.getTableHeader().setResizingAllowed(false);
        billTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        billTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        billTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        */
        //scrollPane = new JScrollPane();
        //scrollPane.setViewportView(billTable);
        //jPanel1.add(scrollPane);
        //getFormPanel().add(PanelTabel);
        //stockDataWithColumn.setViewportView(billTable);
        
        
        //getFormPanel().setLayout(new GridLayout(0,1));
       /* PanelTabel = new JPanel();
        PanelTabel.setLayout(new BoxLayout(PanelTabel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(billTable);
        PanelTabel.add(scrollPane);
        getFormPanel().add(PanelTabel);
        */
        //lblTotal = new JLabel();
        //lblTotalNumber = new JLabel();
        //btnPrint = new JButton();
        //PanelBottom = new JPanel();
        //lblTotal.setText("Total Amount : ");
        //lblTotalNumber.setText("100");
        //btnPrint.setText("Confirm");
        //PanelBottom.add(btnPrint);
        //PanelBottom.add(lblTotal);
        //PanelBottom.add(lblTotalNumber);
        //getFormPanel().add(PanelBottom);
        
        this.pack();
        
        
    }
     private void setValues(float totalValues)
    {
        String s = Float.toString(totalValues);
        lblTotalNumber.setText(s);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInvoice = new javax.swing.JLabel();
        lblInvoiceNumber = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDateNow = new javax.swing.JLabel();
        lblBilledTo = new javax.swing.JLabel();
        lblCustName = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblAddressValue = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTotalNumber = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblInvoice.setText("lblInvoice");

        lblInvoiceNumber.setText("lblInvoiceNumber");

        lblDate.setText("lblDate");

        lblDateNow.setText("lblDateNow");

        lblBilledTo.setText("lblBilledTo");

        lblCustName.setText("lblCustName");

        lblAddress.setText("lblAddress");

        lblAddressValue.setText("lblAddressValue");

        lblName.setText("SAFAPHARMA");

        lblTotal.setText("lblTotal");

        lblTotalNumber.setText("lblTotalNumber");

        btnPrint.setText("Print");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblInvoice)
                        .addGap(18, 18, 18)
                        .addComponent(lblInvoiceNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDate)
                        .addGap(18, 18, 18)
                        .addComponent(lblDateNow)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(lblName))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBilledTo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(lblAddress)
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCustName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAddressValue)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalNumber)
                            .addComponent(lblTotal))
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoice)
                    .addComponent(lblDate)
                    .addComponent(lblInvoiceNumber)
                    .addComponent(lblDateNow))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBilledTo)
                    .addComponent(lblAddress))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustName)
                    .addComponent(lblAddressValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(15, 15, 15)
                        .addComponent(lblTotalNumber)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnPrint)
                        .addGap(43, 43, 43))))
        );

        btnPrint.getAccessibleContext().setAccessibleName("btnPrint");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillDesignForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillDesignForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillDesignForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillDesignForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the dialog */
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAddressValue;
    private javax.swing.JLabel lblBilledTo;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDateNow;
    private javax.swing.JLabel lblInvoice;
    private javax.swing.JLabel lblInvoiceNumber;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalNumber;
    // End of variables declaration//GEN-END:variables
}
