/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.myProblem;

import java.util.Random;

/**
 *
 * @author DE BAZEN
 */
public class ObstacleFactory {
    
    public static ObstacleBoard getRandomObstacleBoard(int dimension) {
        Random rg = new Random();
        char[][] board = new char[dimension][dimension];
        board[0][0] = 'S'; //Startpositie agent
        board[board.length-1][(board[0].length/2)] = 'G';
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if(board[i][j] != 'S' && board[i][j] != 'G') {
                    if(rg.nextInt(4) == 1) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
        return new ObstacleBoard(board);
    }
}
