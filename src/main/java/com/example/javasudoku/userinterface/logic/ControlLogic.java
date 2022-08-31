package com.example.javasudoku.userinterface.logic;

import com.example.javasudoku.computationLogic.GameLogic;
import com.example.javasudoku.constants.GameState;
import com.example.javasudoku.constants.Messages;
import com.example.javasudoku.problemdomain.IStorage;
import com.example.javasudoku.problemdomain.SodukoGame;
import com.example.javasudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;


    public ControlLogic(IStorage storage, IUserInterfaceContract.View view){
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try{
            SodukoGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyofGridState();
            newGridState[x][y] = input;

            gameData= new SodukoGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState
            );

            storage.updateGameData(gameData);

            view.updateSquare(x,y,input);

            if(gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );
            view.updateBoard(storage.getGameData()
            );
        }catch (IOException e){
            view.showError(Messages.ERROR);
        }
    }
}
