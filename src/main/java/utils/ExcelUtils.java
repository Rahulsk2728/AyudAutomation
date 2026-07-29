package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) {

        try {
            FileInputStream fis = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            throw new RuntimeException("Unable to read Excel file", e);
        }
    }

    // Read cell data
    public String getCellData(int rowNum, int colNum) {

        Row row = sheet.getRow(rowNum);

        Cell cell = row.getCell(colNum);

        return cell.toString();
    }

    // Get total rows (excluding header)
    public int getRowCount() {

        return sheet.getLastRowNum();
    }

    // Close workbook
    public void closeWorkbook() {

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}