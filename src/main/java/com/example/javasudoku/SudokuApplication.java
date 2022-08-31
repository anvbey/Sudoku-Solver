package com.example.javasudoku;

import com.example.javasudoku.buildLogic.SudokuBuildLogic;
import com.example.javasudoku.userinterface.IUserInterfaceContract;
import com.example.javasudoku.userinterface.UserInterfaceImp1;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    private IUserInterfaceContract.View uiImp1;

    @Override
    public void start(Stage primaryStage) throws IOException {
       uiImp1 = new UserInterfaceImp1(primaryStage);
       try {
           SudokuBuildLogic.build(uiImp1);
       }
       catch (IOException e){
           e.printStackTrace();
           throw e;
       }
    }

    public static void main(String[] args) {
        launch(args);
    }
}