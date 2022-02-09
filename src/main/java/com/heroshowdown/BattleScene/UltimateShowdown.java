package com.heroshowdown.BattleScene;
import javax.swing.*;
import javax.swing.JFrame;

import com.utils.ImageLoader;

public class UltimateShowdown {
    private JFrame frame;
    private int frameWidth;
    private int frameHeight; 

    public UltimateShowdown() {
        frame=new JFrame("Ultimate Showdown");
		//frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameWidth = 1000;
		frameHeight = 595;
		frame.setSize(frameWidth,frameHeight);
		frame.setVisible(true);
        frame.setContentPane(ImageLoader.load("background.png"));
        frame.pack();
    }       

    public void run() {
        JPanel battleScene = new BattleScenePanel();
        this.frame.add(battleScene);
    }
}   
