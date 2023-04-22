/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.minesweepergame;

/**
 *
 * @author aibikuan
 */
public class MinesweeperGame {

    public static void main(String[] args) {
        Frame frame = new Frame(); // creating new frame
        Board board = new Board(); // creating a board
        frame.add(board); // adding the board to the frame
        frame.pack();
    }
}
