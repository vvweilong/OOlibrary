package aprivate.oo.phonedevicetools;

import android.util.Log;

/**
 * Created by zhuxiaolong on 16/5/13.
 */
public class BaseTool {

    public BaseTool() {
    }


    /*log 部分*/
    private static boolean debug = true;

    protected static void logInfor(String tag,String infor) {
        if (debug) Log.i(tag, infor + "");
    }

    protected static void logError(String tag,String error) {
        if (debug) Log.e(tag, error + "");
    }

}
