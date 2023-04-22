/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweepergame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author aibikuan
 */
public class Board extends JPanel{
    private int rows;
    private int columns;
    private int mines;
    private int[][] board;
    private boolean isFirstClick = true;
    protected JFrame frame;
    
    private int openedCells;
    
    public Board() {
        this.rows = 10;
        this.columns = 10;
        this.mines = 20;
        this.board = new int[rows][columns];
        setLayout(new GridLayout(rows, columns, 0, 0)); //sets the layout the way i need
        createButtons();
        
        int width = columns * 30; // each cell is 30 pixels wide
        int height = rows * 30; // each cell is 30 pixels high
        setPreferredSize(new Dimension(width, height)); // setting my board to be the same size as the window
    }
    
    // all buttons are created
    private void createButtons() {
        ImageIcon defaultCell = new ImageIcon("src/main/java/image/facingDown.png"); // setting an image on buttons
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton(); //creating buttons
                
                Image img = defaultCell.getImage(); //getting the image
                Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); //scaling the image that we got
                button.setIcon(new ImageIcon(scaledImg)); //putting the scaled image as an icon of button
            
                final int finalI = i;
                final int finalJ = j;
                
                button.addActionListener(e -> buttonClick(button, finalI, finalJ)); //used Lambda expression for action listener
                add(button);
            }
        }
    }
    
    // generating bombs
    private void generateMines(int r, int c) {
        int count = 0;
        while (count < mines) {
            // putting mines in random positions
           int row = (int) (Math.random() * rows);
           int column = (int) (Math.random() * columns);
           if (board[row][column] == 0 && !(r == row && c == column)) {
               board[row][column] = -1; //has mines
               count++;
            }
        }
        isFirstClick = false; //setting false after first 
    }            

    // when user clicks the button
    private void buttonClick(JButton button, int row, int column) {
        if (isFirstClick) {
            generateMines(row, column);
        }
        
        if (board[row][column] == -1) {
            gameOver();
        } 
        else {
            int minesCount = surroundingMines(row, column);
            if (minesCount == 0) {
                ImageIcon image0 = new ImageIcon("src/main/java/image/0.png");
                Image img = image0.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);
                button.setIcon(scaledIcon);
                openedCells++;
            } 
            else {
                ImageIcon image = new ImageIcon("src/main/java/image/" + minesCount + ".png"); //sets image according to bomb count
                Image img = image.getImage();
                Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImg));
                openedCells++;
            }
            
            if (openedCells == (rows * columns) - mines) gameWon();
        }
    }
    
    // counting the amount of bombs around the cell
    // incrementing the count by one everytime the cells value is -1 (has bomb)
    private int surroundingMines(int row, int column) {
        int count = 0;
        // going through rows, ensuring that the starting index is at least 0
        // and that the ending row index is at most the last row index of the board
        for (int i = Math.max(0, row - 1); i <= Math.min(rows - 1, row + 1); i++) { 
            for (int j = Math.max(0, column - 1); j <= Math.min(columns - 1, column + 1); j++) { //going through columns same as rows
                if (board[i][j] == -1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // game over method
    private void gameOver() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = (JButton) getComponent(i * columns + j); // returns the button at the (i,j) position in the grid layout
                if (board[i][j] == -1) {
                    ImageIcon bomb = new ImageIcon("src/main/java/image/bomb.png");
                    Image img = bomb.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(img);
                    button.setIcon(scaledIcon);
                }
                button.setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(this, "Game over!", "Minesweeper", JOptionPane.ERROR_MESSAGE); // pop up standard dialog box
    }
    
    // game won method
    private void gameWon() {
        JOptionPane.showMessageDialog(this, "Congrats, you won!", "Minesweeper", JOptionPane.PLAIN_MESSAGE);
    }
}
