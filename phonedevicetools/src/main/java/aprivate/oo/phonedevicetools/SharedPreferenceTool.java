package aprivate.oo.phonedevicetools;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by zhuxiaolong on 16/5/12.
 */
public class SharedPreferenceTool  extends BaseTool{
    private static SharedPreferenceTool ourInstance;

    public static SharedPreferenceTool getInstance() {
        if (ourInstance == null) {
            ourInstance = new SharedPreferenceTool();
        }
        return ourInstance;
    }

    private final String LOGIN_INFOR="LOGIN_INFOR";//登录 信息
    private final String Setting_INFOR="SETTING_INFOR";//系统设置 信息


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;



    /*用户登录信息*/


    private SharedPreferences getLoginInfor(Context context) throws NullPointerException{//获取登录用户 信息sp对象
      return context.getSharedPreferences(LOGIN_INFOR,Context.MODE_PRIVATE);
    }



    /*用户设置信息*/

    private SharedPreferences getUserSettinInfor(Context context) throws NullPointerException{//获取 系统设置 sp对象
        return context.getSharedPreferences(Setting_INFOR,Context.MODE_PRIVATE);
    }

}
