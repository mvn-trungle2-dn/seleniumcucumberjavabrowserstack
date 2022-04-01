package common.utils.file;

import common.LoggingManager;

import java.io.*;
import java.util.Map;

import static common.utils.helpers.CommonUtils.sleep;


public class FileUtils {
    private static final String CLASS_NAME = FileUtils.class.getSimpleName();

    public FileUtils() {
    }

    public static String readFile(String filePath) {
        return GenericReader.readFile(filePath);
    }

    public static String readEncodedFile(String filePath, String encoding) {
        return GenericReader.readEncodedFile(filePath, encoding);
    }

    public static void writeToFile(String filePath, String content, Boolean append) {
        GenericWriter.writeToFile(filePath, content, append);
    }

    public static void writeToFile(InputStream inputStream, OutputStream outputStream) {
        GenericWriter.writeToFile(inputStream, outputStream);
    }

    public static Map<String, Object> readXlsxSheetAsMapList(String filePath, String sheetName, String valueCell) throws IOException {
        return ExcelReader.getSheetAsMapList(filePath, sheetName, valueCell);
    }

    public static void closeFileStream(Closeable fileStream) {
        if (fileStream != null) {
            try {
                fileStream.close();
            } catch (IOException var2) {
                LoggingManager.logTrace(CLASS_NAME, "Could not close file stream");
                throw new RuntimeException("Unable to close file stream", var2);
            }
        }
    }

    public static void delete(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] var2 = file.listFiles();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                File entry = var2[var4];
                String a = entry.getPath();
                delete(entry.getPath());
            }
        }
        if (file.delete()) {
            LoggingManager.logTrace(CLASS_NAME, "Deleted file at '" + path + "'");
        } else {
            LoggingManager.logTrace(CLASS_NAME, "Could not delete file at '" + path + "'");
        }
    }

    public static void createFolder(String path) {
        File file = new File(path);
        try {
            if (file.mkdir()) {
                System.out.println("Directory Created");
            } else {
                System.out.println("Directory is not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitCSVExportedCompleted(String downloadPath, String fileName) {
        int counter = 1;
        while (counter < 60) {
            sleep(1);
            File dir = new File(downloadPath);
            File[] dir_contents = dir.listFiles();
            if (!dir_contents[0].getName().equals(fileName)) {
                counter++;
            } else {
                System.out.println("FOUND file: " + dir_contents[0]);
                break;
            }
        }
    }

    public static int getRecordsCountInCSV(String downloadPath, String csvFileName) {
        int lineNumberCount = 0;
        try {
            if (!csvFileName.isEmpty() || csvFileName != null) {
                String filePath = downloadPath + System.getProperty("file.separator") + csvFileName;
                System.out.println(filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    FileReader fr = new FileReader(file);
                    LineNumberReader linenumberreader = new LineNumberReader(fr);
                    while (linenumberreader.readLine() != null) {
                        lineNumberCount++;
                    }
                    //To remove the header
                    lineNumberCount = lineNumberCount - 1;
                    System.out.println("Total number of lines found in csv : " + (lineNumberCount));
                    linenumberreader.close();
                } else {
                    LoggingManager.logError(CLASS_NAME, "File does not exists");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumberCount;
    }

}
