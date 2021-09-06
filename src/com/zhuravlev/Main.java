package com.zhuravlev;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int count=0, count2=0;
        String path = "C:\\TestText.txt";
        DataTxt data = new DataTxt(path);

        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Светлана\\Desktop\\book.xlsx");
        XSSFSheet sheet = book.createSheet("Sheet1");

        XSSFRow Head = sheet.createRow(0);
        for (String title: data.getHeader())
        {
            XSSFCell cell = Head.createCell(count);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(title);
            count++;
        }
        count=0;
        for (int i=0; i<(data.getNumberOfRows()-1); i++)
        {
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
