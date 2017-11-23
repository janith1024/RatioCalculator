package com.js.util;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

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

    public double getDouble(int raw,int col){
      if(sheet != null){
        XSSFRow sheetRow = sheet.getRow(raw);
        if(sheetRow != null){
          XSSFCell cell = sheetRow.getCell(col);
          if(cell != null) {
            return cell.getNumericCellValue();
          }
        }
      }
      return 0;
    }
  }
}

