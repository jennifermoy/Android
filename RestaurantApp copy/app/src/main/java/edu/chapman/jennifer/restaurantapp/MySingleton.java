package edu.chapman.jennifer.restaurantapp;

import java.util.ArrayList;
import java.util.HashMap;

public class MySingleton
{
    private static MySingleton mSingleton;

    public HashMap<String, String> restaurantList = null;

    public static MySingleton getInstance()
    {
        if(mSingleton == null)
            mSingleton = new MySingleton();

        return mSingleton;
    }

    private MySingleton() {


        restaurantList = new HashMap<String, String>();
    }
}
