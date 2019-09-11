/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.Helpers.Constants;
import com.safapharma.Templates.*;
import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Helpers.IconConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Akshit
 */
public class SalesPanel extends javax.swing.JPanel {

    protected ToolbarButton removeButton, viewButton;
    private JLabel summaryLabel;
    protected JTextField searchBox, summaryText, filterTextBefore, filterTextTo;
    protected ToolbarButton searchButton, salesAnalytics;
    private JTable salesTable;
    private CustomDefaultTableModel tableModel;
    private final SalesBackend salesBackend;
    private JComboBox filterSelectBox;
    private TableRowSorter sorter;
    protected MainWindow manager;
    
    /**
     * Creates new form NewJPanel
     */
    public SalesPanel(MainWindow manager) {
        salesBackend = new SalesBackend();
        this.manager = manager;
        initComponents();
        initUI();
        setListeners();
    }

    private void initUI() {
        tableModel = new CustomDefaultTableModel();
                
        summaryLabel = new JLabel("Total Sales:");
        summaryText = new JTextField();
        summaryPanel = new JPanel();
        
        toolbarPanel.setLayout(new GridLayout(1, 4));
        toolbarPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        removeButton = new ToolbarButton(Constants.BUTTON_REMOVE_SALES_LABEL);
        viewButton = new ToolbarButton(Constants.BUTTON_VIEW_SALES_LABEL);
 
        searchBox = new JTextField();
        searchBox.setFont(DesignConstants.FONT_SIZE_16_CALIBRI);
        searchButton = new ToolbarButton("Search",new ImageIcon(getClass().getResource(IconConstants.SEARCH_ICON)));
        searchButton.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        
        searchBox.setForeground(Color.GRAY);
        searchBox.setText(Constants.PLACEHOLDER_TEXT_SEARCH);
        
        filterTextBefore = new JTextField();  
        filterTextBefore.setForeground(Color.GRAY);
        filterTextBefore.setText(Constants.PLACEHOLDER_TEXT_DATE_FROM);

        filterTextTo = new JTextField();        
        filterTextTo.setForeground(Color.GRAY);
        filterTextTo.setText(Constants.PLACEHOLDER_TEXT_DATE_TO);
        
        toolbarPanel.add(viewButton);
        toolbarPanel.add(removeButton);
        toolbarPanel.add(searchBox);
        toolbarPanel.add(searchButton);
     
        disableRemoveButtons();
        disableViewButtons(); 
        
        // Status
        statusLabel.setText("Bill Status");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        salesAnalytics = new ToolbarButton("Sales Analysis",new ImageIcon(getClass().getResource(IconConstants.SEARCH_ICON)));
        
        statusPanel.add(salesAnalytics);
        statusPanel.add(summaryPanel);
        
        filterSelectBox = new JComboBox();
        filterSelectBox.addItem(Constants.COMBOBOX_FILTER_BY_DATE);
        filterSelectBox.addItem(Constants.COMBOBOX_FILTER_BY_AMOUNT);
        
        filterPanel.setLayout(new GridLayout(1, 3)); 
        filterPanel.add(filterSelectBox);
        filterPanel.add(filterTextBefore);
        filterPanel.add(filterTextTo);
           
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }
        }.execute();

        // Sales Table
        salesTable = new JTable(tableModel);
        salesTable.getTableHeader().setResizingAllowed(false);
        salesTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        salesTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sorter =  new TableRowSorter(tableModel);
        salesTable.setRowSorter(sorter);
        //salesTable.setAutoCreateRowSorter(true);
        tableScrollPane.setViewportView(salesTable);
        

        summaryPanel.setLayout(new GridLayout(1, 2));
        summaryPanel.add(summaryLabel);
        summaryPanel.add(summaryText);
    }
    
    public void getSum()
    {
        float sum1 = 0;
        for(int i = 0; i < salesTable.getRowCount(); i++)
        { 
            try{
                sum1 = sum1 + Float.parseFloat(salesTable.getValueAt(i, 5).toString());
             }
            catch(Exception e){
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
                enableButtons();
            }
        });
        
         filterSelectBox.addItemListener(new ItemListener() {
            // Listening if a new items of the combo box has been selected.
            public void itemStateChanged(ItemEvent event) {
                //JComboBox comboBox = (JComboBox) event.getSource();

                // The item affected by the event.
                String item = (String)event.getItem();

             
                if (item == Constants.COMBOBOX_FILTER_BY_DATE ) {
                    filterTextBefore.setText(Constants.PLACEHOLDER_TEXT_DATE_FROM);
                    filterTextTo.setText(Constants.PLACEHOLDER_TEXT_DATE_TO);
                }

                if (item == Constants.COMBOBOX_FILTER_BY_AMOUNT) {
                    filterTextBefore.setText(Constants.PLACEHOLDER_TEXT_AMOUNT_FROM);
                    filterTextTo.setText(Constants.PLACEHOLDER_TEXT_AMOUNT_TO);
                }
            }
        });
         
        //on view button click
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create new form
                    int row = salesTable.getSelectedRow();
                    int sale_id = (Integer)tableModel.getValueAt(row, 0);
                    String customerName = (String)tableModel.getValueAt(row, 1);

                    manager.createSaleViewForm(sale_id, customerName); //the appropriate function call
                    manager.showSaleViewForm();
                } catch (Exception ex) {
                    Logger.getLogger(SalesPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
  
    // placeholder for the JTextField         
        filterTextBefore.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(filterSelectBox.getSelectedItem() == Constants.COMBOBOX_FILTER_BY_DATE){
                    if (filterTextBefore.getText().equals(Constants.PLACEHOLDER_TEXT_DATE_FROM)) {
                        filterTextBefore.setText("");
                        filterTextBefore.setForeground(Color.BLACK);
                    }
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(filterSelectBox.getSelectedItem() == Constants.COMBOBOX_FILTER_BY_DATE){
                    if (filterTextBefore.getText().isEmpty()) {
                        filterTextBefore.setForeground(Color.GRAY);
                        filterTextBefore.setText(Constants.PLACEHOLDER_TEXT_DATE_FROM);
                    }
                }
            }
        });

        filterTextTo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(filterSelectBox.getSelectedItem() == Constants.COMBOBOX_FILTER_BY_DATE){
                    if (filterTextTo.getText().equals(Constants.PLACEHOLDER_TEXT_DATE_TO)) {
                        filterTextTo.setText("");
                        filterTextTo.setForeground(Color.BLACK);
                    }
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                    if (filterTextTo.getText().isEmpty()) {
                        filterTextTo.setForeground(Color.GRAY);
                        filterTextTo.setText(Constants.PLACEHOLDER_TEXT_DATE_TO);
                    }
            }
        });        

        searchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if (searchBox.getText().equals(Constants.PLACEHOLDER_TEXT_SEARCH)) {
                searchBox.setText("");
                searchBox.setForeground(Color.BLACK);
            }
        }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchBox.getText().isEmpty()) {
                    searchBox.setForeground(Color.GRAY);
                    searchBox.setText(Constants.PLACEHOLDER_TEXT_SEARCH);
                }
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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" +str));
                }
            }
        });    
    }
    
    private void loadData() throws Exception {
       DataWithColumn dataWithColumn = salesBackend.setSaleInfoIntoTable(salesTable, tableModel);
       tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
       getSum();
    }

    protected void enableButtons() {
        removeButton.setEnabled(true);
        viewButton.setEnabled(true);
    }
  
    protected void enableRemoveButtons() {
        removeButton.setEnabled(true);
    }
    protected void enableViewButtons() {
        viewButton.setEnabled(true);
    }
        
    protected void disableRemoveButtons() {
        removeButton.setEnabled(false);
    }
    protected void disableViewButtons() {
        viewButton.setEnabled(false);
    }
    
    protected void disableButtons() {
        removeButton.setEnabled(false);
        viewButton.setEnabled(false);
    }
    
    protected JPanel getToolbar(){
        return toolbarPanel;
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
        filterPanel = new javax.swing.JPanel();
        summaryPanel = new javax.swing.JPanel();

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
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 731, Short.MAX_VALUE))
        );

        filterPanel.setPreferredSize(new java.awt.Dimension(450, 40));

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        summaryPanel.setPreferredSize(new java.awt.Dimension(450, 40));

        javax.swing.GroupLayout summaryPanelLayout = new javax.swing.GroupLayout(summaryPanel);
        summaryPanel.setLayout(summaryPanelLayout);
        summaryPanelLayout.setHorizontalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        summaryPanelLayout.setVerticalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolbarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                    .addComponent(summaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JPanel toolbarPanel;
    // End of variables declaration//GEN-END:variables

    protected class ToolbarButton extends JButton {

        ToolbarButton(String text, ImageIcon icon) {
            super(text,icon);
            init();
        }

        ToolbarButton(String text) {
            super(text);
            init();
        }

        void init() {
//          setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.yellow));
            setFont(DesignConstants.FONT_SIZE_16_CALIBRI_BOLD);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }
}
