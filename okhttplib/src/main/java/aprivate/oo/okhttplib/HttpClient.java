package aprivate.oo.okhttplib;

import android.support.annotation.MainThread;
import android.util.Log;

import java.util.Map;

/**
 * Created by zhuxiaolong on 16/5/25.
 */
@MainThread
public class HttpClient {
    public static final int METHOD_GET = 1;
    public static final int METHOD_POST = 2;
    public static final int METHOD_PUT = 3;
    public static final int METHOD_FILE = 4;


    private static HttpCore mHttpCore=null;

    private static String TAG="HttpClient";


    private static  HttpCore getCore(){
        if(mHttpCore==null){
            mHttpCore=new HttpCore();
        }
        return mHttpCore;
    }



    @MainThread
    public static void getRequest(String url, Map<String,String> map, final RequestCallback requestCallback){
        Log.i("HttpClient","getRequest");
        getCore().getMethod(url, map, new RequestStringCallback() {

            @Override
            public void onRequestComplect(int code, String resultStr) {
                Log.i("HttpClient","Request complect----  "+resultStr);
                manageRequestCallback(code,requestCallback,resultStr);

            }

            @Override
            public void onRequestFail() {
                Log.i("HttpClient","Request fail ");
                manageRequestCallback(0,requestCallback,"");
            }
        });
    }

    @MainThread
    public static void  postRequest(String url, Map<String,String> map, final RequestCallback requestCallback){
        Log.i("HttpClient","postRequest");

        getCore().postMethod(url, map, new RequestStringCallback() {
            @Override
            public void onRequestComplect(int code, String resultStr) {
                Log.i(TAG,"onRequestComplect"   +" code :"+code+"  result :"+resultStr);
                manageRequestCallback(code,requestCallback,resultStr);
            }

            @Override
            public void onRequestFail() {
                Log.i(TAG,"onRequestFail");
                manageRequestCallback(0,requestCallback,"");
            }
        });
    }

    @MainThread
    public static void putRequest(String url, Map<String,String> map, final RequestCallback requestCallback){
        Log.i("HttpClient","putRequest");
        getCore().putMethod(url, map, new RequestStringCallback() {
            @Override
            public void onRequestComplect(int code, String resultStr) {
                manageRequestCallback(code,requestCallback,resultStr);
            }

            @Override
            public void onRequestFail() {
              manageRequestCallback(0,requestCallback,"");
            }
        });
    }
    @MainThread
    public static void filePostRequest(String url,Map<String,String> map,RequestCallback requestCallback){
        Log.i("HttpClient","fileRequest");

    }

    @MainThread
    private static void manageRequestCallback(int code,RequestCallback callback,String result){
        if (code > 199 && code < 300) {
            callback.onSuccess(result);
        } else if (code > 399 && code < 500) {
            callback.onParamError();
        } else if (code > 499 && code < 600) {
            callback.onServerError();
        } else if (code == 0) {
            callback.onNetError();
        } else {
            callback.unknowError();
        }
    }





}
