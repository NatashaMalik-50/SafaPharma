/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;

import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Karan
 */
public class ViewMedicineLotForm extends DialogForm {    
    private MainWindow manager;
        
    private FormLabel medicineNameLabel;
    private FormText medicineNameText;
    
    
    private FormLabel batchNumLabel;
    private FormText batchNumText;
    
    
    private FormLabel expiryLabel;
    private FormText expiryText;
    
    
    private FormLabel rateLabel;
    private FormText rateText;
    
    private FormLabel inStockLabel;
    private FormText inStockText;
    
    private FormButton closeButton;    
    
    private MedicineLotBackend medicineLotBackend;   
    
    

    public ViewMedicineLotForm(MainWindow manager, MedicineLotBackend medicineLotBackend,Vector selectedObject) {
        this.manager = manager;
        this.medicineLotBackend = medicineLotBackend;
        initUI();        
        addListeners();
        
        System.out.println(selectedObject);
        System.out.println("View object clicked");
        
        medicineNameText.setText(selectedObject.get(2).toString());
        batchNumText.setText(selectedObject.get(4).toString());
        expiryText.setText(selectedObject.get(3).toString());
        rateText.setText(selectedObject.get(5).toString());
        
        
    }
  
    

    private void initUI() {
        
        //Set and configure layout
        getFormLabel().setText("View MedicineLotForm");
        System.out.println("Viewing Medicine Lot Form ");
        
        medicineNameLabel = new FormLabel("Medicine Name");
        medicineNameText = new FormText();

        batchNumLabel = new FormLabel("Batch Number");
        batchNumText = new FormText();        

        expiryLabel = new FormLabel("Expiry");
        expiryText = new FormText();        

        rateLabel = new FormLabel("Rate");
        rateText = new FormText();

        inStockLabel = new FormLabel("In Stock");
        inStockText = new FormText();
        
        closeButton = new FormButton("Close");
        
        medicineNameText.setEditable(false);
        batchNumText.setEditable(false);
        expiryText.setEditable(false);
        rateText.setEditable(false);        
        inStockText.setEditable(false);
        
//        medicineNameText.setEnabled(false);
//        batchNumText.setEnabled(false);
//        expiryText.setEnabled(false);
//        rateText.setEnabled(false);        
//        inStockText.setEnabled(false);
        
        getFormPanel().add(medicineNameLabel);
        getFormPanel().add(medicineNameText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(batchNumLabel);
        getFormPanel().add(batchNumText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(expiryLabel);
        getFormPanel().add(expiryText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(rateLabel);
        getFormPanel().add(rateText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(inStockLabel);
        getFormPanel().add(inStockText);
        getFormPanel().add(new JPanel());
        
        getFormPanel().add(closeButton);
        getFormPanel().add(new JPanel());
        getFormPanel().add(new JPanel());
        

        this.pack();
    }

    private void addListeners() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Close the form once this is clicked.
                //TODO : Close the form once this is clicked.
                
            }
        });
    }

private void checkInStock(){
    try{
        //Get stock from dao. If stock exists :        
        boolean stockExists = true;
        if(stockExists){
            displayTable();
        }        
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

private void displayTable(){
    // Code to display the table in the view button.

}

    @Override
    protected void deleteScreen() {
                manager.deleteViewMedicineLotForm();
    }
    
}
