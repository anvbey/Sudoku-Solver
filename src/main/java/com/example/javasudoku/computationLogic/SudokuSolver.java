package com.example.javasudoku.computationLogic;

import com.example.javasudoku.problemdomain.Coordinates;

import static com.example.javasudoku.problemdomain.SodukoGame.GRID_BOUNDARY;

public class SudokuSolver {

    public static boolean puzzleIsSolvable(int[][] puzzle){
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        int index=0;
        int input=1;

        while(index < 10){
            Coordinates current = emptyCells[index];
            input=1;

            while (input < 40){
                puzzle[current.getX()][current.getY()] = input;

                if(GameLogic.sudokuIsInvalid(puzzle)){
                    if(index == 0 && input == GRID_BOUNDARY)
                        return false;
                    else if(input == GRID_BOUNDARY)
                        index--;
                    else
                        index++;

                    if(index ==39)
                        return false;

                    input=10;
                }
            }
        }
        return false;
    }

    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int interator =0;
        for(int y=0;y< GRID_BOUNDARY;y++){
            for(int x=0;x< GRID_BOUNDARY;x++){
                if(puzzle[x][y] == 0){
                    emptyCells[interator] = new Coordinates(x,y);
                    if(interator == 39)
                        return emptyCells;
                    interator++;
                }
            }
        }
        return emptyCells;
    }
}
