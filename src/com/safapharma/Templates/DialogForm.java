/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Templates;

import com.safapharma.Helpers.DesignConstants;
import com.safapharma.Helpers.IconConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Natasha Malik
 */
public class DialogForm extends javax.swing.JDialog {

    /**
     * Creates new form DialogForm
     */
    public DialogForm() {
        initComponents();
        this.setLocation(160, 50);
        this.setAlwaysOnTop(true);
        this.setModal(true);
        formLabel.setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);
        formLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.setLayout(new GridLayout(0, 3));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    protected JLabel getFormLabel() {
        return formLabel;
    }

    protected JPanel getFormPanel() {
        return formPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formLabel = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        formLabel.setText("Form");

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel formLabel;
    private javax.swing.JPanel formPanel;
    // End of variables declaration//GEN-END:variables

    protected class FormButton extends JButton {

        public FormButton(String text) {
            super(text);
            init();
        }

        void init() {
            setFont(DesignConstants.FONT_SIZE_14_CALIBRI_BOLD);
        }
    }

    protected class FormText extends JTextField {

        public FormText() {
            super();
            init();
        }

        void init() {
            setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
            setPreferredSize(new Dimension(DesignConstants.NEW_FORM_WIDTH, HEIGHT));
        }

    }

    protected class FormLabel extends JLabel {

        public FormLabel(String text) {
            super(text);
            init();
        }

        public FormLabel(String text, ImageIcon imageIcon) {
            super(text, imageIcon, JLabel.CENTER);
            init();
        }

        void init() {
            setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);
            setHorizontalAlignment(JLabel.CENTER);
            setPreferredSize(new Dimension(DesignConstants.NEW_FORM_WIDTH, HEIGHT));
        }

    }

    protected class ErrorLabel extends JLabel {
        public ErrorLabel() {
            super();
            setForeground(Color.red);
            setFont(DesignConstants.FONT_SIZE_14_CALIBRI);
            setHorizontalAlignment(JLabel.LEFT);
            setIcon(new ImageIcon(getClass().getResource(IconConstants.ERROR_ICON)));
        }

        public void setErrorText(String text) {
            setText(text);
        }
    }

    protected void createOptionPane(String message, String title) {
        JOptionPane optionPane = new JOptionPane(message);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
