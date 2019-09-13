/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Templates;

import com.safapharma.Helpers.DesignConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Natasha Malik
 */
public class BlinkingLabel extends JLabel {

    private static final int BLINKING_RATE = 500; // in ms

    private boolean blinkingOn = true;

    public BlinkingLabel(String text) {
        super(text);
        setFont(DesignConstants.FONT_SIZE_18_CALIBRI_BOLD);
        setForeground(Color.red);
        Timer timer = new Timer(BLINKING_RATE, new TimerListener(this));
        timer.setInitialDelay(0);
        timer.start();
    }

    public void setBlinking(boolean flag) {
        this.blinkingOn = flag;
    }

    public boolean getBlinking(boolean flag) {
        return this.blinkingOn;
    }

    private class TimerListener implements ActionListener {

        private BlinkingLabel bl;
        private Color bg;
        private Color fg;
        private boolean isForeground = true;

        public TimerListener(BlinkingLabel bl) {
            this.bl = bl;
            fg = bl.getForeground();
            bg = bl.getBackground();
        }

        public void actionPerformed(ActionEvent e) {
            if (bl.blinkingOn) {
                if (isForeground) {
                    bl.setForeground(fg);
                } else {
                    bl.setForeground(bg);
                }
                isForeground = !isForeground;
            } else {
                // here we want to make sure that the label is visible
                // if the blinking is off.
                if (isForeground) {
                    bl.setForeground(fg);
                    isForeground = false;
                }
            }
        }

    }
}
