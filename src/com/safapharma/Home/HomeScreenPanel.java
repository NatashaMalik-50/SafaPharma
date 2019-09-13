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
import com.safapharma.Templates.MainScreenPanel;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Natasha Malik
 */
public class HomeScreenPanel extends MainScreenPanel {

    private MainWindow manager;
    private JTable billEntriesTable;
    private DefaultTableModel tableModel;
    private final HomeScreenBackend homeScreenBackend;
    private TableRowSorter sorter;
    private DataWithColumn stockData;
    private DataWithColumn billData;
    private final HomeScreenPanel thisHome;
    private Object[] column = {"Sr.No", "Medicine Name", "Quantity", "Batch Number", "Rate"};
    private JLabel totalLable;
    private JLabel totalBox;
    protected ToolbarButton btnAddCustomer;
    private ToolbarButton btnGenerateBill;
    private static int  Srno=0;
    

    public HomeScreenPanel(MainWindow manager) {
        this.manager = manager;
        this.thisHome = this;
        homeScreenBackend = new HomeScreenBackend();

        initUI();
        setListeners();
    }

    private void initUI() {

        tableModel = new CustomDefaultTableModel();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadStockData();
                return null;
            }
        }.execute();
        billEntriesTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        billEntriesTable.setRowSorter(sorter);
        
        billEntriesTable.getTableHeader().setResizingAllowed(false);
        billEntriesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        billEntriesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        billEntriesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(billEntriesTable);
        //addButton.setText("Add Medicine Inside Bill");
        //removeButton.setText("Remove Medicine From Bill");
        //viewButton.setText("View Medicine In Detail");
        //updateButton.setText("Update Quatity Of Medicine");
        totalLable = new JLabel();
        totalLable.setText("Total :");
        totalLable.setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);

        totalBox = new JLabel();
        totalBox.setText("₹ " + "0.0");
        totalBox.setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);

        btnGenerateBill = new ToolbarButton("Bill");
        btnAddCustomer = new ToolbarButton("AddCustomer");
        //btnAddCustomer = new ToolbarButton("Search",new ImageIcon(getClass().getResource(IconConstants.SEARCH_ICON)));
        btnAddCustomer.setText("ADD CUSTOMER");
        btnGenerateBill.setText("Generate Bill");
        btnGenerateBill.setEnabled(false);
        //TotalBox.
        getTotalPanel().setLayout(new GridLayout(0, 2));

        getTotalPanel().add(totalLable);
        getTotalPanel().add(totalBox);
        getTotalPanel().add(btnAddCustomer);
        getTotalPanel().add(btnGenerateBill);

        billData = new DataWithColumn(true);

    }

    private void loadStockData() throws Exception {

        stockData = homeScreenBackend.setStockInfoIntoTable();
        //tableModel.setDataVector(null, stockData.getColumnNames());
        tableModel.setColumnIdentifiers(column);
        billEntriesTable.setModel(tableModel);
    }

    private void setListeners() {
//        billEntriesTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                enableAddButtons();
//                enableUpdateButtons();
//                enableRemoveButtons();
//                enableViewButtons();
//            }
//
//        });

        //on add button click
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (stockData == null) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        loadStockData();
                        setCursor(Cursor.getDefaultCursor());
                    }
                    manager.createNewStockViewForm(thisHome);
                    manager.showNewStockViewForm();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                if(billEntriesTable.getSelectedRow()>=0)
                    {
                        int rowIndex = billEntriesTable.getSelectedRow();
                        String exactSrno = billEntriesTable.getValueAt(rowIndex, 0).toString();
                        int id = Integer.parseInt(exactSrno);
                        int currQuantity = Integer.parseInt(billEntriesTable.getValueAt(rowIndex, 2).toString());
                        int maxQuantity=Integer.parseInt(billData.getDataOf(0).get(1).toString());
                        
                        //System.out.println(" id -- "+id);
                        manager.createNewUpdateBillForm(thisHome,id,currQuantity,maxQuantity);
                        manager.showNewUpdateBillForm();
                    }
                    else
                    {
                        //Alert ob = new Alert(Alert.AlertType.WARNING,"Select Atleast One Row",ButtonType.OK);
                    } 
                }
                catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(billEntriesTable.getSelectedRow()>=0)
                    {
                        int rowIndex = billEntriesTable.getSelectedRow();
                        String exactSrno = billEntriesTable.getValueAt(rowIndex, 0).toString();
                        int id = (Integer)billData.getDataOf(Integer.parseInt(exactSrno)-1).get(0);
                        
                        //System.out.println(exactSrno + " id -- "+id);
                        manager.createNewBillInfoViewForm(thisHome,id);
                        manager.showNewBillInfoViewForm();
                    }
                    else
                    {
                        //Alert ob = new Alert(Alert.AlertType.WARNING,"Select Atleast One Row",ButtonType.OK);
                    }
                       
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = billEntriesTable.getSelectedRow();
                    //System.out.println(id);
                    //billEntriesTable.remove(id);
                    if (billEntriesTable.getSelectedRow() >= 0) {
                        DefaultTableModel model = (DefaultTableModel) billEntriesTable.getModel();
                        model.removeRow(id);
                        Srno--;
                        
                        billData.getData().remove(id);
                        if (billEntriesTable.getRowCount() >= 0) {
                            disableRemoveButtons();
                            disableUpdateButtons();
                            disableViewButtons();
                        }
                    } else {
                        disableRemoveButtons();
                        disableUpdateButtons();
                        disableViewButtons();
                    }
                    
                    getSum();
                    
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                disableButtons();
            }
        });
        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.createCustomerPanel();
                    manager.showCustomerPanel();
                } catch (Exception ex) {
                    Logger.getLogger(HomeScreenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        billEntriesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableRemoveButtons();
                enableUpdateButtons();
                enableViewButtons();
            }
        });

        searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchBox.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    public DataWithColumn getStockData() {
        return stockData;
    }

    public void getSum() {
        float sum1 = 0;
        for (int i = 0; i < billEntriesTable.getRowCount(); i++) {
            try {
                sum1 = sum1 + Float.parseFloat(billEntriesTable.getValueAt(i, 4).toString());
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(HomeScreenPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        }
        totalBox.setText("₹ " + Float.toString(sum1));
        totalBox.setHorizontalAlignment(JTextField.CENTER);
    }
    public void updateQuantity(int quantity,int id)
    {
        //System.out.println(id);
        //System.out.println(quantity+" "+id);
        billEntriesTable.setValueAt(quantity, id-1, 2);
        
    }
    public void addRow(int rowIndex) {
        //System.out.println("found row : "+rowIndex+ " with value"+stockData.getDataOf(rowIndex)+ " --with id-- "+stockData.getIdData());
        JTextField obc = new JTextField();
        String str;
        str=stockData.getDataOf(rowIndex - 1).get(5).toString();
        obc.setText(str);
        System.out.println("Str -- "+str);
        DefaultTableModel model = (DefaultTableModel) billEntriesTable.getModel();
        Vector<Object> newRow = new Vector<>();
        Srno++;
        newRow.add(Srno);
        System.out.println("SNo added "+Srno + " for row index "+(rowIndex-1));
        newRow.add(stockData.getDataOf(rowIndex - 1).get(2).toString());
        
        System.out.println("next field - "+stockData.getDataOf(rowIndex - 1).get(2).toString());
        //newRow.add();
        //newRow.add(stockData.getDataOf(rowIndex - 1).get(3).toString());
        newRow.add(1);
        System.out.println("next field2 - "+stockData.getDataOf(rowIndex - 1).get(3).toString());
        newRow.add(stockData.getDataOf(rowIndex - 1).get(5).toString());
        System.out.println("next field3 - "+stockData.getDataOf(rowIndex - 1).get(5).toString());
        newRow.add(stockData.getDataOf(rowIndex - 1).get(4).toString());
        System.out.println("next field - "+stockData.getDataOf(rowIndex - 1).get(4).toString());
//        ob[0]=billEntriesTable.getValueAt(rowIndex-1, 0).toString();
//        ob[1]=billEntriesTable.getValueAt(rowIndex-1, 2).toString();
//        ob[2]=billEntriesTable.getValueAt(rowIndex-1, 3).toString();
//        ob[3]=billEntriesTable.getValueAt(rowIndex-1, 7).toString();
//        ob[4]=billEntriesTable.getValueAt(rowIndex-1, 4).toString();
        //model.addRow(stockData.getDataOf(rowIndex-1));
        model.addRow(newRow);
        //model.setValueAt(false);
        //bill row also stores id of the object
        Vector<Object> billRow = new Vector<>();
        billRow.add(stockData.getIdBySerialNo((Integer)(stockData.getDataOf(rowIndex - 1).get(0))));
        billRow.add(stockData.getDataOf(rowIndex - 1).get(3).toString());
        System.out.println("Bill row "+billRow);
        billData.getData().add(billRow);
        System.out.print("Vec -- " + billData.getData());

        /*Vector v = new Vector();
        v.add("Sr.No");
        v.add("Medicine Name");
        v.add("Quantity");
        v.add("Supplier Name");
        v.add("Rate");
        Vector ob = new Vector();
        
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(ob, v);
         */
        getSum();

    }

    @Override
    protected void addAlerts() {
        manager.viewExpiredMedicineThroughStatusPanel();
    }
}
