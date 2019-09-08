/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Helpers;

import static com.safapharma.Helpers.Constants.DATE_LABEL;
import static com.safapharma.Helpers.Constants.DAY_LABEL;
import static com.safapharma.Helpers.Constants.TIME_LABEL;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Natasha Malik
 */
public class ClockLabel extends JLabel implements ActionListener {

    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type) {
        this.type = type;
        setForeground(Color.BLUE);
        setFont(new Font("arial-black", Font.BOLD, 18));
        setHorizontalAlignment(SwingConstants.CENTER);
        switch (type) {
            case DATE_LABEL:
                sdf = new SimpleDateFormat("EE, dd MMM yyyy");
                break;
            case TIME_LABEL:
                sdf = new SimpleDateFormat("hh:mm:ss a");
                break;
            case DAY_LABEL:
                sdf = new SimpleDateFormat("EEEE  ");
                break;

            default:
                sdf = new SimpleDateFormat();
                break;
        }

        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));
    }
}
