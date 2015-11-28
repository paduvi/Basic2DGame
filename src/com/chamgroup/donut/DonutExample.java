package com.chamgroup.donut;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class DonutExample extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176123182598552061L;

	public DonutExample() {

        initUI();
    }

    private void initUI() {

        add(new Donut());

        setSize(330, 330);

        setTitle("Donut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DonutExample ex = new DonutExample();
                ex.setVisible(true);
            }
        });
    }

}
