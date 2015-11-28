package com.chamgroup.chickeninvader;

import javax.swing.JFrame;

public class ChickenInvader extends JFrame implements Commons {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3509318738500456982L;

	public ChickenInvader() {
		add(new Board());
		setTitle("Space Invaders");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(BOARD_WIDTH, BOARD_HEIGTH);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new ChickenInvader();
	}
}