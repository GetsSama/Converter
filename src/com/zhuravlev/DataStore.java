package com.zhuravlev;

import java.util.ArrayList;

public class DataStore
{
    private DataReader dataReader;
    private ArrayList<double[]> dataInRows;
    private ArrayList<double[]> dataInColoms;
    private String[] Header;

    DataStore (DataReader dataReader)
    {
        this.dataReader = dataReader;
    }


}
