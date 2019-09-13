/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.MainScreenPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import javax.swing.JComboBox;
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
 * @author akshit
 */
public class SalesPanel extends MainScreenPanel{
    
    private MainWindow manager;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private SalesBackend salesBackend;
    private TableRowSorter sorter;
    private DataWithColumn salesDataWithColumn;
    private JLabel summaryLabel;
    private JTextField filterTextBefore, filterTextTo, summaryText;
    private JComboBox filterSelectBox;
    private ToolbarButton resetButton;
    
    public SalesPanel(MainWindow manager) {

        this.manager = manager;
        salesBackend = new SalesBackend();
        initUI();
        setListeners();
    }

    private void initUI() {
        filterSelectBox = new JComboBox();
        filterSelectBox.addItem("Filter By Amount");
        filterTextBefore = new JTextField();
        filterTextTo = new JTextField();
        summaryText = new JTextField();
        summaryLabel = new JLabel("Total Amount");
        
        getToolbar().remove(addButton);
        getToolbar().remove(updateButton);
        getToolbar().remove(removeButton);
        getToolbar().remove(viewButton);
        
        getToolbar().setLayout(new GridLayout(1, 3));
        getToolbar().add(viewButton);
        getToolbar().add(removeButton);
        getToolbar().add(searchBox);
        
        searchButton.setText("Apply Filter");
        resetButton = new ToolbarButton("Reset Filter");
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

        tableModel = new DefaultTableModel();
        salesTable = new JTable(tableModel);
        sorter = new TableRowSorter(tableModel);
        salesTable.setRowSorter(sorter);
        salesTable.getTableHeader().setResizingAllowed(false);
        salesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        salesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableScrollPane().setViewportView(salesTable);
    
        getSearchPanel().setLayout(new GridLayout(1, 5));
        getSearchPanel().add(filterSelectBox);
        getSearchPanel().add(filterTextBefore);
        getSearchPanel().add(filterTextTo);
        getSearchPanel().add(searchButton);
        getSearchPanel().add(resetButton);

        getTotalPanel().setLayout( new GridLayout(1, 2));
        getTotalPanel().add(summaryLabel);
        getTotalPanel().add(summaryText);    
        }

    private void loadData() throws Exception {
        this.salesDataWithColumn = salesBackend.setSaleInfoIntoTable(salesTable, tableModel);
        if (salesDataWithColumn != null) {
            tableModel.setDataVector(salesDataWithColumn.getData(), salesDataWithColumn.getColumnNames());
        }
        getTotalSales();
    }

        public void getTotalSales() {
        float sum1 = 0;
        for (int i = 0; i < salesTable.getRowCount(); i++) {
            try {
                sum1 = sum1 + Float.parseFloat(salesTable.getValueAt(i, 5).toString());
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(SalesPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        }
        summaryText.setText(Float.toString(sum1));
        summaryText.setHorizontalAlignment(JTextField.CENTER);
    }


    private void setListeners() {
        salesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enableRemoveButtons();
                enableViewButtons();
            }

        });
        
        
        
        //on view button click
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    int row = salesTable.getSelectedRow();
                    int saleTableRowId = (Integer) tableModel.getValueAt(row, 0);
                    int saleId = salesDataWithColumn.getIdOf(saleTableRowId);

                    manager.createSaleViewForm(saleId,salesDataWithColumn.getDataOf(saleTableRowId)); //the appropriate function call
                    manager.showSaleViewForm();
                } catch (Exception ex) {
                    Logger.getLogger(SalesPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double low = Double.parseDouble(filterTextBefore.getText());
                    double high = Double.parseDouble(filterTextTo.getText());
                    // incase when value of high is lower
                    if (high < low)
                    {
                        double temp = low;
                        low = high;
                        high = temp;
                    }
                    
                    List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
                    filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, low - 1, 5));
                    filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, high + 1, 5));
                    RowFilter<Object, Object> rangeFilter = RowFilter.andFilter(filters);

                    sorter.setRowFilter(rangeFilter);
                    
                } catch (Exception ex) {
                    Logger.getLogger(SalesPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    sorter.setRowFilter(null);;
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
 
}
