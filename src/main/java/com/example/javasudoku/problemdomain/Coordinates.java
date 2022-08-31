package com.example.javasudoku.problemdomain;

import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x,int y)
    {
        this.x= x;
        this.y = y;
    }
    public int getY()
    {
        return x;
    }
    public int getX()
    {
        return x;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

}
