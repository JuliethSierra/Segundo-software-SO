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
public class ButtonMenu extends JButton{
    
    	public ButtonMenu(String text) {
		super(text);
		setBackground(Color.decode("#9a8c98"));
		setForeground(Color.WHITE);
                setFont(ConstantsGUI.FONT_MENU_ITEMS);
		setPreferredSize(new Dimension(280,40));
	}
    
}
