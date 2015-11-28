package com.chamgroup.chickeninvader;

import javax.swing.ImageIcon;

public class Chicken extends Sprite {

	private Egg egg;
	private final String shot = "chicken.png";

	public Chicken(int x, int y) {
		this.x = x;
		this.y = y;

		egg = new Egg(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
		setImage(ii.getImage());
		getImageDimensions();
	}

	public void act(int direction) {
		this.x += direction;
	}

	public Egg getEgg() {
		return egg;
	}

	public class Egg extends Sprite {

		private final String egg = "egg.png";
		private boolean destroyed;

		public Egg(int x, int y) {
			setDestroyed(true);
			this.x = x;
			this.y = y;
			ImageIcon ii = new ImageIcon(this.getClass().getResource(egg));
			setImage(ii.getImage());
		}

		public void setDestroyed(boolean destroyed) {
			this.destroyed = destroyed;
		}

		public boolean isDestroyed() {
			return destroyed;
		}
	}
}
