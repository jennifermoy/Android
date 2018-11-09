package edu.chapman.jennifer.filmdatabase;

import java.util.ArrayList;

public class MySingleton
{
    private static MySingleton mSingleton;

    public ArrayList<String> filmNameList;
    public String curr_FilmName;

    public static MySingleton getInstance()
    {
        if(mSingleton == null)
            mSingleton = new MySingleton();

        return mSingleton;
    }

    private MySingleton() {
        filmNameList = new ArrayList<>();
        curr_FilmName = new String();
    }
}
