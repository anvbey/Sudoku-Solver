package com.example.javasudoku.userinterface;

import com.example.javasudoku.problemdomain.SodukoGame;

public interface IUserInterfaceContract {
    interface EventListener{
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }

    interface View{
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SodukoGame game);
        void showDialog(String Message);
        void showError(String message);
    }
}
