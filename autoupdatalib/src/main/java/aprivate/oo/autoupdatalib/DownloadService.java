package aprivate.oo.autoupdatalib;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }


    private static final int BUFFER_SIZE = 10 * 1024; // 8k ~ 32K

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(getClass().getSimpleName(), "onHandleIntent");
        createNotification();
        startDownLoadAPK();
    }


    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;

    private void createNotification() {
        Log.i(getClass().getSimpleName(), "createNotification");
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        String appName = getString(getApplicationInfo().labelRes);
        int icon = getApplicationInfo().icon;
        mBuilder.setContentTitle(appName).setSmallIcon(icon);
    }


    private void startDownLoadAPK() {
        Log.i(getClass().getSimpleName(), "startDownLoadAPK");
        InputStream in = null;
        FileOutputStream out = null;
        try {
            String urlStr="abc";
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

            urlConnection.connect();
            long bytetotal = urlConnection.getContentLength();
            long bytesum = 0;
            int byteread = 0;
            in = urlConnection.getInputStream();
            File dir = Environment.getExternalStorageDirectory();
            String apkName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.length());
            File apkFile = new File(dir, apkName);
            out = new FileOutputStream(apkFile);
            byte[] buffer = new byte[BUFFER_SIZE];

            int oldProgress = 0;

            while ((byteread = in.read(buffer)) != -1) {
//                Log.i(getClass().getSimpleName(), "downloading");
                bytesum += byteread;
                out.write(buffer, 0, byteread);

                int progress = (int) (bytesum * 100L / bytetotal);
                // 如果进度与之前进度相等，则不更新，如果更新太频繁，否则会造成界面卡顿
                if (progress != oldProgress) {
                    updateProgress(progress);
                }
                oldProgress = progress;
            }
            // 下载完成
            Log.i(getClass().getSimpleName(), "download finished  " + apkFile.getAbsolutePath());
            mBuilder.setContentText("下载完成").setProgress(0, 0, false);

            Intent installAPKIntent = new Intent(Intent.ACTION_VIEW);
            //如果没有设置SDCard写权限，或者没有sdcard,apk文件保存在内存中，需要授予权限才能安装
            String[] command = {"chmod", "777", apkFile.toString()};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();

            installAPKIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            installAPKIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installAPKIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installAPKIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, installAPKIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            startActivity(installAPKIntent);
            mBuilder.setContentIntent(pendingIntent);
            Notification noti = mBuilder.build();
            noti.flags = Notification.FLAG_AUTO_CANCEL;
            mNotificationManager.notify(0, noti);

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "download apk file error", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateProgress(int progress) {
        //"正在下载:" + progress + "%"
        mBuilder.setContentText("%").setProgress(100, progress, false);
        //setContentInent如果不设置在4.0+上没有问题，在4.0以下会报异常
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingintent);
        mNotificationManager.notify(0, mBuilder.build());
    }


}