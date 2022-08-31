package com.example.javasudoku.problemdomain;

import com.example.javasudoku.computationLogic.SudokuUtilities;
import com.example.javasudoku.constants.GameState;

import java.io.Serializable;

public class SodukoGame implements Serializable {
    private final GameState gameState;
    private final int[][] gridState;

    public static final int GRID_BOUNDARY = 9;

    public SodukoGame(GameState gameState, int[][] gridState)
    {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState(){
        return gameState;
    }

    public int[][] getCopyofGridState(){
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
