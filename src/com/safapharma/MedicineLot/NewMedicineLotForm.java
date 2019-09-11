/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;


import com.safapharma.Main.MainWindow;
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

/**
 *
 * @author Karan
 */
public class NewMedicineLotForm extends DialogForm {
    
    private MainWindow manager;
        
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
    
    

    public NewMedicineLotForm(MainWindow manager, MedicineLotBackend medicineLotBackend,Vector fullList) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        this.fullList = fullList;
        
        initUI();        
        addListeners();  
     
    }
    
    private void setComboBox(JComboBox mbox){
        mbox.setEditable(true);    
        AutoCompleteDecorator.decorate(mbox);       
        setFullNameList();
    }
    
    private void setFullNameList(){
        Vector medName = new Vector();
        for(Vector v : fullList){
            System.out.println(v.get(2).toString());
            medName.add(v.get(2));
        }
        fullNameList=medName;
    }
    
    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("Add MedicineLotForm");
        System.out.println("Creating and Adding Medicine Lot Form ");
        
//        String[] someString = {"", "Acetaminophen", "Adderall", "Amitriptyline", "Amlodipine","Karan","Manan","Raghav","Parman" };
        setFullNameList();
        
        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameCombo = new JComboBox(fullNameList);
        medicineNameAddButton = new FormButton("Add");
        medicineNameErrorLabel = new ErrorLabel();
        
        setComboBox(medicineNameCombo);

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
        
        getFormPanel().add(submitButton);
        getFormPanel().add(resetButton);
        getFormPanel().add(new JPanel());
//        getFormPanel().add(new JPanel());

        this.pack();
        hideErrorLabels();
    }

    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = validateInfo();
                if (isValid) {
                    try {
                        // Add the content provided in the form.
                    } catch (Exception ex) {
                        createOptionPane("Database Error", "Error");
                    }
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        medicineNameCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Button called");
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


    @Override
    protected void deleteScreen() {
        manager.deleteNewMedicineLotForm();
    }

    
}
