package com.example.javasudoku.persistence;

import com.example.javasudoku.problemdomain.IStorage;
import com.example.javasudoku.problemdomain.SodukoGame;

import java.io.*;

public class LocalStorageImp1 implements IStorage {
    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt"
    );

    @Override
    public void updateGameData(SodukoGame game) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        }
        catch (IOException e){
            throw new IOException("Unable to access Game data");
        }
    }

    @Override
    public SodukoGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SodukoGame gameState = (SodukoGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        }
        catch (ClassNotFoundException e){
            throw new IOException("File not found");
        }
    }
}
