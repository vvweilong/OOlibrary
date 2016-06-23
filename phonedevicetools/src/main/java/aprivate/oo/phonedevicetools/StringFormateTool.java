package aprivate.oo.phonedevicetools;

import android.support.annotation.NonNull;

/**
 * Created by zhuxiaolong on 16/5/12.
 */
public class StringFormateTool extends BaseTool{

    public StringFormateTool() {
    }


    public static boolean isDigitalFormate(@NonNull String str) {
        for (char c : str.toCharArray()) {
            if (!isDigtial(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlpheFormate(@NonNull String str) {
        for (char c : str.toCharArray()) {
            if (!isAlphe(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLengh(@NonNull String str, int min, int max) {
        if (min > 0 && max > 0 && max >= min) {// 需要比较的 最小长度和最大长度合法
            int l = str.length();
            if (l >= min) {
                if (l <= max) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {// 输入比较 长度不合法
            return false;
        }
    }


    private static boolean isAlphe(char c) {
        if (c >= 'A' && c <= 'z') {
            return true;
        }
        return false;
    }

    private static boolean isDigtial(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

}
