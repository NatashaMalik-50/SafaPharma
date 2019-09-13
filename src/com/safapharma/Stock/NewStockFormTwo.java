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
    
    private FormLabel batchNumLabel;
    private FormText batchNumText;
    private ErrorLabel batchNumErrorLabel;
    
    private FormLabel expiryLabel;
    private FormText expiryText;
    private ErrorLabel expiryErrorLabel;
    
    private FormLabel rateLabel;
    private FormText rateText;
    private ErrorLabel rateErrorLabel;
    
    private FormButton submitButton;
    private FormButton resetButton;
    
    private MedicineLotBackend medicineLotBackend;

    private Vector<Vector> fullList;
    private Vector fullNameList;
    
    private Vector fullSupplierNameList;
    
    private JPanel upperPanel;
    private JPanel lowerPanel;
    
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private NewStockFormTwo thisForm;
    private Object[] columnNames = {"Medicine Name","Batch No","Expiry", "Rate"};

    public NewStockFormTwo(MainWindow manager, MedicineLotBackend medicineLotBackend) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;        
        thisForm = this;
        initUI();        
        addListeners();  
     
    }
    
    private void setComboBox(JComboBox mbox,JComboBox sbox){
        try{
            mbox.setEditable(true);    
            AutoCompleteDecorator.decorate(mbox);       
            
            sbox.setEditable(true);
            AutoCompleteDecorator.decorate(sbox);       
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
    
    private void addRow(){
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        Vector<Object> newRow = new Vector<>();
        
        newRow.add(medicineNameCombo.getSelectedItem().toString());
        newRow.add(batchNumText.getText());
        newRow.add(expiryText.getText());
        newRow.add(rateText.getText());
        model.addRow(newRow);
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
    
    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("Add MedicineLotForm");
        getFormPanel().setLayout(new GridLayout(0,2));
        System.out.println("Creating and Adding Medicine Lot Form ");
        
//        String[] someString = {"", "Acetaminophen", "Adderall", "Amitriptyline", "Amlodipine","Karan","Manan","Raghav","Parman" };
        setMediceNameList();
        setSupplierNameList();
        
        upperPanel = new JPanel(new GridLayout(0,3));
        
        supplierNameLabel = new FormLabel("Supplier Name");
        supplierNameCombo = new JComboBox(fullSupplierNameList);
        supplierNameAddButton = new FormButton("Add");
        
        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameCombo = new JComboBox(fullNameList);
        medicineNameAddButton = new FormButton("Add");
        medicineNameErrorLabel = new ErrorLabel();
        
        setComboBox(medicineNameCombo,supplierNameCombo);

        batchNumLabel = new FormLabel("Batch Number");
        batchNumText = new FormText();
        batchNumErrorLabel = new ErrorLabel();

        expiryLabel = new FormLabel("Expiry");
        expiryText = new FormText();
        expiryErrorLabel = new ErrorLabel();

        rateLabel = new FormLabel("Rate");
        rateText = new FormText();
        rateErrorLabel = new ErrorLabel();
        
        submitButton = new FormButton("Submit");
        resetButton = new FormButton("Reset");
        
        upperPanel.add(supplierNameLabel);
        upperPanel.add(supplierNameCombo);
        upperPanel.add(supplierNameAddButton);
        
        upperPanel.add(medicineNameLabel);
        upperPanel.add(medicineNameCombo);
        upperPanel.add(medicineNameAddButton);
//        upperPanel .add(medicineNameErrorLabel);        
        
        upperPanel.add(batchNumLabel);
        upperPanel.add(batchNumText);
        upperPanel.add(batchNumErrorLabel);
//        upperPanel .add(new JPanel());
        
        upperPanel.add(expiryLabel);
        upperPanel.add(expiryText);
        upperPanel.add(expiryErrorLabel);
//        upperPanel .add(new JPanel());
        
        upperPanel.add(rateLabel);
        upperPanel.add(rateText);
        upperPanel.add(rateErrorLabel);
//        upperPanel .add(new JPanel());

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
            if(batchNumText.getText().isEmpty()){
                isValid = false;
                batchNumErrorLabel.setText("Incorrect batch number");
            }
                
            if(expiryText.getText().isEmpty()){
                isValid = false;
                expiryErrorLabel.setText("Incorrect Expiry ");
            }
            if(rateText.getText().isEmpty()){
                isValid = false;
                rateErrorLabel.setText("Incorrect Rate");
            }
            if(isValid){
                double num = Double.parseDouble(rateText.getText());
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
        batchNumErrorLabel.setVisible(false);
        expiryErrorLabel.setVisible(false);
        rateErrorLabel.setVisible(false);
    }
    
    private void showErrorLabels() {
        medicineNameErrorLabel.setVisible(true);
        batchNumErrorLabel.setVisible(true);
        expiryErrorLabel.setVisible(true);
        rateErrorLabel.setVisible(true);
    }

    private void resetErrorLabels() {
        medicineNameErrorLabel.setErrorText("");
        batchNumErrorLabel.setErrorText("");
        expiryErrorLabel.setErrorText("");
        rateErrorLabel.setErrorText("");
        hideErrorLabels();
    }
    
    private void resetText() {
        supplierNameCombo.getEditor().setItem("");
        medicineNameCombo.getEditor().setItem("");
        batchNumText.setText("");
        expiryText.setText("");
        rateText.setText("");
        hideErrorLabels();
    }


    @Override
    protected void deleteScreen() {
        manager.deleteNewMedicineLotForm();
    }

    
}
