/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Main.MainWindow;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

/**
 *
 * @author shiva
 */
public class NewUpdateBillForm extends DialogForm {
    private MainWindow manager;
    private HomeScreenPanel homeScreenPanel;    
    private DialogForm.FormLabel QuantityLabel;
    private DialogForm.FormText QuantityText;
    private DialogForm.ErrorLabel QuantityLabelError;
    private DialogForm.FormButton Btnsubmit;
    
     public NewUpdateBillForm(MainWindow manager, HomeScreenPanel homeScreenPanel) {
        this.manager = manager;
        this.homeScreenPanel = homeScreenPanel;
        initUI();        
        //addListeners();
    }
    @Override
    protected void deleteScreen() {
        manager.deleteNewStockViewForm();
    }

    private void initUI() {
        getFormLabel().setText("Update Bill Form");
        QuantityLabel = new FormLabel("Quantity :");
        QuantityText = new FormText();
        //QuantityText.setAlignmentX(10);
        QuantityLabelError = new ErrorLabel();
        Btnsubmit = new FormButton("Submit");
        getFormPanel().add(QuantityLabel);
        getFormPanel().add(QuantityText);
        getFormPanel().add(QuantityLabelError);
        //getFormPanel().setLayout(new BoxLayout(getFormLabel(), BoxLayout.Y_AXIS));
        getFormPanel().add(Btnsubmit);
        
                
    }
    
    
    
}
