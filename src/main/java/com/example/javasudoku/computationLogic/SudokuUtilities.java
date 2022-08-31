package com.example.javasudoku.computationLogic;

import com.example.javasudoku.problemdomain.SodukoGame;

public class SudokuUtilities {
    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
        for (int xIndex = 0; xIndex < SodukoGame.GRID_BOUNDARY; xIndex++) {
            for(int yIndex= 0;yIndex< SodukoGame.GRID_BOUNDARY;yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    public static int[][] copyToNewArray(int[][] oldArray){
        int[][] newArray = new int[SodukoGame.GRID_BOUNDARY][SodukoGame.GRID_BOUNDARY];
        for (int xIndex = 0; xIndex < SodukoGame.GRID_BOUNDARY; xIndex++) {
            for(int yIndex= 0;yIndex< SodukoGame.GRID_BOUNDARY;yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
        return newArray;
    }
}
