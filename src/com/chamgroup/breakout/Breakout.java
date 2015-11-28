package com.chamgroup.breakout;

import javax.swing.JFrame;

public class Breakout extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3500589324041258472L;

	public Breakout()
    {
        add(new Board());
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Breakout();
    }
}