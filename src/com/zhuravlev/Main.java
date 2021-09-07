package com.zhuravlev;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int count=0, count2=0;
        System.out.println("Enter path to .txt file");
        Scanner scn = new Scanner(System.in);
        String path = scn.nextLine();
        scn.close();

        DataReader data = new DataReader(path);

        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(path.replace("txt", "xlsx"));
        XSSFSheet sheet = book.createSheet("Sheet1");

        XSSFRow Head = sheet.createRow(0);
        System.out.println("Create header");
        for (String title: data.getHeader())
        {
            XSSFCell cell = Head.createCell(count);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(title);
            count++;
        }
        count=0;
        System.out.println("Create rows");
        for (int i=0; i<(data.getNumberOfRows()-1); i++)
        {
            System.out.println("Row "+ (i+1));
            double[] rowData = data.getData().get(i);
            XSSFRow row = sheet.createRow(count+1);
            count2=0;
            for (double val: rowData)
            {
                XSSFCell cell = row.createCell(count2);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(val);
                count2++;
            }
            count++;
        }

        book.write(fileOut);
        fileOut.close();
        System.out.println("Complete!");

    }


}
