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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karan
 */
public class NewStockFormTwo extends DialogForm {
    
    private MainWindow manager;
    
    private FormLabel supplierNameLabel;
    private JComboBox supplierNameCombo;
//    private ErrorLabel supplierNameErrorLabel;
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
        supplierNameAddButton = new FormButton("Add");
        
        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameCombo = new JComboBox(fullNameList);
        medicineNameAddButton = new FormButton("Add");
        medicineNameErrorLabel = new ErrorLabel();
        
        medicineLotBatchLabel = new FormLabel("Medicine Lot Batch");
        medicineLotBatchCombo = new JComboBox(fullBatchNameList);
        medicineLotBatchButton = new FormButton("Add");
        

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
        
        setComboBox(medicineNameCombo,supplierNameCombo,medicineLotBatchCombo);
        
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
        

        getFormPanel().add(upperPanel);
        
        
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
        getFormPanel().add(scrollPane);

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
        
        newRow.add(medicineNameCombo.getSelectedItem().toString());
        newRow.add(medicineLotBatchCombo.getSelectedItem().toString());
        newRow.add(quantityText.getText());
        newRow.add(discountPercentageText.getText());
        newRow.add(gstText.getText());
        newRow.add(amountText.getText());
        
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
                addRow();             
                // Karan here
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
        try{
        
            /*Checking if the respective textfields are empty or not. */
            if(medicineNameCombo.getSelectedItem().toString().isEmpty()){
                isValid = false;
                medicineNameErrorLabel.setText("Incorrect Medicine Name");
            }
            if(quantityText.getText().isEmpty()){
                isValid = false;
                quantityErrorLabel.setText("Incorrect batch number");
            }
                
            if(discountPercentageText.getText().isEmpty()){
                isValid = false;
                discountPercentageErrorLabel.setText("Incorrect Expiry ");
            }
            if(gstText.getText().isEmpty()){
                isValid = false;
                gstErrorLabel.setText("Incorrect Rate");
            }
            if(isValid){
                double num = Double.parseDouble(gstText.getText());
                if(num<=0){
                    isValid = false;
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

//    private Supplier generateSupplier() {
//        Supplier supplier = new Supplier(nameText.getText(), addressText.getText(), contactText.getText(), emailText.getText());
//        return supplier;
//    }

    private void hideErrorLabels() {
        medicineNameErrorLabel.setVisible(false);
        quantityErrorLabel.setVisible(false);
        discountPercentageErrorLabel.setVisible(false);
        gstErrorLabel.setVisible(false);
        amountErrorLabel.setVisible(false);
    }
    
    private void showErrorLabels() {
        medicineNameErrorLabel.setVisible(true);
        quantityErrorLabel.setVisible(true);
        discountPercentageErrorLabel.setVisible(true);
        gstErrorLabel.setVisible(true);
        amountErrorLabel.setVisible(false);
    }

    private void resetErrorLabels() {
        medicineNameErrorLabel.setErrorText("");
        quantityErrorLabel.setErrorText("");
        discountPercentageErrorLabel.setErrorText("");
        gstErrorLabel.setErrorText("");
        amountErrorLabel.setErrorText("");
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
