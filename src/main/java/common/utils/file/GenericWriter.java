package common.utils.file;

import common.LoggingManager;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

public class GenericWriter {
    private static final String CLASS_NAME = GenericWriter.class.getSimpleName();

    GenericWriter() {
    }

    static void writeToFile(String filepath, String content, Boolean append) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, append);
            try {
                fileWriter.write(content);
            } catch (Throwable var7) {
                try {
                    fileWriter.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
                throw var7;
            }
            fileWriter.close();
        } catch (Exception var8) {
            LoggingManager.logError(CLASS_NAME, "Unable to write to file '" + filepath + "'", var8);
        }
        LoggingManager.logTrace(CLASS_NAME, "Wrote to file '" + filepath + "'");
    }

    static void writeToFile(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buffer = new byte[1024];
            int readBytes;
            while ((readBytes = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, readBytes);
            }
        } catch (Exception var4) {
            LoggingManager.logError(CLASS_NAME, "Unable to write to input stream", var4);
        }
        LoggingManager.logTrace(CLASS_NAME, "Wrote to input stream");
    }

}
