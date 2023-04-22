/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweepergame;

import javax.swing.JFrame;

/**
 *
 * @author aibikuan
 */
public class Frame extends JFrame{
    public Frame() {
        this.setVisible(true); // making the frame visible
        this.setTitle("Minesweeper Game"); // setting the title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the program
        this.setResizable(false); // prevents frame from being resized
    }
}
