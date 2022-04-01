package common.utils.file;


import common.Constants;
import common.LoggingManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class ExcelReader {
    private static final String logger = ExcelReader.class.getSimpleName();

    ExcelReader() {
    }

    static Map<String, Object> getSheetAsMapList(String filepath, String sheetName, String valueCell) throws IOException {
        List<Map<String, Object>> dataList = new ArrayList();
        HashMap<String, Object> data = new HashMap();
        HashMap<String, Object> dataOutput = new HashMap();
        String messageIDColumn = Constants.UID_COLUMN_EXCEL;

        Workbook workbook = filepath.endsWith(".xls") ? new HSSFWorkbook(new FileInputStream(filepath)) : new XSSFWorkbook(new FileInputStream(filepath));
        Throwable var5 = null;

        ArrayList var22;
        try {
            Sheet sheet = ((Workbook) workbook).getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.rowIterator();
            ArrayList<String> columnNames = new ArrayList();
            Row row;
            Iterator cellIterator;
            if (rowIterator.hasNext()) {
                row = (Row) rowIterator.next();
                cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    columnNames.add(((Cell) cellIterator.next()).getStringCellValue());
                }
            }

            while (rowIterator.hasNext()) {
                row = (Row) rowIterator.next();
                cellIterator = row.cellIterator();

                for (int i = 0; cellIterator.hasNext(); ++i) {
                    Cell cell = (Cell) cellIterator.next();
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            data.put(columnNames.get(i), cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            data.put(columnNames.get(i), cell.getNumericCellValue());
                            break;
                        case FORMULA:
                            XSSFFormulaEvaluator evaluator = (XSSFFormulaEvaluator) workbook.getCreationHelper().createFormulaEvaluator();
                            CellValue cellValue = evaluator.evaluate(cell);
                            data.put(columnNames.get(i), cellValue.getStringValue());
                            break;
                        case BOOLEAN:
                            data.put(columnNames.get(i), cell.getBooleanCellValue());
                            break;
                    }
                }
                if (data.get(messageIDColumn).equals(valueCell)) {
//                    return data;
                    dataOutput = data;
                    dataList.add(data);
                    break;
                }
            }

            LoggingManager.logTrace(logger, "Read file '" + filepath + "' as map list");
            var22 = (ArrayList) dataList;
        } catch (Throwable var20) {
            var5 = var20;
            throw var20;
        } finally {
            if (workbook != null) {
                if (var5 != null) {
                    try {
                        ((Workbook) workbook).close();
                    } catch (Throwable var19) {
                        var5.addSuppressed(var19);
                    }
                } else {
                    ((Workbook) workbook).close();
                }
            }

        }
        return dataOutput;
//        return var22;
    }


}

	

