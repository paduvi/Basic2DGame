package com.chamgroup.aircraft;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MovingSpriteEx extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4335368488625206278L;

	public MovingSpriteEx() {

		initUI();
	}

	private void initUI() {

		add(new AirCraft());

		setSize(400, 300);
		setResizable(false);

		setTitle("Moving sprite");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				MovingSpriteEx ex = new MovingSpriteEx();
				ex.setVisible(true);
			}
		});
	}
}