package com.zhuravlev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataTxt
{
    private String path;
    private ArrayList<String> arrayOfString;
    private String delim;
    private int numberRows = 0, numberColoms = 0;

    DataTxt(String pathToFileTxt)
    {
        path = pathToFileTxt;
        if (isRightFormatTxt())
        {
            arrayOfString = txtToArrayOfString();
        }
    }

    private ArrayList<String> txtToArrayOfString ()
    {
        ArrayList<String> Rows = new ArrayList<>();

        try (FileInputStream input = new FileInputStream(path))
        {
            Scanner scn  = new Scanner(input);
            while (scn.hasNext())
                Rows.add(scn.nextLine());
            scn.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        numberRows = Rows.size();
        return Rows;
    }

    private boolean isRightFormatTxt()
    {
        String[] Rows = new String[2];
        String corrForm = " x   y   z...\n 0   0   1...\n0.3  3   0...\n...\nUse delimiter: ' ' or ','";

        try (FileInputStream input = new FileInputStream(path))
        {
            Scanner scn  = new Scanner(input);
            if (scn.hasNext())
            {
                Rows[0] = scn.nextLine();
                Rows[1] = scn.nextLine();
            }
            else
                System.out.println("Empty file!");
            scn.close();

            delim = getDelimiter(Rows[0]);

            Scanner scnRow1 = new Scanner(Rows[0]);
            Scanner scnRow2 = new Scanner(Rows[1]);
            scnRow1.useDelimiter(delim);
            scnRow2.useDelimiter(delim);

            boolean flag = false;
            String str;
            double num;

            while (scnRow1.hasNext())
            {
               str = scnRow1.next();
               try
               {
                   num = Double.parseDouble(str);
                   System.out.println("Incorrect format!\nExample of correct format:\n"+corrForm);
                   return false;
               }
               catch (NumberFormatException e)
               {
               }
            }
            while (scnRow2.hasNext())
            {
                str = scnRow2.next();
                try
                {
                    num = Double.parseDouble(str);
                    numberColoms++;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Incorrect format!\nExample of correct format:\n"+corrForm);
                    return false;
                }
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private String getDelimiter(String row)
    {
        for (char ch: row.toCharArray())
        {
            if (Character.isWhitespace(ch))
                return " ";
        }
        return ",";
    }

    public int getNumberOfRows()
    {
        return numberRows;
    }
    public int getNumberOfColoms() {return numberColoms;}

    public String[] getHeader()
    {
        Scanner scn = new Scanner(arrayOfString.get(0));
        scn.useDelimiter(delim);
        String[] strArr = new String[numberColoms];

        for (int i=0; i<numberColoms; i++)
            strArr[i] = scn.next();

        scn.close();
        return strArr;
    }

    public List<double[]> getData()
    {
        int count = 1;
        List<double[]> rowsArr = new ArrayList<>();

        for (int j=1; j<numberRows; j++)
        {
            String str = arrayOfString.get(j);
            double[] row = new double[numberColoms];
            Scanner scn = new Scanner(str);
            scn.useDelimiter(delim);

            for (int i=0; i<numberColoms; i++)
            {
                try
                {
                    row[i] = Double.parseDouble(scn.next());
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Incorrect data! in row: "+ count);
                }
            }
            count++;
            rowsArr.add(row);
        }
        return rowsArr;
    }

}
