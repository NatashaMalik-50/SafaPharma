/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Stock;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.MedicineLot.*;
import com.safapharma.Home.Supplier.SupplierBackend;
import com.safapharma.Main.MainWindow;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Medicine;
import com.safapharma.Templates.DialogForm;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.safapharma.ModelObjects.MedicineLot;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.JSType;
import com.safapharma.ModelObjects.StockEntry;
import com.safapharma.ModelObjects.Supplier;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Karan
 */
public class NewStockFormTwo extends DialogForm {

    private MainWindow manager;

    private FormLabel supplierNameLabel;
    private JComboBox supplierNameCombo;
    private ErrorLabel combinedErrorLabel;
    private FormLabel supplierNameAddButton;

    private FormLabel medicineNameLabel;
    private JComboBox medicineNameCombo;
    private ErrorLabel medicineNameErrorLabel;
    private FormLabel medicineNameAddButton;

    private FormLabel medicineLotBatchLabel;
    private JComboBox medicineLotBatchCombo;
    private FormLabel medicineLotBatchButton;
    DefaultComboBoxModel medicineBatchmodel;

    private FormLabel quantityLabel;
    private FormText quantityText;
    private ErrorLabel quantityErrorLabel;

    private FormLabel discountPercentageLabel;
    private FormText discountPercentageText;
    private ErrorLabel discountPercentageErrorLabel;

    private FormLabel gstLabel;
    private FormText gstText;
    private ErrorLabel gstErrorLabel;

    private FormLabel amountLabel;
    private FormText amountText;
    private ErrorLabel amountErrorLabel;

    private FormButton submitButton;
    private FormButton resetButton;

    private MedicineLotBackend medicineLotBackend;
    private StockBackend stockBackend;

    private Vector<MedicineLot> lotIdBatchList;
    private Vector<Supplier> supplierIdNameList;
    private Vector<Medicine> medicineIdNameList;
    private Vector<StockEntry> stockEntryList;

    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JPanel buttonPanel;

    private FormButton submitStock;
    private FormButton deleteStock;

    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private NewStockFormTwo thisForm;
    private Object[] columnNames = {"Medicine Name", "Medicine Batch", "Quantity", "DiscountPercentage", "GSTPercentage", "Amount"};

    public NewStockFormTwo(MainWindow manager, MedicineLotBackend medicineLotBackend) {

        this.setLocation(160, 10);
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        stockBackend = new StockBackend();
        thisForm = this;
        stockEntryList = new Vector<>();
        initUI();
        addListeners();
        resetText();
    }

