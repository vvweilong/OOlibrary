package aprivate.oo.okhttplib;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by zhuxiaolong on 16/5/23.
 */
public class HttpCore {
    private final int SERVER_ERROR_CODE = 0XFF;
    private final int PARAMER_ERROR_CODE = 0X01;
    private final int TIME_ERROR_CODE = 0X02;
    private final int OK_CODE = 0X00;
    private final int UNKONW_CODE = 0XEE;


    private String TAG = "HttpCore";
    private OkHttpClient mOkHttpClient;

    public HttpCore() {
        mOkHttpClient = new OkHttpClient();
    }
    private void clientInit(OkHttpClient client){

    }



    private RequestBody constructRequestBody(@NonNull Map<String, String> paramers) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();

        for (String value : paramers.keySet()) {
            bodyBuilder.add(value, paramers.get(value));
        }
        return bodyBuilder.build();
    }


    private String constructGetUrl(@NonNull Map<String, String> paramers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : paramers.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(paramers.get(key));
            stringBuilder.append("&");
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);


        return stringBuilder.toString();
    }


    private void enqueueCall(final Request request, @NonNull final RequestStringCallback callback) {
        Log.i(TAG,"enqueueCall");
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"onFailure"+e.getMessage());
                callback.onRequestFail();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "response callback in thread:"+Thread.currentThread());

                if (callback == null) {
                    return;
                }
                switch (checkResultCode(response.code())) {
                    case OK_CODE:
                        Log.i(TAG, "OK_CODE:");
                        callback.onRequestComplect(response.code(), response.body().string());
                        break;
                    case SERVER_ERROR_CODE:
                        Log.e(TAG, "SERVER_ERROR_CODE:" + response.message());
                        callback.onRequestComplect(response.code(), response.body().string());
                        break;
                    case PARAMER_ERROR_CODE:
                        Log.e(TAG, "PARAMER_ERROR_CODE: " + response.message());
                        callback.onRequestComplect(response.code(), response.body().string());
                        break;
                    case TIME_ERROR_CODE:
                        Log.e(TAG, "TIME_ERROR_CODE:" + response.message());
                        callback.onRequestComplect(response.code(), response.body().string());
                        break;
                    case UNKONW_CODE:
                        Log.e(TAG, "UNKONW_CODE:" + response.message());
                        callback.onRequestComplect(response.code(), response.body().string());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected void getMethod(@NonNull String url, @NonNull Map<String, String> paramMap, @NonNull RequestStringCallback stringCallback) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url + "/?" + constructGetUrl(paramMap)).get().build();
        enqueueCall(request, stringCallback);
    }

    protected void postMethod(@NonNull String url, @NonNull Map<String, String> paramMap, @NonNull RequestStringCallback stringCallback) {
        Log.i(TAG,"postMethod");
        Request.Builder builder = new Request.Builder();
        RequestBody body = constructRequestBody(paramMap);
        Request request = builder.url(url).post(body).build();
        enqueueCall(request, stringCallback);
    }

    protected void putMethod(@NonNull String url, @NonNull Map<String, String> paramMap, @NonNull RequestStringCallback stringCallback) {
        Request.Builder builder = new Request.Builder();
        RequestBody body = constructRequestBody(paramMap);
        Request request = builder.url(url).put(body).build();
        enqueueCall(request, stringCallback);
    }

//    protected void fileMethod(@NonNull String url, @NonNull Map<String, String> paramMap,@NonNull String filePath, @NonNull RequestStringCallback stringCallback){
//        Request.Builder builder = new Request.Builder();
//        MultipartBody.Builder bodybuilder=new MultipartBody.Builder();
//        MultipartBody.Part part=MultipartBody.Part.createFormData();
//
//
//    }


    private int checkResultCode(int code) {
        if (code > 199 && code < 300) {
            return OK_CODE;
        } else if (code > 399 && code < 500) {
            return PARAMER_ERROR_CODE;
        } else if (code > 499 && code < 600) {
            return SERVER_ERROR_CODE;
        } else if (code == 0) {
            return TIME_ERROR_CODE;
        } else {
            return UNKONW_CODE;
        }
    }


}
