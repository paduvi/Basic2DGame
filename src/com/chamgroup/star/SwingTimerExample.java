package com.chamgroup.star;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SwingTimerExample extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7686595777971546874L;

	public SwingTimerExample() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Star());
        
        setResizable(false);
        pack();
        
        setTitle("Star");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new SwingTimerExample();
                ex.setVisible(true);                
            }
        });
    }

}
