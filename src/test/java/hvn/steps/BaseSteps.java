package hvn.steps;
//
import common.LoggingManager;

import java.util.List;

public class BaseSteps {
    public String getClassName() {
        return getClass().getSimpleName();
    }

    public void compareTwoList(String type, List<String> actual, List<String> expected) {
        boolean result = actual.equals(expected);
        LoggingManager.assertTrue(getClassName(), result, "Verify selected " + type + " in Information form successful", "Failed to verify selected " + type);
    }


}
