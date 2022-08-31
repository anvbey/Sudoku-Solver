package com.example.javasudoku.problemdomain;

import java.io.IOException;

public interface IStorage {

    void updateGameData(SodukoGame game) throws IOException;
    SodukoGame getGameData() throws IOException;
}
