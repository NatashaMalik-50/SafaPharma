/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.CustomDefaultTableModel;
import com.safapharma.Templates.DummyPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Natasha Malik
 */
public class NewStockViewForm extends DialogForm {

    private HomeScreenPanel homeScreenPanel;
    private final MainWindow manager;
    private JTable stockTable;
    private FormButton addButton;
    private FormButton resetButton;
    private DefaultTableModel tableModel;
    private JPanel searchPanel;
    private FormText searchBox;
    private JScrollPane scrollPane;
    private JPanel toolbarPanel;
    private TableRowSorter sorter;
    private final DataWithColumn stockDataWithColumn;

    public NewStockViewForm(MainWindow manager, HomeScreenPanel homeScreenPanel) {
        this.manager = manager;
        this.homeScreenPanel = homeScreenPanel;
        this.stockDataWithColumn = (this.homeScreenPanel != null) ? this.homeScreenPanel.getStockData() : null;
        if (stockDataWithColumn == null) {
            JOptionPane optionPane = new JOptionPane("Stock cannot be viewed at the moment.");
            JDialog dialog = optionPane.createDialog("Stock Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            manager.deleteNewStockViewForm();
            dispose();
        }
        initUI();
        addListeners();
    }

    private void initUI() {
        getFormLabel().setText("View Stock");
        addButton = new FormButton("Add Medicine");
        resetButton = new FormButton("Reset Selection");
        disableButtons();

        tableModel = new CustomDefaultTableModel();
        tableModel.setDataVector(stockDataWithColumn.getData(), stockDataWithColumn.getColumnNames());
        stockTable = new JTable(tableModel);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        stockTable.setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        stockTable.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter(tableModel);
        stockTable.setRowSorter(sorter);
        
        
        searchBox = new FormText();
        toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new GridLayout(2,0));
        toolbarPanel.add(new FormLabel("Enter search text"));
        toolbarPanel.add(new DummyPanel());
        toolbarPanel.add(new DummyPanel());
        toolbarPanel.add(searchBox);
        toolbarPanel.add(addButton);
        toolbarPanel.add(resetButton);
        
        getFormPanel().setLayout(new BoxLayout(getFormPanel(), BoxLayout.Y_AXIS));
        getFormPanel().add(toolbarPanel);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(stockTable);
        getFormPanel().add(scrollPane);
        this.pack();
    }

    private void addListeners() {
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addButton.setEnabled(true);
                resetButton.setEnabled(true);
            }

        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int rowIndex = stockTable.getSelectedRow();
                String exactSrno = stockTable.getValueAt(rowIndex, 0).toString();
                int srno = Integer.parseInt(exactSrno);
                //System.out.println(sr);
                homeScreenPanel.addRow(srno);
                }
                catch(Exception em)
                {
                    System.out.println(em);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockTable.clearSelection();
                disableButtons();
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

    @Override
    protected void deleteScreen() {
        manager.deleteNewStockViewForm();
    }
    
    private void disableButtons(){
        addButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

}
