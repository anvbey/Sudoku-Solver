package com.example.javasudoku.computationLogic;

import com.example.javasudoku.constants.GameState;
import com.example.javasudoku.constants.Rows;
import com.example.javasudoku.problemdomain.SodukoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.javasudoku.problemdomain.SodukoGame.GRID_BOUNDARY;

public class GameLogic {

    public static SodukoGame getNewGame(){
        return new SodukoGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int grid[][]){
        if(sudokuIsInvalid(grid))
            return GameState.ACTIVE;
        if(tilesAreNotFilled(grid))
            return GameState.ACTIVE;

        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid))
            return true;
        if(columnsAreInvalid(grid))
            return true;
        if(squaresAreInvalid(grid))
            return true;
        else
            return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowsOfSquaresIsInvalid(Rows.TOP, grid))return true;

        if(rowsOfSquaresIsInvalid(Rows.MIDDLE, grid))return true;

        if(rowsOfSquaresIsInvalid(Rows.BOTTOM, grid))return true;
        return false;
    }

    private static boolean rowsOfSquaresIsInvalid(Rows top, int[][] grid) {
        switch (top){
            case TOP :
                    if(squareIsInvalid(0,0,grid))
                        return true;
                    if(squareIsInvalid(0,3,grid))
                        return true;
                    if(squareIsInvalid(0,6,grid))
                        return true;
                    return false;
            case MIDDLE :
                if(squareIsInvalid(3,0,grid))
                    return true;
                if(squareIsInvalid(3,3,grid))
                    return true;
                if(squareIsInvalid(3,6,grid))
                    return true;
                return false;
            case BOTTOM:
                if(squareIsInvalid(6,0,grid))
                    return true;
                if(squareIsInvalid(6,3,grid))
                    return true;
                if(squareIsInvalid(6,6,grid))
                    return true;
                return false;
            default:
                return false;
        }
    }

    private static boolean squareIsInvalid(int i, int i1, int[][] grid) {
        int yIndexEnd = i1 +3;
        int xIndexEnd = i + 3;

        List<Integer> square = new ArrayList<>();

        while(i1 < yIndexEnd){
            while(i < xIndexEnd){
                square.add(
                        grid[xIndexEnd][yIndexEnd]
                );
                i++;
            }
            i=i-3;
            i1++;
        }

        if(collectionHasRepeats(square))
            return true;
        return false;
    }

    private static boolean collectionHasRepeats(List<Integer> square) {
        for(int index=1;index <= GRID_BOUNDARY;index ++){
            if(Collections.frequency(square, index) > 1)
                return true;
        }
        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for(int xindex=0;xindex<GRID_BOUNDARY;xindex++){
            List<Integer> row = new ArrayList<>();
            for(int yindex=0;yindex<GRID_BOUNDARY;yindex++){
                row.add(grid[xindex][yindex]);
            }

            if(collectionHasRepeats(row))
                return true;
        }
        return false;
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for(int yindex=0;yindex<GRID_BOUNDARY;yindex++){
            List<Integer> row = new ArrayList<>();
            for(int xindex=0;xindex<GRID_BOUNDARY;xindex++){
                row.add(grid[xindex][yindex]);
            }

            if(collectionHasRepeats(row))
                return true;
        }
        return false;
    }

    private static boolean tilesAreNotFilled(int[][] grid) {
        for(int xIndex=0;xIndex<GRID_BOUNDARY;xIndex++)
        {
            for(int yIndex=0;yIndex<GRID_BOUNDARY;yIndex++){
                if(grid[xIndex][yIndex] == 0)
                    return true;
            }
        }
        return false;
    }
}
