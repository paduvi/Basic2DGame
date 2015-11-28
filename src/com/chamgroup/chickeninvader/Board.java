package com.chamgroup.chickeninvader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Commons {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411633571382614570L;
	private Dimension d;
	private ArrayList<Chicken> chickens;
	private Player player;
	private Shot shot;

	private int chickenX = 150;
	private int chickenY = 5;
	private int direction = -1;
	private int deaths = 0;

	private boolean ingame = true;
	private final String expl = "explosion.png";
	private final String chickenpix = "chicken.png";
	private String message = "Game Over";

	private Thread animator;

	public Board() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
		setBackground(Color.black);

		gameInit();
		setDoubleBuffered(true);
	}

	public void addNotify() {
		super.addNotify();
		gameInit();
	}

	public void gameInit() {

		chickens = new ArrayList<Chicken>();

		ImageIcon ii = new ImageIcon(this.getClass().getResource(chickenpix));

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				Chicken chicken = new Chicken(chickenX + 32 * j, chickenY + 32 * i);
				chicken.setImage(ii.getImage());
				chickens.add(chicken);
			}
		}

		player = new Player();
		shot = new Shot();

		if (animator == null || !ingame) {
			animator = new Thread(this);
			animator.start();
		}
	}

	public void drawChickens(Graphics g) {
		Iterator<Chicken> it = chickens.iterator();

		while (it.hasNext()) {
			Chicken chicken = (Chicken) it.next();

			if (chicken.isVisible()) {
				g.drawImage(chicken.getImage(), chicken.getX(), chicken.getY(), this);
			}

			if (chicken.isDying()) {
				chicken.die();
			}
		}
	}

	public void drawPlayer(Graphics g) {

		if (player.isVisible()) {
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}

		if (player.isDying()) {
			player.die();
			ingame = false;
		}
	}

	public void drawShot(Graphics g) {
		if (shot.isVisible())
			g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
	}

	public void drawBombing(Graphics g) {

		Iterator<Chicken> i3 = chickens.iterator();

		while (i3.hasNext()) {
			Chicken a = (Chicken) i3.next();

			Chicken.Egg b = a.getEgg();

			if (!b.isDestroyed()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.green);

		if (ingame) {

			g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
			drawChickens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void gameOver() {

		Graphics g = this.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2, BOARD_WIDTH / 2);
	}

	public void animationCycle() {

		if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
			ingame = false;
			message = "Game won!";
		}

		// player

		player.act();

		// shot
		if (shot.isVisible()) {
			Iterator<Chicken> it = chickens.iterator();

			while (it.hasNext()) {
				Chicken chicken = (Chicken) it.next();

				if (chicken.isVisible() && shot.isVisible()) {
					if (shot.getRect().intersects(chicken.getRect())) {
						ImageIcon ii = new ImageIcon(getClass().getResource(expl));
						chicken.setImage(ii.getImage());
						chicken.setDying(true);
						deaths++;
						shot.die();
					}
				}
			}

			int y = shot.getY();
			y -= 4;
			if (y < 0)
				shot.die();
			else
				shot.setY(y);
		}

		// aliens

		Iterator<Chicken> it1 = chickens.iterator();

		while (it1.hasNext()) {
			Chicken a1 = (Chicken) it1.next();
			int x = a1.getX();

			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
				direction = -1;
				Iterator<Chicken> i1 = chickens.iterator();
				while (i1.hasNext()) {
					Chicken a2 = (Chicken) i1.next();
					a2.setY(a2.getY() + GO_DOWN);
				}
			}

			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;

				Iterator<Chicken> i2 = chickens.iterator();
				while (i2.hasNext()) {
					Chicken a = (Chicken) i2.next();
					a.setY(a.getY() + GO_DOWN);
				}
			}
		}

		Iterator<Chicken> it = chickens.iterator();

		while (it.hasNext()) {
			Chicken chicken = (Chicken) it.next();
			if (chicken.isVisible()) {

				int y = chicken.getY();

				if (y > GROUND - chicken.getRect().getHeight()) {
					ingame = false;
					message = "Invasion!";
				}

				chicken.act(direction);
			}
		}

		// bombs

		Iterator<Chicken> i3 = chickens.iterator();
		Random generator = new Random();

		while (i3.hasNext()) {
			int shot = generator.nextInt(15);
			Chicken a = (Chicken) i3.next();
			Chicken.Egg b = a.getEgg();
			if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {

				b.setDestroyed(false);
				b.setX(a.getX());
				b.setY(a.getY());
			}

			if (player.isVisible() && !b.isDestroyed()) {
				if (b.getRect().intersects(player.getRect())) {
					ImageIcon ii = new ImageIcon(this.getClass().getResource(expl));
					player.setImage(ii.getImage());
					player.setDying(true);
					b.setDestroyed(true);
				}
			}

			if (!b.isDestroyed()) {
				b.setY(b.getY() + 1);
				if (b.getY() >= GROUND - b.getRect().getHeight()) {
					b.setDestroyed(true);
				}
			}
		}
	}

	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (ingame) {
			repaint();
			animationCycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {

			player.keyPressed(e);

			int x = player.getX();
			int y = player.getY();

			if (ingame) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (!shot.isVisible())
						shot = new Shot(x, y);
				}
			}
		}
	}
}