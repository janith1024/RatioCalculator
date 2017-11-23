package com.js.util;


import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class ExcelManager {


  public static ExcelSheet getReadSheet(InputStream fileInputStream, int sheetIndex){
//    try {
//      return new ExcelSheet(WorkbookFactory.create(fileInputStream).getSheetAt(sheetIndex));
//    }
//    catch (IOException e) {
//      e.printStackTrace();
//    }
    return new ExcelSheet(null);
  }

  public static class ExcelSheet{

    private XSSFSheet sheet;

    ExcelSheet(XSSFSheet sheet){
      this.sheet = sheet;
    }

    public String get(int raw,int col){
      if(sheet != null){
//        XSSFRow sheetRow = sheet.getRow(raw);
//        if(sheetRow != null){
//          return sheetRow.getCell(col).getRawValue();
//        }
      }
      return null;
    }
  }
}

