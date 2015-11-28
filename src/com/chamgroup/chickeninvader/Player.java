package com.chamgroup.chickeninvader;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {

	private final int START_Y = GROUND;
	private final int START_X = 270;

	private final String player = "player.png";
	private int width;
	private int height;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

		width = ii.getImage().getWidth(null);
		height = ii.getImage().getHeight(null);

		setImage(ii.getImage());
		setX(START_X);
		setY(START_Y - height / 2);
		getImageDimensions();
	}

	public void act() {
		x += dx;
		if (x <= 2)
			x = 2;
		if (x >= BOARD_WIDTH - width - 2)
			x = BOARD_WIDTH - width - 2;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}
}
