package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) {

        try {

            FileInputStream file =
                    new FileInputStream(filePath);

            workbook =
                    WorkbookFactory.create(file);

            sheet =
                    workbook.getSheet(sheetName);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath,
                    e
            );
        }
    }

    public int getRowCount() {

        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {

        return sheet
                .getRow(0)
                .getPhysicalNumberOfCells();
    }

    public String getCellData(int row, int column) {

        Cell cell =
                sheet.getRow(row)
                        .getCell(column);

        return cell.toString();
    }
    public void closeWorkbook() {

        try {

            if (workbook != null) {
                workbook.close();
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to close Excel workbook",
                    e
            );
        }
    }
}