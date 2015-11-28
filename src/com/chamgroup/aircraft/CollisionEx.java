package com.chamgroup.aircraft;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CollisionEx extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8904604476047584209L;

	public CollisionEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Collision");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CollisionEx ex = new CollisionEx();
                ex.setVisible(true);
            }
        });
    }
}
