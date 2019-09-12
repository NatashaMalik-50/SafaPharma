/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;


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

/**
 *
 * @author Karan
 */
public class NewMedicineLotForm extends DialogForm {
    
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
    
    

    public NewMedicineLotForm(MainWindow manager, MedicineLotBackend medicineLotBackend,Vector fullList) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        this.fullList = fullList;
        
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
    
    
    private void setMediceNameList(){
//        Vector medName = new Vector();
//        for(Vector v : fullList){
////            System.out.println(v.get(2).toString());
//            medName.add(v.get(2));
//        }
//        fullNameList=medName;
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
        System.out.println("Creating and Adding Medicine Lot Form ");
        
//        String[] someString = {"", "Acetaminophen", "Adderall", "Amitriptyline", "Amlodipine","Karan","Manan","Raghav","Parman" };
        setMediceNameList();
        setSupplierNameList();
        
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
        
        getFormPanel().add(supplierNameLabel);
        getFormPanel().add(supplierNameCombo);
        getFormPanel().add(supplierNameAddButton);
        
        getFormPanel().add(medicineNameLabel);
        getFormPanel().add(medicineNameCombo);
        getFormPanel().add(medicineNameAddButton);
//        getFormPanel().add(medicineNameErrorLabel);        
        
        getFormPanel().add(batchNumLabel);
        getFormPanel().add(batchNumText);
        getFormPanel().add(batchNumErrorLabel);
//        getFormPanel().add(new JPanel());
        
        getFormPanel().add(expiryLabel);
        getFormPanel().add(expiryText);
        getFormPanel().add(expiryErrorLabel);
//        getFormPanel().add(new JPanel());
        
        getFormPanel().add(rateLabel);
        getFormPanel().add(rateText);
        getFormPanel().add(rateErrorLabel);
//        getFormPanel().add(new JPanel());
        
        getButtonPanel().add(submitButton);
        getButtonPanel().add(resetButton,getGBC(1));
//        getFormPanel().add(new JPanel());
//        getFormPanel().add(new JPanel());

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

    private void addListeners() {
        System.out.println("addListereners initiated");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit Button called");
                boolean isValid = validateInfo();
                if (isValid) {
                    try {
                        // Add the content provided in the form.
                        MedicineLot ml = new MedicineLot();
                        String medName = (String)medicineNameCombo.getSelectedItem();
                        int medId = getIdOfMedicine(medName);
                        
                        ml.setMedicine_id(medId);
                        ml.setBatchNo(batchNumText.getText());
                        double d = Double.parseDouble(rateText.getText());
                        ml.setRate(d);
                        ml.setExpiry(expiryText.getText());
                        medicineLotBackend.insertMedicineLotDetails(ml);
                    } catch (Exception ex) {
                        createOptionPane("Database Error", "Error");
                    }
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
                    Logger.getLogger(NewMedicineLotForm.class.getName()).log(Level.SEVERE, null, ex);
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
