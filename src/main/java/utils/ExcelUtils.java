package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * Utility class for reading data from Excel files to support Data-Driven Testing (DDT).
 */
public class ExcelUtils {

    /**
     * Reads an Excel sheet and converts it into a 2D Object array suitable for TestNG DataProviders.
     * * @param filePath  The relative or absolute path to the Excel file.
     * @param sheetName The name of the specific sheet to read.
     * @return A 2D array of objects containing the test data.
     */
    public static Object[][] getTableArray(String filePath, String sheetName) {
        Object[][] tabArray = null;

        // Using try-with-resources to ensure the file stream is closed automatically
        try (FileInputStream excelFile = new FileInputStream(new File(filePath))) {

            // WorkbookFactory automatically detects whether the file is .xls or .xlsx
            Workbook workbook = WorkbookFactory.create(excelFile);
            System.out.println("DEBUG: Looking for sheet 'DataProvider'. Found sheet name: " + workbook.getSheetName(0));

            Sheet sheet = workbook.getSheetAt(0);

            if (sheet == null) {
                System.err.println("Sheet " + sheetName + " does not exist in " + filePath);
                return null;
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            // Assuming the first row (header) defines the number of columns
            int totalCols = sheet.getRow(0).getLastCellNum();

            // Initialize the array (totalRows - 1 because we skip the header row)
            tabArray = new Object[totalRows - 1][totalCols];

            // Loop through each row starting from index 1 (skipping the header)
            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    // Format and store the cell data in the matrix
                    tabArray[i - 1][j] = getFormattedCellData(cell);
                }
            }
            workbook.close();
        } catch (Exception e) {
            System.err.println("Exception occurred while reading Excel file: " + e.getMessage());
            e.printStackTrace();
        }

        return tabArray;
    }

    /**
     * Helper method to handle different cell types and convert them to the appropriate format.
     * This prevents common issues like integers appearing as doubles (e.g., 123 becoming 123.0).
     * * @param cell The Excel cell to format.
     * @return The formatted data as an Object (String, Boolean, etc.).
     */
    private static Object getFormattedCellData(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                // Check if the numeric cell is actually a date
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }

                double numericValue = cell.getNumericCellValue();
                // Check if the number is an integer (no decimal part)
                if (numericValue % 1 == 0) {
                    return String.valueOf((long) numericValue);
                }
                return String.valueOf(numericValue);

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case FORMULA:
                // Returns the evaluated formula result as a string
                return cell.getCellFormula();

            default:
                return "";
        }
    }
}