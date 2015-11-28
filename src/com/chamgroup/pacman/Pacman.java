package com.chamgroup.pacman;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3793252505684418822L;

	public Pacman() {

		initUI();
	}

	private void initUI() {

		add(new Board());
		setTitle("Pacman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(380, 420);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setFocusable(false);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Pacman ex = new Pacman();
				ex.setVisible(true);
			}
		});
	}
}