package aprivate.oo.okhttplib;

import com.google.gson.Gson;

/**
 * Created by zhuxiaolong on 16/6/6.
 */
public class HttpClientGson<T extends Object> {
    private T t;
    private Gson mGson;

    public HttpClientGson() {
        mGson=new Gson();

    }


    public  T gsonFormate(String json){
        mGson.fromJson(json,t.getClass());
        return null;
    }


}
