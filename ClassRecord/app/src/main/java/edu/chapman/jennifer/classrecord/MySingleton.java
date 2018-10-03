package edu.chapman.jennifer.classrecord;

import java.util.ArrayList;
import java.util.HashMap;

public class MySingleton
{
    private static MySingleton mSingleton;
    public ArrayList<String> classNames = null;
    public ArrayList<String> classNumbers = null;

    public ArrayList<String> studFirstName = null;
    public ArrayList<String> studLastName = null;
    public ArrayList<String> studNum = null;

    public HashMap<String, ArrayList<String>> classList = null;

    public static MySingleton getInstance()
    {
        if(mSingleton == null)
            mSingleton = new MySingleton();

        return mSingleton;
    }

    private MySingleton() {
        classNames = new ArrayList<String>();
        classNumbers = new ArrayList<String>();

        studFirstName = new ArrayList<String>();
        studLastName = new ArrayList<String>();
        studNum = new ArrayList<String>();

        classList = new HashMap<String, ArrayList<String>>();
    }

    public HashMap<String, ArrayList<String>> getHashMap()
    {
        return classList;
    }
}
