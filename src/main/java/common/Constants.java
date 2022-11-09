package common;

import common.utils.PropertyUtils;

import java.io.File;

public class Constants {

    public static final String EXPORT_FILE_PATH = PropertyUtils.getUserDirectory() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator + "export_csv";
    public static final String TEST_DATA_PATH = PropertyUtils.getUserDirectory() + File.separator + "data" + File.separator + "text-dictionary_" + PropertyUtils.getProperty("text.dictionary.version") + ".xlsx";
    public static final String UID_COLUMN_EXCEL = "UID";
    public static final String URL_LOGIN_PORTAL = "";


    public Constants() {
    }
}
