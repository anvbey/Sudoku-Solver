package com.example.javasudoku.buildLogic;

import com.example.javasudoku.computationLogic.GameLogic;
import com.example.javasudoku.persistence.LocalStorageImp1;
import com.example.javasudoku.problemdomain.IStorage;
import com.example.javasudoku.problemdomain.SodukoGame;
import com.example.javasudoku.userinterface.IUserInterfaceContract;
import com.example.javasudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SodukoGame intialState;
        IStorage storage = new LocalStorageImp1();

        try {
            intialState = storage.getGameData();
        }
        catch (IOException e){
            intialState = GameLogic.getNewGame();
            storage.updateGameData(intialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage,userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(intialState);
    }
}
