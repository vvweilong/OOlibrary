package aprivate.oo.autoupdatalib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zhuxiaolong on 16/6/23.
 */
public class AutoUpdataSDK {
    private static AutoUpdataSDK ourInstance;

    public static AutoUpdataSDK getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AutoUpdataSDK(context);
        }
        return ourInstance;
    }


    private Context mContext;

    private AutoUpdataSDK(Context context) {
        mContext = context;
    }


    private int getCurrentVersionCode() {
        int versionCode = 1;
        try {
            PackageInfo applicationInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionCode = applicationInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    public boolean checkVersionCode(int newCode){
        return getCurrentVersionCode()<newCode;
    }




    private void requestServiceVersionInfor(){



    }


    private void startDownloadAPK(){

    }




}
