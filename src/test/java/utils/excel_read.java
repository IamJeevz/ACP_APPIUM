package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;


public class excel_read {
    public  String cellValues(String path, String sheetname, int row, int cell) {
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook WB = new XSSFWorkbook(file);
            XSSFSheet SH=WB.getSheet(sheetname);
            XSSFCell CELL=SH.getRow(row).getCell(cell);
            if(CELL.getCellType()== CellType.STRING)
            {
                return CELL.getStringCellValue();
            }
            else
            {
                double val=CELL.getNumericCellValue();
                int value=(int)val;
                return  String.valueOf(value);
            }
        }
        catch (Exception e) {
        }
        return null;
    }

    public  int CellCount(String path,String sheetname)
    {
        try
        {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook WB = new XSSFWorkbook(file);
            XSSFSheet SH=WB.getSheet(sheetname);
            return  SH.getLastRowNum();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }
}