    private void initUI() {

        getFormLabel().setText("Add Stock");
        getFormPanel().setLayout(new GridLayout(0, 2));

//        setBatchNameList();
        upperPanel = new JPanel(new GridLayout(0, 3));

        supplierNameLabel = new FormLabel("Supplier Name");
        fetchAndSetSupplierIdNameList();
        supplierNameAddButton = new FormLabel("or Add a new supplier?");
        supplierNameAddButton.setHorizontalAlignment(JLabel.LEFT);
        supplierNameAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        medicineNameLabel = new FormLabel("Medicine Name");
        fetchAndSetMediceIdNameList();
        medicineNameAddButton = new FormLabel("or Add a new medicine?");
        medicineNameAddButton.setHorizontalAlignment(JLabel.LEFT);
        medicineNameAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        medicineNameErrorLabel = new ErrorLabel();

        medicineLotBatchLabel = new FormLabel("Medicine Lot Batch");
        medicineLotBatchButton = new FormLabel("or Add a new batch?");
        medicineLotBatchButton.setHorizontalAlignment(JLabel.LEFT);
        medicineLotBatchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        medicineLotBatchCombo = new JComboBox();

        quantityLabel = new FormLabel("Quantity");
        quantityText = new FormText();
        quantityErrorLabel = new ErrorLabel();

        discountPercentageLabel = new FormLabel("DiscountPercentage");
        discountPercentageText = new FormText();
        discountPercentageErrorLabel = new ErrorLabel();

        gstLabel = new FormLabel("GSTPercentage");
        gstText = new FormText();
        gstErrorLabel = new ErrorLabel();

        amountLabel = new FormLabel("Amount");
        amountText = new FormText();
        amountErrorLabel = new ErrorLabel();

        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");
        combinedErrorLabel = new ErrorLabel();

        submitStock = new FormButton("Submit Lot");
        deleteStock = new FormButton("Delete Selected Lot");

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel(new GridLayout(0, 2));

        setComboBox(medicineNameCombo, supplierNameCombo, medicineLotBatchCombo);

        buttonPanel.add(submitStock);
        buttonPanel.add(deleteStock);

        upperPanel.add(supplierNameLabel);
        upperPanel.add(supplierNameCombo);
        upperPanel.add(supplierNameAddButton);

        upperPanel.add(medicineNameLabel);
        upperPanel.add(medicineNameCombo);
        upperPanel.add(medicineNameAddButton);
//        upperPanel .add(medicineNameErrorLabel);        

        upperPanel.add(medicineLotBatchLabel);
        upperPanel.add(medicineLotBatchCombo);
        upperPanel.add(medicineLotBatchButton);

        upperPanel.add(quantityLabel);
        upperPanel.add(quantityText);
        upperPanel.add(quantityErrorLabel);
//        upperPanel .add(new JPanel());

        upperPanel.add(discountPercentageLabel);
        upperPanel.add(discountPercentageText);
        upperPanel.add(discountPercentageErrorLabel);
//        upperPanel .add(new JPanel());

        upperPanel.add(gstLabel);
        upperPanel.add(gstText);
        upperPanel.add(gstErrorLabel);
//        upperPanel .add(new JPanel());

        upperPanel.add(amountLabel);
        upperPanel.add(amountText);
        upperPanel.add(amountErrorLabel);

        upperPanel.add(submitButton);
        upperPanel.add(resetButton);
        upperPanel.add(combinedErrorLabel);

//        getButtonPanel().add(submitButton);
//        getButtonPanel().add(resetButton,getGBC(1));
//        upperPanel .add(new JPanel());
//        upperPanel .add(new JPanel());
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(WIDTH, 200));
        tableModel = new DefaultTableModel();
        stockTable = new JTable(tableModel);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        getFormPanel().setLayout(new BoxLayout(getFormPanel(), BoxLayout.Y_AXIS));
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        stockTable.setAutoCreateRowSorter(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                thisForm.pack();
                return null;
            }
        }.execute();

        scrollPane.setViewportView(stockTable);

        lowerPanel.add(scrollPane);
        lowerPanel.add(buttonPanel);

        getFormPanel().add(upperPanel);
        getFormPanel().add(lowerPanel);

        this.pack();
        hideErrorLabels();
    }

    private void setComboBox(JComboBox mbox, JComboBox sbox, JComboBox medLotBatch) {
        try {
            mbox.setEditable(true);
            AutoCompleteDecorator.decorate(mbox);

            sbox.setEditable(true);
            AutoCompleteDecorator.decorate(sbox);

            medLotBatch.setEditable(true);
            AutoCompleteDecorator.decorate(medLotBatch);
        } catch (Exception e) {
            System.out.println("Exception at setComboBox");
        }
    }

    private void fetchAndSetSupplierIdNameList() {
        try {
            supplierIdNameList = medicineLotBackend.getAllSuppliers();
            System.out.println("All supplier details are" + supplierIdNameList);
            supplierNameCombo = new JComboBox();
            for (Supplier supplier : supplierIdNameList) {
                supplierNameCombo.addItem(supplier.getName());
            }
        } catch (Exception e) {
            System.out.println("Exception at set Supplier Name List");
        }
    }

    private void fetchAndSetMediceIdNameList() {
        try {
            medicineIdNameList = medicineLotBackend.getIdNameFromMedicine();   // To get a Vector of [id,name]
            System.out.println("Med details are" + medicineIdNameList);
            medicineNameCombo = new JComboBox();
            for (Medicine medicine : medicineIdNameList) {
                medicineNameCombo.addItem(medicine.getName());
            }
        } catch (Exception e) {
            System.out.println("Exception at set medicine name list");
        }
    }

    private void fetchAndSetBatchIdNameList(int id) {
        try {
            medicineBatchmodel = (DefaultComboBoxModel) medicineLotBatchCombo.getModel();
            medicineBatchmodel.removeAllElements();

            lotIdBatchList = medicineLotBackend.getMedicineLotDetailsId(id); // Get id and batch name list from medicine_lot from particular id            
            System.out.println("Lot details are" + lotIdBatchList);
            for (MedicineLot ml : lotIdBatchList) {
                medicineBatchmodel.addElement(ml.getBatchNo()); //adding elements to the model
            }
            medicineLotBatchCombo.setModel(medicineBatchmodel);
        } catch (Exception e) {
            System.out.println("Exception at Batch Name List");
            e.printStackTrace();
        }
    }

    private void addRow() {
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        Vector<Object> newRow = new Vector<>();
        StockEntry stockEntry = new StockEntry();

        int qty = Integer.parseInt(quantityText.getText());
        double discountPercentage = Double.parseDouble(discountPercentageText.getText());
        double gst = Double.parseDouble(gstText.getText());
        double amount = Double.parseDouble(amountText.getText());
        int selectedMedicineIndex = medicineNameCombo.getSelectedIndex();
        int selectBatchIndex = medicineLotBatchCombo.getSelectedIndex();

        newRow.add(medicineIdNameList.get(selectedMedicineIndex).getName());
        newRow.add(lotIdBatchList.get(selectBatchIndex).getBatchNo());
        stockEntry.setMedicineLotId(lotIdBatchList.get(selectBatchIndex).getId());
        newRow.add(qty);
        stockEntry.setQuantity(qty);
        newRow.add(discountPercentage);
        stockEntry.setDiscountPercentage(discountPercentage);
        newRow.add(gst);
        stockEntry.setGstPercentage(gst);
        newRow.add(amount);
        stockEntry.setAmount(amount);

        model.addRow(newRow);
        stockEntryList.add(stockEntry);
    }

    private void loadData() throws Exception {
//        DataWithColumn dataWithColumn = customerBackend.setCustomerInfoIntoTable(customerTable, tableModel);
//        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        tableModel.setColumnIdentifiers(columnNames);
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInfo()) {
                    addRow();
                    supplierNameCombo.setEditable(false);
                    supplierNameCombo.setEnabled(false);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetText();
            }
        });
        // Add Supplier Name
        supplierNameAddButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Add Supplier called");
                try {
                    manager.createNewOrUpdateSupplierForm(new SupplierBackend(),false,null,true);
                    manager.showNewOrUpdateSupplierForm();
                    fetchAndSetSupplierIdNameList();
                } catch (Exception ex) {
                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //Medicine Name listener
        medicineNameAddButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Add Medicine called");
            }
        });

        // Add Medicine lot batch listener
        medicineLotBatchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Add Medicine Lot Batch Called");
                try {
                    manager.createNewMedicineLotForm(new MedicineLotBackend(), null);
                    manager.showNewMedicineLotForm();
                    fetchAndSetBatchIdNameList(medicineIdNameList.get(medicineNameCombo.getSelectedIndex()).getId());
                } catch (Exception ex) {
                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

//        supplierNameAddButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Add Supplier called");
//                try {
//                    manager.createNewOrUpdateSupplierForm(new SupplierBackend());
//                    manager.showNewOrUpdateSupplierForm();
//                } catch (Exception ex) {
//                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                  
//                
//            }
//        });
//        medicineNameAddButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Add Medicine called");
//            }
//        });
//        medicineLotBatchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Add Medicine Lot Batch Called");
//                try {
//                    manager.createNewMedicineLotForm(new MedicineLotBackend(), null);
//                    manager.showNewMedicineLotForm();
//                } catch (Exception ex) {
//                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//        });
//        
        submitStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit Stock button clicked");

                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                System.out.println("All data in add stock table");

                Vector<Vector> submitLotData = model.getDataVector();
                stockBackend.insertStockDetails(submitLotData);
                
                supplierNameCombo.setEditable(true);
                supplierNameCombo.setEnabled(true);
                model.setRowCount(0);
                
                JOptionPane.showMessageDialog(thisForm,"The medicine lot has been submitted successfully.");  
            }
        });

        deleteStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Selected Stock button clicked");
                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                int numRows = stockTable.getSelectedRows().length;
                for (int i = 0; i < numRows; i++) {
                    model.removeRow(stockTable.getSelectedRow());
                }
            }
        });

        medicineNameCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Item state changed, selected");
                    if (medicineIdNameList != null && medicineNameCombo.getSelectedIndex() >= 0) {
                        fetchAndSetBatchIdNameList(medicineIdNameList.get(medicineNameCombo.getSelectedIndex()).getId());
                    }
                }
            }
        });
    }

    private boolean isEmpty(String str) {
        boolean checker = false;
        if (str.equals("") || str.equals(null)) {
            checker = true;
        }

        return checker;
    }

    private boolean validateInfo() {
        resetErrorLabels();
        boolean isValid = true;
        String errorMsg = "Please select ";
        try {

            /*Checking if the respective textfields are empty or not. */
            if (supplierNameCombo.getSelectedItem().toString().isEmpty()) {
                isValid = false;
                errorMsg = errorMsg + "supplier name,";
                combinedErrorLabel.setText(errorMsg);
                combinedErrorLabel.setVisible(true);
            }

            if (medicineNameCombo.getSelectedItem().toString().isEmpty()) {
                isValid = false;
                errorMsg = errorMsg + "medicine name,";
                combinedErrorLabel.setText(errorMsg);
                combinedErrorLabel.setVisible(true);
            }

            if (medicineLotBatchCombo.getSelectedItem().toString().isEmpty()) {
                isValid = false;
                errorMsg = errorMsg + "medicine lot batch";
                combinedErrorLabel.setText(errorMsg);
                combinedErrorLabel.setVisible(true);
            }

            if (quantityText.getText().isEmpty()) {
                isValid = false;
                quantityErrorLabel.setText("Quantity cannot be empty");
                quantityErrorLabel.setVisible(true);
            }

            if (discountPercentageText.getText().isEmpty()) {
                isValid = false;
                discountPercentageErrorLabel.setText("Discount cannot be empty");
                discountPercentageErrorLabel.setVisible(true);
            }
            if (gstText.getText().isEmpty()) {
                isValid = false;
                gstErrorLabel.setText("GST cannot be empty");
                gstErrorLabel.setVisible(true);
            }
            if (amountText.getText().isEmpty()) {
                isValid = false;
                amountErrorLabel.setText("Amount cannot be empty");
                amountErrorLabel.setVisible(true);
            }

            if (isValid) {
                String qtyString = this.quantityText.getText();
                String discountPercentageString = this.discountPercentageText.getText();
                String gstString = this.gstText.getText();
                String amountString = this.amountText.getText();

                int qty = 0;
                double discountPercentage = 0.0;
                double gst = 0.0;
                double amount = 0.0;

                if (isInteger(qtyString)) {
                    qty = Integer.parseInt(qtyString);
                } else {
                    this.quantityErrorLabel.setText("Quantity should be a valid number.");
                    quantityErrorLabel.setVisible(true);
                    isValid = false;
                }

                if (isDouble(discountPercentageString)) {
                    discountPercentage = Double.parseDouble(discountPercentageString);
                } else {
                    this.discountPercentageErrorLabel.setText("Discount Percentage should be a valid number.");
                    discountPercentageErrorLabel.setVisible(true);
                    isValid = false;
                }

                if (isDouble(gstString)) {
                    gst = Double.parseDouble(gstString);
                } else {
                    this.gstErrorLabel.setText("GST Percentage should be a valid number.");
                    gstErrorLabel.setVisible(true);
                    isValid = false;
                }

                if (isDouble(amountString)) {
                    amount = Double.parseDouble(amountString);
                } else {
                    this.amountErrorLabel.setText("Amount should be a valid number.");
                    amountErrorLabel.setVisible(true);
                    isValid = false;
                }

                if (isValid) {
                    if (qty < 0) {
                        this.quantityErrorLabel.setText("Quantity cannot be less than 0");
                        quantityErrorLabel.setVisible(true);
                        isValid = false;
                    }

                    if (discountPercentage < 0.0) {
                        this.discountPercentageErrorLabel.setText("Discount Percentage cannot be less than 0");
                        discountPercentageErrorLabel.setVisible(true);
                        isValid = false;

                    }
                    if (gst < 0.0) {
                        this.gstErrorLabel.setText("Discount Percentage cannot be less than 0");
                        gstErrorLabel.setVisible(true);
                        isValid = false;

                    }
                    if (amount < 0.0) {
                        this.amountErrorLabel.setText("Discount Percentage cannot be less than 0");
                        amountErrorLabel.setVisible(true);
                        isValid = false;
                    }
                }
            }

            if (isValid) {
                resetErrorLabels();
            } else {
//                 showErrorLabels();
            }

            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
            showErrorLabels();
            return false;
        }
    }

    boolean isDouble(String str) {
        try {
            Float.parseFloat(str);
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void hideErrorLabels() {
        medicineNameErrorLabel.setVisible(false);
        quantityErrorLabel.setVisible(false);
        discountPercentageErrorLabel.setVisible(false);
        gstErrorLabel.setVisible(false);
        amountErrorLabel.setVisible(false);
        combinedErrorLabel.setVisible(false);
    }

    private void showErrorLabels() {
        medicineNameErrorLabel.setVisible(true);
        quantityErrorLabel.setVisible(true);
        discountPercentageErrorLabel.setVisible(true);
        gstErrorLabel.setVisible(true);
        amountErrorLabel.setVisible(true);
        combinedErrorLabel.setVisible(true);
    }

    private void resetErrorLabels() {
        medicineNameErrorLabel.setErrorText("");
        quantityErrorLabel.setErrorText("");
        discountPercentageErrorLabel.setErrorText("");
        gstErrorLabel.setErrorText("");
        amountErrorLabel.setErrorText("");
        combinedErrorLabel.setErrorText("");
        hideErrorLabels();
    }

    private void resetText() {
        if (supplierNameCombo.isEditable() == true) {
            supplierNameCombo.getEditor().setItem("");
        }
        medicineNameCombo.getEditor().setItem("");
        quantityText.setText("");
        discountPercentageText.setText("");
        gstText.setText("");
        medicineLotBatchCombo.getEditor().setItem("");
        amountText.setText("");

        hideErrorLabels();
    }
    
    public void receiveSupplier(Supplier supplier){
        supplierIdNameList.add(supplier);
        supplierNameCombo.addItem(supplier.getName());
        supplierNameCombo.setSelectedIndex(supplierIdNameList.size()-1);
        System.out.println("Supplier selected : "+supplier);
    }
    public void receiveMedicine(Medicine medicine){
        medicineIdNameList.add(medicine);
        medicineNameCombo.addItem(medicine.getName());
        medicineNameCombo.setSelectedIndex(medicineIdNameList.size()-1);
        System.out.println("Medicine selected : "+medicine);
    }
    public void receiveMedicineLot(MedicineLot medicineLot){
        lotIdBatchList.add(medicineLot);
        medicineLotBatchCombo.addItem(medicineLot.getBatchNo());
        medicineLotBatchCombo.setSelectedIndex(lotIdBatchList.size()-1);
        System.out.println("Batch selected : "+medicineLot);
    }
    @Override
    protected void deleteScreen() {
        manager.deleteNewMedicineLotForm();
    }

}
