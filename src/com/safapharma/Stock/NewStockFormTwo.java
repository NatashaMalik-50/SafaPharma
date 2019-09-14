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
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author Karan
 */
public class NewStockFormTwo extends DialogForm {
    
    private MainWindow manager;
    
    private FormLabel supplierNameLabel;
    private JComboBox supplierNameCombo;
    private ErrorLabel combinedErrorLabel;
    private FormButton supplierNameAddButton;
    
        
    private FormLabel medicineNameLabel;
    private JComboBox medicineNameCombo;
    private ErrorLabel medicineNameErrorLabel;
    private FormButton medicineNameAddButton;
    
    private FormLabel medicineLotBatchLabel;
    private JComboBox medicineLotBatchCombo;
    private FormButton medicineLotBatchButton;
    
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

    private Vector<Vector> fullList;
    private Vector fullNameList;    
    private Vector fullSupplierNameList;
    private Vector fullBatchNameList;
    
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JPanel buttonPanel;            
    
    private FormButton submitStock;
    private FormButton deleteStock;
    
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private NewStockFormTwo thisForm;
    private Object[] columnNames = {"Medicine Name","Medicine Batch","Quantity","DiscountPercentage", "GSTPercentage","Amount"};

    public NewStockFormTwo(MainWindow manager, MedicineLotBackend medicineLotBackend) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;        
        thisForm = this;
        initUI();        
        addListeners();  
     
    }
    
    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("Add Stock");
        getFormPanel().setLayout(new GridLayout(0,2));
        System.out.println("Creating and Adding Medicine Lot Form ");
        
