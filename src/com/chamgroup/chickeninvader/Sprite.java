package com.chamgroup.chickeninvader;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

	private boolean visible;
	private Image image;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean dying;
	protected int dx;

	public Sprite() {
		visible = true;
	}

	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public void die() {
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	protected void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public boolean isDying() {
		return this.dying;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
