package eu.algent.DuckCartUtility.Utils;

public class StringUtil {

    public static int parseIntSafe(String string, int onFail) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return onFail;
        }
    }
}