//        String[] someString = {"", "Acetaminophen", "Adderall", "Amitriptyline", "Amlodipine","Karan","Manan","Raghav","Parman" };
        setMediceNameList();
        setSupplierNameList();
        setBatchNameList();
        
        upperPanel = new JPanel(new GridLayout(0,3));
        
        supplierNameLabel = new FormLabel("Supplier Name");
        supplierNameCombo = new JComboBox(fullSupplierNameList);
        supplierNameAddButton = new FormButton("Add Supplier Name");
        
        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameCombo = new JComboBox(fullNameList);
        medicineNameAddButton = new FormButton("Add Medicine Name");
        medicineNameErrorLabel = new ErrorLabel();
        
        medicineLotBatchLabel = new FormLabel("Medicine Lot Batch");
        medicineLotBatchCombo = new JComboBox(fullBatchNameList);
        medicineLotBatchButton = new FormButton("Add Medicine Lot Batch");
        

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
        lowerPanel.setLayout(new BoxLayout(lowerPanel,BoxLayout.Y_AXIS));
        buttonPanel = new JPanel(new GridLayout(0,2));
        
        setComboBox(medicineNameCombo,supplierNameCombo,medicineLotBatchCombo);
        
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

        scrollPane= new JScrollPane();
        tableModel = new DefaultTableModel();
        stockTable = new JTable(tableModel);
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        getFormPanel().setLayout(new BoxLayout(getFormPanel(),BoxLayout.Y_AXIS));
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
    
    private void setComboBox(JComboBox mbox,JComboBox sbox,JComboBox medLotBatch){
        try{
            mbox.setEditable(true);    
            AutoCompleteDecorator.decorate(mbox);       
            
            sbox.setEditable(true);
            AutoCompleteDecorator.decorate(sbox);       
            
            medLotBatch.setEditable(true);
            AutoCompleteDecorator.decorate(medLotBatch);       
        }
        catch(Exception e){
            System.out.println("Exception at setComboBox");
        }
    }
    
    private void setSupplierNameList(){
        try{   
            DataWithColumn details =  medicineLotBackend.getAllSupplierFromBillDetails();
            Vector<Vector<Object>> allDetails = details.getData();
            System.out.println("All supplier details are"+allDetails);
            Vector supplierName = new Vector();
            for(Vector v : allDetails){
    //            System.out.println(v.get(2).toString());
                supplierName.add(v.get(1));
            }
            fullSupplierNameList=supplierName;
        }
        catch(Exception e){
            System.out.println("Exception at setFullNameList");
        }
    }
    
    private void setMediceNameList(){
        try{   
            DataWithColumn details =  medicineLotBackend.getAllMedicineLotDetails();
            Vector<Vector<Object>> allDetails = details.getData();
//            System.out.println("All details are"+allDetails);
            Vector medName = new Vector();
            for(Vector v : allDetails){
    //            System.out.println(v.get(2).toString());
                medName.add(v.get(2));
            }
            fullNameList=medName;
        }
        catch(Exception e){
            System.out.println("Exception at setFullNameList");
        }
    }
    
    private void setBatchNameList(){
        try{   
            DataWithColumn details =  medicineLotBackend.getAllMedicineLotDetails();
            Vector<Vector<Object>> allDetails = details.getData();
//            System.out.println("All details are"+allDetails);
            Vector medName = new Vector();
            for(Vector v : allDetails){
    //            System.out.println(v.get(2).toString());
                medName.add(v.get(4));
            }
            fullBatchNameList=medName;
        }
        catch(Exception e){
            System.out.println("Exception at setFullNameList");
        }
    }
    
    private void addRow(){
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        Vector<Object> newRow = new Vector<>();
        
        String qtyString = this.quantityText.getText();
        String discountPercentageString = this.discountPercentageText.getText();
        String gstString = this.gstText.getText();
        String amountString = this.amountText.getText();
        
        int qty = Integer.parseInt(qtyString);
        double discountPercentage = Double.parseDouble(discountPercentageString);
        double gst = Double.parseDouble(gstString);
        double amount = Double.parseDouble(amountString);                    
    
        newRow.add(medicineNameCombo.getSelectedItem().toString());
        newRow.add(medicineLotBatchCombo.getSelectedItem().toString());
        newRow.add(qty);
        newRow.add(discountPercentage);
        newRow.add(gst);
        newRow.add(amount);
        
        model.addRow(newRow);
    }
 
    private int getIdOfMedicine(String medName){
        for(Vector v : fullList){
            //fullList does not contain medicineId , need to get a view which contains medicine Id as well then check for that value.
            // if v.getItem(2).toString().equals(medName)
            //     int a = Integer.apseInt( v.getItem(1));
//            supplierNameCombo.getSelectedIndex()
//              Vector.getID... apne aap
        }
        return -1;
    }
    
    private void loadData() throws Exception {
//        DataWithColumn dataWithColumn = customerBackend.setCustomerInfoIntoTable(customerTable, tableModel);
//        tableModel.setDataVector(dataWithColumn.getData(), dataWithColumn.getColumnNames());
        tableModel.setColumnIdentifiers(columnNames);
    }

    private void addListeners() {
        System.out.println("addListereners initiated");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {              
                if(validateInfo()){
                    addRow();             
                    
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetText();
            }
        });
        medicineNameAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Medicine called");
            }
        });
        
        supplierNameAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Supplier called");
                try {
                    manager.createNewOrUpdateSupplierForm(new SupplierBackend());
                    manager.showNewOrUpdateSupplierForm();
                } catch (Exception ex) {
                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        medicineLotBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Medicine Lot Batch Called");
                try {
                    manager.createNewMedicineLotForm(new MedicineLotBackend(), null);
                    manager.showNewMedicineLotForm();
                } catch (Exception ex) {
                    Logger.getLogger(NewStockFormTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        submitStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {              
                System.out.println("Submit Stock button clicked");
                
                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();                                
                System.out.println("All data in add stock table");
                
                Vector<Vector>  submitLotData = model.getDataVector();
                for(Vector object : submitLotData){
                    System.out.println(object);
                }
            }
        });
        
        deleteStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {              
                System.out.println("Delete Selected Stock button clicked");
                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                int numRows = stockTable.getSelectedRows().length;                
                for(int i=0; i<numRows; i++) {
                    model.removeRow(stockTable.getSelectedRow());
                }
            }
        });
    }
    
    private boolean isEmpty(String str){
        boolean checker = false;
        if(str.equals("") || str.equals(null))
            checker = true;
        
        return checker;    
    }
    
    
    private boolean validateInfo() {
        resetErrorLabels();
        boolean isValid = true;
        String errorMsg = "Please select ";
        try{
        
            /*Checking if the respective textfields are empty or not. */
            if(supplierNameCombo.getSelectedItem().toString().isEmpty()){
                isValid = false;
                errorMsg = errorMsg + "supplier name,";
                combinedErrorLabel.setText(errorMsg);
            }
            
            if(medicineNameCombo.getSelectedItem().toString().isEmpty()){
                isValid = false;
                errorMsg = errorMsg + "medicine name,";
                combinedErrorLabel.setText(errorMsg);
            }
            
            if(medicineLotBatchCombo.getSelectedItem().toString().isEmpty()){
                isValid = false;
                errorMsg = errorMsg + "medicine lot batch";
                combinedErrorLabel.setText(errorMsg);
            }
            
            if(quantityText.getText().isEmpty()){
                isValid = false;
                quantityErrorLabel.setText("Quantity cannot be empty");
            }
                
            if(discountPercentageText.getText().isEmpty()){
                isValid = false;
                discountPercentageErrorLabel.setText("Discount cannot be empty");
            }
            if(gstText.getText().isEmpty()){
                isValid = false;
                gstErrorLabel.setText("GST cannot be empty");
            }
            if(amountText.getText().isEmpty()){
                isValid = false;
                amountErrorLabel.setText("Amount cannot be empty");
            }
            
            
            if(isValid){
                String qtyString = this.quantityText.getText();
                String discountPercentageString = this.discountPercentageText.getText();
                String gstString = this.gstText.getText();
                String amountString = this.amountText.getText();
                
                int qty=0;
                double discountPercentage = 0.0;
                double gst = 0.0 ;
                double amount = 0.0;
                
                if(isInteger(qtyString)){
                    qty = Integer.parseInt(qtyString);
                }
                else{
                    this.quantityErrorLabel.setText("Quantity should be a valid number.");
                    isValid = false;
                }
                
                if(isDouble(discountPercentageString)){
                    discountPercentage = Double.parseDouble(discountPercentageString);
                }
                else{
                    this.discountPercentageErrorLabel.setText("Discount Percentage should be a valid number.");
                    isValid = false;
                }

                if(isDouble(gstString)){
                    gst = Double.parseDouble(gstString);
                }
                else{
                    this.gstErrorLabel.setText("GST Percentage should be a valid number.");
                    isValid = false;
                }
                
                if(isDouble(amountString)){
                    amount = Double.parseDouble(amountString);                    
                }
                else{
                    this.amountErrorLabel.setText("Amount should be a valid number.");
                    isValid = false;
                }
                
                if(isValid){
                    if(qty<0)
                    {
                        this.quantityErrorLabel.setText("Quantity cannot be less than 0");
                        isValid = false;
                    }
                    
                    if(discountPercentage<0.0){
                        this.discountPercentageErrorLabel.setText("Discount Percentage cannot be less than 0");
                        isValid = false;
                                
                    }
                    if(gst<0.0){
                        this.gstErrorLabel.setText("Discount Percentage cannot be less than 0");
                        isValid = false;
                                
                    }
                    if(amount<0.0){
                        this.amountErrorLabel.setText("Discount Percentage cannot be less than 0");
                        isValid = false;                                
                    }
                }                
            }               
            
            if(isValid){
               resetErrorLabels();
            }
            else{
                 showErrorLabels();
            }
            
            return isValid;
        }
        catch(Exception e){
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
        supplierNameCombo.getEditor().setItem("");
        medicineNameCombo.getEditor().setItem("");
        quantityText.setText("");
        discountPercentageText.setText("");
        gstText.setText("");
        medicineLotBatchCombo.getEditor().setItem("");
        
        hideErrorLabels();
    }


    @Override
    protected void deleteScreen() {
        manager.deleteNewMedicineLotForm();
    }

    
}
