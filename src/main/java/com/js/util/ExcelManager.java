package com.js.util;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Iterator;

public class ExcelManager {


  public static ExcelSheet getReadSheet(InputStream fileInputStream, int sheetIndex) throws InvalidFormatException {
    try {
      return new ExcelSheet((XSSFSheet) WorkbookFactory.create(fileInputStream).getSheetAt(sheetIndex));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new ExcelSheet(null);
  }

  public static class ExcelSheet{

    private XSSFSheet sheet;

    ExcelSheet(XSSFSheet sheet){
      this.sheet = sheet;
    }

    /**
     * This method return the double value of the excel sheet for the given row and the column
     * @param row row of the excel file
     * @param col column of the file
     * @return double value
     */
    public double getDouble(int row,int col){
      if(sheet != null){
        XSSFRow sheetRow = sheet.getRow(row);
        if(sheetRow != null){
          XSSFCell cell = sheetRow.getCell(col);
          if(cell != null) {
            return cell.getNumericCellValue();
          }
        }
      }
      return 0;
    }

    /**
     * This method is return double value of the given column for the matching name of the first column
     * @param name name of field
     * @param col column of the excel
     * @return double value
     */
    public double getDouble(String name,int col){
      if(sheet != null){
        Iterator<Row> loop = sheet.iterator();
        while (loop.hasNext()){
          Row row = loop.next();
          if(row.getCell(0).getStringCellValue().equalsIgnoreCase(name)){
            Cell cell = row.getCell(col);
            if(cell != null) {
              return cell.getNumericCellValue();
            }
            break;
          }
        }
      }
      return 0;

    }
  }
}

