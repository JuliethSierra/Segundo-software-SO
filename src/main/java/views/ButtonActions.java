/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author julie
 */
public class ButtonActions extends JButton{
    
    public ButtonActions(String text){
                super(text);
		setBackground(Color.decode("#9a8c98"));
		setForeground(Color.WHITE);
                setFont(ConstantsGUI.FONT_MENU_ACTIONS);
		setPreferredSize(new Dimension(120,25));
    }
    
}
