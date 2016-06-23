package aprivate.oo.okhttplib;

/**
 * Created by zhuxiaolong on 16/5/25.
 */
public interface RequestStringCallback {

    void onRequestComplect(int code,String resultStr);
    void onRequestFail();
}
