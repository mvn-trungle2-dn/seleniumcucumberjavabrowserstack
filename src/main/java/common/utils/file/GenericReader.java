package common.utils.file;

import common.LoggingManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenericReader {
    private static final String CLASS_NAME = GenericReader.class.getSimpleName();

    GenericReader() {
    }

    static String readFile(String filepath) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String currentLine;
            try {
                while ((currentLine = reader.readLine()) != null) {
                    builder.append(currentLine);
                    builder.append("\n");
                }
            } catch (Throwable var6) {
                try {
                    reader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
                throw var6;
            }
            reader.close();
        } catch (Exception var7) {
            LoggingManager.logError(CLASS_NAME, "Unable to read file '" + filepath + "'", var7);
        }
        LoggingManager.logTrace(CLASS_NAME, "Read file '" + filepath + "'");
        return builder.toString();
    }

    static String readEncodedFile(String filepath, String encoding) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), encoding));
            String currentLine;
            try {
                while ((currentLine = reader.readLine()) != null) {
                    builder.append(currentLine);
                    builder.append("\n");
                }
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
                throw var7;
            }
            reader.close();
        } catch (Exception var8) {
            LoggingManager.logError(CLASS_NAME, "Unable to read encoded file '" + filepath + "'", var8);
        }
        LoggingManager.logTrace(CLASS_NAME, "Read encoded file '" + filepath + "'");
        return builder.toString();
    }

}

