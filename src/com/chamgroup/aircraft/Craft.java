package com.chamgroup.aircraft;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {

    private int dx;
    private int dy;
    private ArrayList<Missile> missiles;

    public Craft(int x, int y) {
        super(x, y);
        
        initCraft();
    }

    private void initCraft() {

        missiles = new ArrayList<Missile>();
        loadImage("craft.png"); 
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > 400 - width) x = 400 - width;
        if(y > 300 - height) y = 300 - height;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
