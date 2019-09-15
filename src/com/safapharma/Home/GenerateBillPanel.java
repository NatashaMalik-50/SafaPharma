/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author shiva
 */
public class GenerateBillPanel extends DialogForm {
    
    private final MainWindow manager;
    private HomeScreenPanel homeScreenPanel;
    private JLabel lblInvoice;
    private JLabel lblInvoiceNumber;
    private JLabel lblDate;
    private JLabel lblDateNow;
    private JLabel lblBilledTo;
    private JLabel lblCustName;
    private JLabel lblCustInfo;
    private JLabel lblTotal;
    private JLabel lblTotalNumber;
    private JButton btnPrint;
    private JPanel PanelInvoice;
    private JPanel PanelDate;
    private JPanel PanelTabel;
    private JPanel PanelCustDetails;
    private JPanel PanelBottom;
    private DefaultTableModel tableModel;
    private JTable billTable;
    private JScrollPane scrollPane;
    //private final DataWithColumn stockDataWithColumn;
    private TableRowSorter sorter;
    
        
    
    public GenerateBillPanel(MainWindow manager,HomeScreenPanel homeScreenPanel,DefaultTableModel billData,float totalValue){
        this.homeScreenPanel = homeScreenPanel;
        this.manager = manager;
        this.tableModel = (this.homeScreenPanel != null) ? billData : null;
        if (this.tableModel == null) {
            JOptionPane optionPane = new JOptionPane("Stock cannot be viewed at the moment.");
            JDialog dialog = optionPane.createDialog("Stock Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            manager.deleteNewStockViewForm();
            dispose();
        }
        initUI();
        setValues(totalValue);
//        addListeners();
    }
    private void setValues(float totalValues)
    {
        String s = Float.toString(totalValues);
        lblTotalNumber.setText(s);
        
    }
    private void initUI() 
    {
        getFormLabel().setText("SafaPharma Bill");
        lblInvoice = new JLabel();
        lblInvoice.setText("Invoice Number");
        lblInvoiceNumber = new JLabel();
        lblInvoiceNumber.setText("0001");
        
        lblDate = new JLabel();
        lblDate.setText("Date Of Issue");
        lblDateNow = new JLabel();
        lblDateNow.setText("12-12-12");
        
        PanelInvoice = new JPanel();
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
        
        lblCustInfo = new JLabel();
        lblCustName = new JLabel();
        lblCustInfo.setText("Billed To");
        lblCustName.setText("Shivam Saxena");
        PanelCustDetails = new JPanel();
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
        //stockDataWithColumn.setViewportView(billTable);
        
        
        //getFormPanel().setLayout(new GridLayout(0,1));
        PanelTabel = new JPanel();
        PanelTabel.setLayout(new BoxLayout(PanelTabel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(billTable);
        PanelTabel.add(scrollPane);
        getFormPanel().add(PanelTabel);
        
        lblTotal = new JLabel();
        lblTotalNumber = new JLabel();
        btnPrint = new JButton();
        PanelBottom = new JPanel();
        lblTotal.setText("Total Amount : ");
        lblTotalNumber.setText("100");
        btnPrint.setText("Confirm");
        PanelBottom.add(btnPrint);
        PanelBottom.add(lblTotal);
        PanelBottom.add(lblTotalNumber);
        getFormPanel().add(PanelBottom);
        
        this.pack();
        
        
    }
    
   
    
  @Override
  protected void deleteScreen() {
        manager.deleteGenerateBillPanel();
    }
    
}
